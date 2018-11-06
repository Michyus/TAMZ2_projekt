package com.example.michyus.piskvorky;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Graphics extends View{

    private GameEngine gameEngine;
    private Paint mPaint;

    public Graphics(Context context, GameEngine gameEngine) {
        super(context);

        this.gameEngine = gameEngine;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if (gameEngine.getGridSize() == 0){
            gameEngine.setGridSize(canvas.getWidth());
        }

        drawGrid(canvas);

        invalidate();
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i <= gameEngine.getGridNumber(); i++){
            // Horizontal line
            canvas.drawLine(0, gameEngine.getGridSize() / gameEngine.getGridNumber() * i,
                    gameEngine.getGridSize(), gameEngine.getGridSize() / gameEngine.getGridNumber() * i, mPaint);

            // Vertical line
            canvas.drawLine(gameEngine.getGridSize() / gameEngine.getGridNumber() * i, 0,
                    gameEngine.getGridSize() / gameEngine.getGridNumber() * i, gameEngine.getGridSize(), mPaint);
        }
    }
}
