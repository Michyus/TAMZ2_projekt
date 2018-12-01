package com.example.michyus.piskvorky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GameStartDialog extends AlertDialog {
    public GameStartDialog(final GameEngine gameEngine, Context context) {
        super(context);

        setMessage(context.getResources().getString(R.string.dialog_start_title));

        setButton(BUTTON_POSITIVE, context.getResources().getString(R.string.dialog_start_player1), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameEngine.startGame(GameEngine.Players.Player1);
            }
        });

        setButton(BUTTON_NEGATIVE, context.getResources().getString(R.string.dialog_start_player2), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameEngine.startGame(GameEngine.Players.Player2);
            }
        });

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);

        show();
    }
}
