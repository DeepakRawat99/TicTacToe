package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerNames extends Dialog implements android.view.View.OnClickListener {
    Activity acti;
    EditText player1,player2;
    Button plybtn,disbtn;
    ScorePreference scorePreference;
    PlayMediaFile pl;
    public PlayerNames(Activity act) {
        super(act);
        this.acti=act;
    }
//dialog box to add player names when two players are playing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playernames);
        player1=findViewById(R.id.ply1);
        player2=findViewById(R.id.ply2);
        disbtn=findViewById(R.id.dis);
        plybtn=findViewById(R.id.lplay);
        pl=new PlayMediaFile(acti);

        disbtn.setOnClickListener(this);
        plybtn.setOnClickListener(this);
        scorePreference=new ScorePreference(acti);
       /* if(!scorePreference.getSound()){

        }*/
        player1.setText(scorePreference.getName());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lplay:
                pl.accept.start();
                String fplayer=player1.getText().toString();
                String splayer=player2.getText().toString();
                if(fplayer.equals("")) fplayer="Player1";
                if (splayer.equals("")) splayer="Player2";
                Intent intent=new Intent(acti,TwoPlayer.class);
                intent.putExtra("player1",fplayer);
                intent.putExtra("player2",splayer);
                acti.startActivity(intent);
                break;
            case R.id.dis:
                pl.nameclick.start();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
