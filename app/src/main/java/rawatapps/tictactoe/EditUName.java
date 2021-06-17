package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditUName extends Dialog implements android.view.View.OnClickListener {
    Activity activityu;
    EditText editText;
    Button button;
    PlayMediaFile pl;
    public EditUName(Activity activity) {
        super(activity);
        this.activityu=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editname);
        editText=findViewById(R.id.custchange);
        button=findViewById(R.id.custbtn);
        button.setOnClickListener(this);
        pl=new PlayMediaFile(activityu);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.custbtn){
            String name=editText.getText().toString();
            if(name.equals("")){
                pl.nameclick.start();
                editText.setError("Please enter valid username");
            }
            else {
                pl.accept.start();
                ScorePreference scorePreference = new ScorePreference(activityu);
                scorePreference.playerName(name);
                Toast.makeText(activityu, "Username Changed", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        }
    }
}
