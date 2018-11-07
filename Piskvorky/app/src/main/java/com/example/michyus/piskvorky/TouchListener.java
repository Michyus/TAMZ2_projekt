package com.example.michyus.piskvorky;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    private GameEngine gameEngine;

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;

    public TouchListener(GameEngine gameEngine){
        this.gameEngine = gameEngine;
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
            }
        }

        return true;
    }
}
