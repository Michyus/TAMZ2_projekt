package com.example.michyus.piskvorky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class GameEndDialog extends AlertDialog {
    public GameEndDialog(final GameEngine gameEngine, final Context context) {
        super(context);

        setMessage("Nejake info.. kdo vyhral, cas pocet tahu...");

        setButton(BUTTON_POSITIVE, context.getResources().getString(R.string.dialog_end_rematch), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, NewGameActivity.class);
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

        show();
    }
}
