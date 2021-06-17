package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
//custom dialog box for leaving match
public class LeaveMatch extends Dialog implements android.view.View.OnClickListener{
    Activity as;
    Button lmyes,lmno;
    PlayMediaFile pl;
    public LeaveMatch(Activity a) {
        super(a);
        this.as=a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.leavematch);
        lmno=findViewById(R.id.lno);
        lmyes=findViewById(R.id.lyes);
        pl=new PlayMediaFile(as);
        lmyes.setOnClickListener(this);
        lmno.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyes:
                pl.accept.start();
                ScorePreference scorePreference=new ScorePreference(as);

                scorePreference.player1Streak("");
                scorePreference.player2Streak("");
                Intent intent=new Intent(as, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                as.startActivity(intent);
                break;
            case R.id.lno:
                pl.nameclick.start();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
