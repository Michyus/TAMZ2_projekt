package com.example.michyus.piskvorky;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    public TouchListener(){

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
