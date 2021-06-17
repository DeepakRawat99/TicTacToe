package rawatapps.tictactoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<Devicedetails> {
private List<Userclass> uc;
public RecyclerAdapter(List<Userclass>dd){
    this.uc=dd;
}
    @NonNull
    @Override
    public Devicedetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.devicelists,null);
    return new Devicedetails(v);
    }
@Override
    public void onBindViewHolder(@NonNull Devicedetails holder, int position) {
            Userclass details=uc.get(position);
            holder.useicon.setImageResource(details.img);
            holder.username.setText(details.usname);
            holder.desc.setText(details.usdesc);
    }

    @Override
    public int getItemCount() {
        return uc.size();
    }
}
