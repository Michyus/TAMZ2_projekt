package com.example.michyus.piskvorky;

public class GameEngine {

    private final static int GRID_NUMBER = 12;

    private float gridSize;

    public GameEngine(){


    }

    public int getGridNumber(){
        return GRID_NUMBER;
    }

    public float getGridSize() {
        return gridSize;
    }

    public void setGridSize(float gridSize) {
        this.gridSize = gridSize;
    }
}
