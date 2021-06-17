package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChangeImage extends Dialog implements android.view.View.OnClickListener {
    Activity con;
    ImageButton sad,smile,dum,pir,spl,slep,sav,nic;
    ImageView imageView;
    Button changeP;
    int id=0;
    PlayMediaFile playMediaFile;
    public ChangeImage(Activity activity) {
        super(activity);
        this.con=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeimage);
        smile=findViewById(R.id.avat1);
        sad=findViewById(R.id.avat2);
        dum=findViewById(R.id.avat3);
        pir=findViewById(R.id.avat4);
        spl=findViewById(R.id.avat5);
        slep=findViewById(R.id.avat6);
        sav=findViewById(R.id.avat7);
        nic=findViewById(R.id.avat8);
        changeP=findViewById(R.id.changeimage);
        imageView=findViewById(R.id.img);
        //these avatars are created with adobe illustrator
        smile.setBackgroundResource(android.R.color.transparent);
        sad.setBackgroundResource(android.R.color.transparent);
        dum.setBackgroundResource(android.R.color.transparent);
        pir.setBackgroundResource(android.R.color.transparent);
        spl.setBackgroundResource(android.R.color.transparent);
        slep.setBackgroundResource(android.R.color.transparent);
        sav.setBackgroundResource(android.R.color.transparent);
        nic.setBackgroundResource(android.R.color.transparent);

        playMediaFile= new PlayMediaFile(con);

        smile.setOnClickListener(this);
        sad.setOnClickListener(this);
        dum.setOnClickListener(this);
        pir.setOnClickListener(this);
        spl.setOnClickListener(this);
        changeP.setOnClickListener(this);
        slep.setOnClickListener(this);
        sav.setOnClickListener(this);
        nic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.avat1:
                id=R.drawable.ic_smile;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat2:
                id=R.drawable.ic_really;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat3:
                id=R.drawable.ic_faces;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat4:
                id=R.drawable.ic_angry;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat5:
                id=R.drawable.ic_cry;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat6:
                id=R.drawable.ic_sleepy;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat7:
                id=R.drawable.ic_savage;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.avat8:
                id=R.drawable.ic_nice;
                imageView.setImageResource(id);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.changeimage:
                if(id!=0){
                    playMediaFile.accept.start();
                    ScorePreference scorePreference=new ScorePreference(con);
                    scorePreference.profileImage(id);
                    dismiss();
                }
                default:
                    dismiss();
        }
    }
}
