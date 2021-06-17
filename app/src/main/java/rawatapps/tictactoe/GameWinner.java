package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameWinner extends Dialog implements android.view.View.OnClickListener {
    public Activity act;
    public TextView textView;
    String Winner;
    public Button plag,mame;
    PlayMediaFile pl;
    public GameWinner(Activity activity,String winn) {
        super(activity);
        this.act=activity;
        this.Winner=winn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamewinner);
        textView=findViewById(R.id.win);
        plag=findViewById(R.id.pa);
        mame=findViewById(R.id.mm);
        textView.setText(Winner);
        pl=new PlayMediaFile(act);
        plag.setOnClickListener(this);
        mame.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pa:
                pl.accept.start();
                act.recreate();
                break;
            case R.id.mm:
                pl.nameclick.start();
                ScorePreference scorePreference=new ScorePreference(act);
                scorePreference.player1Streak("");
                scorePreference.player2Streak("");
                Intent intent=new Intent(act, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                act.startActivity(intent);
                break;
            default:
                    break;
        }
        dismiss();
    }
}
