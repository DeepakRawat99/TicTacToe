package rawatapps.tictactoe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    Button splayer,tplayer,local;
    ImageButton set,exit;
    CustomDialogClass customDialogClass;
    private AdView adView;
    PlayMediaFile mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialising demo Ads for application
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adView=findViewById(R.id.mainadd);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        splayer=findViewById(R.id.singleplay);
        tplayer=findViewById(R.id.twoplay);
        local=findViewById(R.id.local);
        set=findViewById(R.id.settings);
        exit=findViewById(R.id.exit);
        //Media file on click
        mp=new PlayMediaFile(this);
        //I have Created custom dialog box for requests
        customDialogClass=new CustomDialogClass(MainActivity.this);
        customDialogClass.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
       //Checking storage permission to save scores and username of players

        int permissioncheck= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissioncheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},28);
        }else {
            ScorePreference scorePreference=new ScorePreference(this);
            scorePreference.player1Streak("0");
            scorePreference.player2Streak("0");
        }
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.select.start();
                Intent i=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    public void singleplayer(View view) {
        mp.select.start();
        Intent in =new Intent(this,SinglePlayer.class);
        startActivity(in);
    }

    public void twoplayer(View view) {
        mp.select.start();
        PlayerNames playerNames=new PlayerNames(this);
        playerNames.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        playerNames.show();
    }

    public void locally(View view) {
        mp.select.start();
        Toast.makeText(this, "This Feature Will Be Added Soon", Toast.LENGTH_LONG).show();
    /*Intent in =new Intent(this,devicelist.class);
    startActivity(in);*/
    }


    public void exit(View view) {

        mp.select.start();
        customDialogClass.show();
    }

    @Override
    public void onBackPressed() {
        customDialogClass.show();
    }
}
