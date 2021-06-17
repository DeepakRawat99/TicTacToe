package rawatapps.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes,no;
    LinearLayout ll;
    PlayMediaFile pl;
    public CustomDialogClass(Activity a) {
        super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exitdialog);
        
        ll=findViewById(R.id.exitll);
        pl=new PlayMediaFile(c);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.yes:
            pl.accept.start();
            c.finish();
            break;
        case R.id.no:
            pl.accept.start();
            dismiss();
            break;
        default:
            break;
    }
    dismiss();
    }
}
