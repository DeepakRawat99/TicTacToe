package rawatapps.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
TextView uname,comp,twop,vsfriend;
Button change;
ImageButton imageButton;
SharedPreferences sps;
PlayMediaFile playMediaFile;
Switch sound;
//settings activity to update details
    ScorePreference scorePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        uname=findViewById(R.id.username);
        comp=findViewById(R.id.strcom);
        twop=findViewById(R.id.strtwo);
        imageButton=findViewById(R.id.userimage);
        vsfriend=findViewById(R.id.strfriend);
        change=findViewById(R.id.changename);
        playMediaFile=new PlayMediaFile(this);
        scorePreference=new ScorePreference(this);
        imageButton.setImageResource(scorePreference.getImage());
        String usname = scorePreference.getName();
        uname.setText(usname);
        sps=this.getSharedPreferences("wins",MODE_PRIVATE);
        comp.setText(String.valueOf(sps.getInt("vscomp",0)));
        twop.setText(String.valueOf(sps.getInt("vstwo",0)));
        vsfriend.setText(String.valueOf(sps.getInt("vsfriend",0)));
        imageButton.setOnClickListener(this);
//changes sound to 0 or 1
        sound = findViewById(R.id.sound);
        sound.setOnClickListener(this);
        if(scorePreference.getSound()){
            sound.setChecked(true);
        }
        else sound.setChecked(false);
    }

    public void changeuname(View view) {
        playMediaFile.select.start();
        EditUName editUName=new EditUName(this);
        editUName.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        editUName.show();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //updates evrything on changing
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ScorePreference score=new ScorePreference(this);
            String usname = score.getName();
            int id = score.getImage();
            uname.setText(usname);
            imageButton.setImageResource(id);
            imageButton.setAdjustViewBounds(true);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }


    public void back(View view) {
        playMediaFile.select.start();
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.userimage){
            playMediaFile.select.start();
            ChangeImage changeImage=new ChangeImage(this);
            changeImage.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            changeImage.show();
        }
        if(v.getId()==R.id.sound){
            if(sound.isChecked()){
                scorePreference.soundOnOff(true);
                playMediaFile.accept.start();
            }
            else{

                scorePreference.soundOnOff(false);
            }
            super.recreate();
        }
    }
}
