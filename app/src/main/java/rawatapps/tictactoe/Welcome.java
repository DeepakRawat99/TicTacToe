package rawatapps.tictactoe;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Welcome extends AppCompatActivity {
ImageView img1;
TextView text1,text2;
private Handler handler;
private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        img1=findViewById(R.id.aceapps);
        text1=findViewById(R.id.ace);
        text2=findViewById(R.id.apps);


        final Animation anim= AnimationUtils.loadAnimation(Welcome.this,R.anim.fadein);
        handler=new Handler();
        //Creating thread to run animations and wait till animation ends
        this.thread = new Thread(new Runnable() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                img1.startAnimation(anim);
                    text1.startAnimation(anim);
                    text2.startAnimation(anim);
                }
            });
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            Welcome.this.finish();
        }
    });
        this.thread.start();

    }
}
