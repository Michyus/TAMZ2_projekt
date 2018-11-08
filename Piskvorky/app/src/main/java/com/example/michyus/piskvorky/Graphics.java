package com.example.michyus.piskvorky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Graphics extends View{

    private GameEngine gameEngine;
    private Paint mPaint;
    private Bitmap bitmapCircle;
    private Bitmap bitmapCross;
    private float sizeOfElement;


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

            sizeOfElement = gameEngine.getGridSize() / gameEngine.getGridNumber();

            loadAndCreateBitmaps();
        }

        drawGrid(canvas);
        drawPlayers(canvas);

        invalidate();
    }

    private void loadAndCreateBitmaps(){
        // Load bitmaps
        bitmapCircle = BitmapFactory.decodeResource(this.getResources(), R.drawable.circle);
        bitmapCross = BitmapFactory.decodeResource(this.getResources(), R.drawable.cross);

        // Resize bitmaps
        bitmapCircle = Bitmap.createScaledBitmap(bitmapCircle, (int)sizeOfElement, (int)sizeOfElement, true);
        bitmapCross = Bitmap.createScaledBitmap(bitmapCross, (int)sizeOfElement, (int)sizeOfElement, true);
    }

    private void drawPlayers(Canvas canvas){
        for (int y = 0; y < gameEngine.getGridNumber(); y++){
            for (int x = 0; x < gameEngine.getGridNumber(); x++){
                if (gameEngine.getPlayerAt(x, y) != GameEngine.Players.None){
                    Bitmap tempBitmap = null;

                    if (gameEngine.getPlayerAt(x, y) == GameEngine.Players.Player1){
                        tempBitmap = bitmapCircle;
                    }
                    else {
                        tempBitmap = bitmapCross;
                    }

                    canvas.drawBitmap(tempBitmap,sizeOfElement * x,sizeOfElement * y, mPaint);
                }
            }
        }
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i <= gameEngine.getGridNumber(); i++){
            // Horizontal line
            canvas.drawLine(0,sizeOfElement * i, gameEngine.getGridSize(),sizeOfElement * i, mPaint);

            // Vertical line
            canvas.drawLine(sizeOfElement * i,0,sizeOfElement * i, gameEngine.getGridSize(), mPaint);
        }
    }
}
