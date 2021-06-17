package rawatapps.tictactoe;


import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.HashMap;

public class SinglePlayer extends AppCompatActivity implements View.OnClickListener {
    ImageButton b11,b12,b13,b21,b22,b23,b31,b32,b33;
    ImageView plyr1;
    HashMap<String,ImageButton> vars=new HashMap<>();
    String whosmov="pm";
    TextView singlplayer;
    private AdView adView;
    private Thread thread=null;
    ScorePreference scorePreference;
    char[][] matrix=new char[3][3];
    PlayMediaFile playMediaFile;
    int turn,turn2,val=0;

    SharedPreferences sps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {  //ads at bottom
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adView=findViewById(R.id.singleadd);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        b11=findViewById(R.id.a11);
        b12=findViewById(R.id.a12);
        b13=findViewById(R.id.a13);
        b21=findViewById(R.id.a21);
        b22=findViewById(R.id.a22);
        b23=findViewById(R.id.a23);
        b31=findViewById(R.id.a31);
        b32=findViewById(R.id.a32);
        b33=findViewById(R.id.a33);
        playMediaFile=new PlayMediaFile(this);      //Audio file initialization

        plyr1=findViewById(R.id.player1ico);
        singlplayer=findViewById(R.id.splayer);
        scorePreference=new ScorePreference(this);     //getting details from shared preference file
        String Player1= scorePreference.getName();
        singlplayer.setText(Player1);               // name of player saved in shared preference
        sps=this.getSharedPreferences("wins",MODE_PRIVATE);
        plyr1.setImageResource(scorePreference.getImage()); // image of player saved in shared preference

        val=sps.getInt("vscomp",0);
        vars.put("00",b11);
        vars.put("01",b12);
        vars.put("02",b13);
        vars.put("10",b21);
        vars.put("11",b22);
        vars.put("12",b23);
        vars.put("20",b31);
        vars.put("21",b32);
        vars.put("22",b33);

            b11.setOnClickListener(this);
            b12.setOnClickListener(this);
            b13.setOnClickListener(this);
            b21.setOnClickListener(this);
            b22.setOnClickListener(this);
            b23.setOnClickListener(this);
            b31.setOnClickListener(this);
            b32.setOnClickListener(this);
            b33.setOnClickListener(this);

        }

    @Override
    public void onBackPressed() {
        LeaveMatch leaveMatch=new LeaveMatch(SinglePlayer.this);
        leaveMatch.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        leaveMatch.show();
    }

    @Override
    public void onClick(View v) {
    int winner;
    if(whosmov.equals("pm")) {
        switch (v.getId()) {
            case R.id.a11:
                if (checkPlace(0, 0)) {            //check if space is empty to move
                    changeImage("00", 0, 0);
                    winner = checkWinner();
                    getWinner(winner);

                }

                break;
            case R.id.a12:
                if (checkPlace(0, 1)) {
                    changeImage("01", 0, 1);
                    winner = checkWinner();
                    getWinner(winner);

                }

                break;
            case R.id.a13:
                if (checkPlace(0, 2)) {
                    changeImage("02", 0, 2);
                    winner = checkWinner();
                    getWinner(winner);
                }
                break;
            case R.id.a21:
                if (checkPlace(1, 0)) {
                    changeImage("10", 1, 0);
                    winner = checkWinner();
                    getWinner(winner);

                }
                break;
            case R.id.a22:
                if (checkPlace(1, 1)) {
                    changeImage("11", 1, 1);
                    winner = checkWinner();
                    getWinner(winner);

                }
                break;
            case R.id.a23:
                if (checkPlace(1, 2)) {
                    changeImage("12", 1, 2);
                    winner = checkWinner();
                    getWinner(winner);


                }
                break;
            case R.id.a31:
                if (checkPlace(2, 0)) {
                    changeImage("20", 2, 0);
                    winner = checkWinner();
                    getWinner(winner);


                }
                break;
            case R.id.a32:
                if (checkPlace(2, 1)) {
                    changeImage("21", 2, 1);
                    winner = checkWinner();
                    getWinner(winner);

                }
                break;
            case R.id.a33:
                if (checkPlace(2, 2)) {
                    changeImage("22", 2, 2);
                    winner = checkWinner();
                    getWinner(winner);

                }
                break;
        }
    }
    else Toast.makeText(this, "Wait for your move", Toast.LENGTH_SHORT).show();
        }

