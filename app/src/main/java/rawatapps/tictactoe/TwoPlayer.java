package rawatapps.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.HashMap;

public class TwoPlayer extends AppCompatActivity implements android.view.View.OnClickListener {
    ImageButton b11,b12,b13,b21,b22,b23,b31,b32,b33;
    ImageView plyr,plyr2;
    HashMap<String,ImageButton> vars=new HashMap<>();
    private AdView adView;
    int total=0;
    int[][] matrix=new int[3][3];
    int turn,turn2,checkwin=0;
    int streak=0,streak1=0;
    int myturn=0;
    ScorePreference scorePreference;
    SharedPreferences ses;
    PlayMediaFile playMediaFile;
    String pl,p2;
    TextView first_player,second_player,winner1,winner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adView=findViewById(R.id.twoadd);
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
        plyr=findViewById(R.id.player1ico);
        plyr2=findViewById(R.id.player2ico);

        playMediaFile=new PlayMediaFile(this);


        first_player=findViewById(R.id.firstplayer);
        second_player=findViewById(R.id.secplayer);
        winner1=findViewById(R.id.win1);
        winner2=findViewById(R.id.win2);
        ses=this.getSharedPreferences("wins",MODE_PRIVATE);
        myturn=(int) (Math.random()*2);

       Intent intent=getIntent();
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


        pl=intent.getStringExtra("player1");
        p2=intent.getStringExtra("player2");

         scorePreference=new ScorePreference(this);
         streak=Integer.parseInt(scorePreference.getPlayer1());
         streak1=Integer.parseInt(scorePreference.getPlayer2());

        first_player.setText(pl);
        second_player.setText(p2);
        winner1.setText(scorePreference.getPlayer1());
        winner2.setText(scorePreference.getPlayer2());

        if(pl.equals(scorePreference.getName())){
            plyr.setImageResource(scorePreference.getImage());
        }
        else {
            if(p2.equals(scorePreference.getName())){
                plyr2.setImageResource(scorePreference.getImage());
            }
        }

        if(myturn==0){ // changes color of text of the player whose turn is next
        first_player.setTextColor(Color.GREEN);
            second_player.setTextColor(Color.GRAY);
        }
        else{
            second_player.setTextColor(Color.GREEN);
            first_player.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onBackPressed() {
        LeaveMatch leaveMatch=new LeaveMatch(TwoPlayer.this);
        leaveMatch.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        leaveMatch.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.a11://just like singleplayer
                if(checkPlace(0,0)){//checks if the place is empty
                    turn=0;
                    turn2=0;
                    changepicture(); //changes picture
                    checkRow(0,0);//checks if user has won or not
                }
                break;
            case R.id.a12:
                if(checkPlace(0,1)){
                    turn=0;
                    turn2=1;

                    changepicture();
                    checkRow(0,1);

                }
                break;
            case R.id.a13:
                if(checkPlace(0,2)){
                    turn=0;
                    turn2=2;

                    changepicture();
                    checkRow(0,2);

                }
                break;
            case R.id.a21:
                if(checkPlace(1,0)){
                    turn=1;
                    turn2=0;

                    changepicture();

                    checkRow(1,0);
                }
                break;
            case R.id.a22:
                if(checkPlace(1,1)){
                    turn=1;
                    turn2=1;

                    changepicture();

                    checkRow(1,1);
                }
                break;
            case R.id.a23:
                if(checkPlace(1,2)){
                    turn=1;
                    turn2=2;

                    changepicture();

                    checkRow(1,2);
                }
                break;
            case R.id.a31:
                if(checkPlace(2,0)){
                    turn=2;
                    turn2=0;

                    changepicture();

                    checkRow(2,0);
                }
                break;
            case R.id.a32:
                if(checkPlace(2,1)){
                    turn=2;
                    turn2=1;

                    changepicture();

                    checkRow(2,1);
                }
                break;
            case R.id.a33:
                if(checkPlace(2,2)){
                    turn=2;
                    turn2=2;

                    changepicture();

                    checkRow(2,2);
                }
                break;
        }
    }


    public void getWinner(int i){
        switch(i){
            case 1:
                checkwin=1;
                playMediaFile.winner.start();
                GameWinner gameWinner= new GameWinner(this,pl+" Wins");
                gameWinner.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                gameWinner.setCancelable(false);
                gameWinner.setCanceledOnTouchOutside(false);
                gameWinner.show();
                if(pl.equals(scorePreference.getName())){
                    int winstwo=ses.getInt("vstwo",0);
                    scorePreference.winsTwo(winstwo+1);
                }
                streak++;
                scorePreference.player1Streak(String.valueOf(streak));
                break;
            case 2:

                checkwin=1;
                playMediaFile.winner.start();
                GameWinner game= new GameWinner(this,p2+" Wins");
                game.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                game.setCancelable(false);
                game.setCanceledOnTouchOutside(false);
                game.show();
                if(p2.equals(scorePreference.getName())){
                    int winstwo=ses.getInt("vstwo",0);
                    scorePreference.winsTwo(winstwo+1);
                }
                streak1++;
                scorePreference.player2Streak(String.valueOf(streak1));
                break;
            default:
                break;
        }
    }
    public void checkRow(int row, int col){
        if(matrix[row][0] == matrix[row][1] && matrix[row][0] == matrix[row][2]){   //Row Check
            getWinner(matrix[row][0]);
        }
        else{
            if(matrix[0][col]==matrix[1][col] && matrix[0][col]== matrix[2][col]){ //Column Check
                getWinner(matrix[0][col]);
            }
            else {
                if(matrix[0][0]>0){
                    if((matrix[0][0]==matrix[1][1] && matrix[1][1]==matrix[2][2])){   //diagonal check
                        getWinner(matrix[1][1]);
                    }}
                if(matrix[0][2]>0){
                    if((matrix[0][2]==matrix[1][1] && matrix[1][1]==matrix[2][0])){   //diagonal check
                        getWinner(matrix[1][1]);
                    }}

                total++;
                if (total >= 9) {
                    GameWinner win = new GameWinner(this, "It's a DRAW");
                    win.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    win.setCancelable(false);
                    win.setCanceledOnTouchOutside(false);
                    win.show();
                }
            }
        }
    }
   public void changepicture(){

       String pos = turn + "" + turn2;
       if(myturn==0 && checkwin!=1) {
           checkwin=0;
           vars.get(pos).setBackground(getDrawable(R.drawable.cross));
           matrix[turn][turn2]=1;
           myturn=1;
           second_player.setTextColor(Color.GREEN);
           first_player.setTextColor(Color.GRAY);
       }
       else{
       if(myturn==1) {
           vars.get(pos).setBackground(getDrawable(R.drawable.zero));
           matrix[turn][turn2]=2;
           myturn=0;
           second_player.setTextColor(Color.GRAY);
           first_player.setTextColor(Color.GREEN);
       }}
   }

    public boolean checkPlace(int x,int y){
        if(matrix[x][y]==0){

            playMediaFile.noplace.start();
            return true;
        }
        else{
            playMediaFile.noplace.start();
            return false;
        }

    }
}
