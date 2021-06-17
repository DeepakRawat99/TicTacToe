package rawatapps.tictactoe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Devicedetails extends RecyclerView.ViewHolder {
    ImageView useicon;
    TextView username,desc;
    public Devicedetails(@NonNull View itemView) {
        super(itemView);
        useicon=itemView.findViewById(R.id.userface);
        username=itemView.findViewById(R.id.username);
        desc=itemView.findViewById(R.id.usedesc);
    }
}