    private void changeImage(String s,int x,int y) {
        //put X on postion which is clicked
        vars.get(s).setImageDrawable(getDrawable(R.drawable.cross));
        playMediaFile.noplace.start();
        matrix[x][y]='X';
        whosmov="cm";
    }


    private void compMove() {
        final String pos =bestMove();
        matrix[turn][turn2] = 'O';
        vars.get(pos).setImageDrawable(getDrawable(R.drawable.zero)); //put O in position
        whosmov="pm";
        getWinner(checkWinner());               //check if O has won
        }

        //finding best possible move for computer
        private String bestMove(){
            int bestscore=100,a=0,b=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(matrix[i][j]==0){
                        matrix[i][j]='O';
                        int score=miniMax(matrix,0,true);
                        matrix[i][j]=0;
                        if(score<bestscore){
                            a=i;
                            b=j;
                            bestscore=score;
                        }
                    }
                }
            }
            turn=a;         //position x for matrix
            turn2=b;        //position y for matrix
            return a+""+b;
        }

    //I have used minimax game algorithm to find best possible move for computer
    private int miniMax(char[][] matrix,int depth,boolean ismax) {
        int result=checkWinner();

        if(result!=0){
            return result;
        }

        if(!checkEmptyPlace()){
            return 0;
        }

        if(ismax){
            int best = -100;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(matrix[i][j]==0){
                        matrix[i][j]='X';
                      int score = miniMax(matrix,depth+1, false);
                      best=Math.max(score,best);
                        matrix[i][j]=0;
                    }
                }
            }
            return best;
        }
        else{
            int best = 100;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(matrix[i][j]==0){
                        matrix[i][j]='O';
                        int score = miniMax(matrix,depth+1, true);

                        best=Math.min(score,best);
                        matrix[i][j]=0;
                    }
                }
            }
            return best;
        }
    }

    private int checkWinner() {
            //horizontal check
            for(int i=0;i<3;i++){
                if(matrix[i][0]==matrix[i][1] && matrix[i][1]==matrix[i][2]){
                    if(matrix[i][0]=='X'){
                        return 1;
                    }
                    if(matrix[i][0]=='O'){
                        return -1;
                    }
                }
            }
            //vertical check
        for(int i=0;i<3;i++){
            if(matrix[0][i]==matrix[1][i] && matrix[2][i]==matrix[0][i]){
                if(matrix[0][i]=='X'){
                    return 1;
                }
                if(matrix[0][i]=='O'){
                    return -1;
                }
            }
        }
        // Diagonal check
        if(matrix[0][0]==matrix[1][1] && matrix[1][1]== matrix[2][2]){
            if(matrix[0][0]=='X'){
                return 1;
            }
            if(matrix[0][0]=='O'){
                return -1;
            }
        }
        if(matrix[2][0]==matrix[1][1] && matrix[1][1]== matrix[0][2]){
            if(matrix[2][0]=='X'){
                return 1;
            }
            if(matrix[2][0]=='O'){
                return -1;
            }

        }

        return 0;

    }


    public void getWinner(int ix){ //winner takes glory
        switch(ix){
            case 1:

                playMediaFile.winner.start();
                GameWinner gameWinner= new GameWinner(this,"YOU WIN");      //open dialogue of user win
                gameWinner.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                gameWinner.setCancelable(false);
                gameWinner.setCanceledOnTouchOutside(false);

                gameWinner.show();
                scorePreference.winsComp(val+1); // update score against comp in sp file
                break;
            case -1:
                playMediaFile.loser.start();
                GameWinner game= new GameWinner(this,"YOU LOSE");
                game.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                game.setCancelable(false);
                game.setCanceledOnTouchOutside(false);
                game.show();
                break;
                    }
//Check if spots left or not
        int spots=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(matrix[i][j]==0){
                    spots++;
                }
            }
        }
        if(spots==0 && ix==0){
            GameWinner game= new GameWinner(this,"It's a Draw");
            game.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            game.setCancelable(false);
            game.setCanceledOnTouchOutside(false);
            game.show();
        }
        if(spots!=0 && ix==0 && whosmov.equals("cm")){
            compMove();
        }
    }

public boolean checkEmptyPlace(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(matrix[i][j]==0){
                    return true;
                }

            }
        }
       return false;
}

    public boolean checkPlace(int x,int y){  //check if cell is empty or not
        return matrix[x][y] == 0;
    }

}
