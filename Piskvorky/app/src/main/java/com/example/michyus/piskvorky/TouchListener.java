package com.example.michyus.piskvorky;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    private GameEngine gameEngine;

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;

    MediaPlayer mp;

    public TouchListener(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.mp = MediaPlayer.create(gameEngine.gameActivity, R.raw.click);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float sizeOfElement = gameEngine.getGridSize() / gameEngine.getGridNumber();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) (event.getX() / sizeOfElement);
                startY = (int) (event.getY() / sizeOfElement);
                break;
            case MotionEvent.ACTION_UP:
                stopX = (int) (event.getX() / sizeOfElement);
                stopY = (int) (event.getY() / sizeOfElement);
                break;
            default:
                break;
        }

        if (startX < gameEngine.getGridNumber() && startY < gameEngine.getGridNumber()){
            if (startX == stopX && startY == stopY){
                gameEngine.addMark(startX, startY);
                mp.start();
            }
        }

        return true;
    }
}
