package rawatapps.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;

public class ScorePreference {

    //Shared preference file to store the score
    Context context;
    ScorePreference(Context context){
        this.context=context;
    }
    public void player1Streak(String player1){
        SharedPreferences sharedPreferences=context.getSharedPreferences("winstreaks",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Player1",player1);
        editor.apply();
    }
    public void player2Streak(String player2){
        SharedPreferences sharedPreferences=context.getSharedPreferences("winstreaks",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Player2",player2);
        editor.apply();
    }
    public void playerName(String name){
        SharedPreferences sharedPreferences=context.getSharedPreferences("playername",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",name);
        editor.apply();
    }
    public void winsComp(int vscomp){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("vscomp",vscomp);
        editor.apply();
    }
    public void winsTwo(int vstwo){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("vstwo",vstwo);
        editor.apply();
    }
    public void winsfriend(int vsfriend){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("vsfriend",vsfriend);
        editor.apply();
    }
    public void profileImage(int image){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("image",image);
        editor.apply();
    }
    public void soundOnOff(boolean s){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("on",s);
        editor.apply();
    }
    public boolean getSound(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("on",true);
    }
    public int getImage(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("wins",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("image",R.drawable.ic_smile);
    }


    public String getName(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("playername",Context.MODE_PRIVATE);
        return sharedPreferences.getString("username","Player1");
    }
    public String getPlayer1(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("winstreaks",Context.MODE_PRIVATE);
        return sharedPreferences.getString("Player1","0");
    }
    public String getPlayer2(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("winstreaks",Context.MODE_PRIVATE);
        return sharedPreferences.getString("Player2","0");
    }
}
