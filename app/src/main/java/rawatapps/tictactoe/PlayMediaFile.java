package rawatapps.tictactoe;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayMediaFile {
    MediaPlayer select,winner,loser,noplace,accept,nameclick;
    ScorePreference scorePreference;
    public PlayMediaFile(Context context){
        scorePreference=new ScorePreference(context);
        if(scorePreference.getSound()){
        select=MediaPlayer.create(context,R.raw.select_game);
        winner=MediaPlayer.create(context,R.raw.winnersound);
        loser=MediaPlayer.create(context,R.raw.lose);
        noplace=MediaPlayer.create(context,R.raw.noplace);
        accept=MediaPlayer.create(context,R.raw.accept);
        nameclick=MediaPlayer.create(context,R.raw.nameclick);
    }
      else{
          select=MediaPlayer.create(context,R.raw.nosound);
          winner=loser=noplace=accept=nameclick=select;
        }

    }


}
