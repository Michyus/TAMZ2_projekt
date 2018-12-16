package com.example.michyus.piskvorky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;

public class GameEndDialog extends AlertDialog {
    public GameEndDialog(final GameEngine gameEngine, final Context context) {
        super(context);
        MediaPlayer mp;
        mp = MediaPlayer.create(gameEngine.gameActivity, R.raw.cuttingpaper);

        setMessage(gameEngine.winner_name + " " + context.getResources().getString(R.string.won));

        setButton(BUTTON_POSITIVE, context.getResources().getString(R.string.dialog_end_rematch), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, CustomGameActivity.class);
                context.startActivity(intent);
            }
        });

        setButton(BUTTON_NEGATIVE, context.getResources().getString(R.string.dialog_end_menu), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);

        mp.start();
        show();
    }
}
