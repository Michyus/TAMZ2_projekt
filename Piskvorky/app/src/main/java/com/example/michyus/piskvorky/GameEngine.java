package com.example.michyus.piskvorky;

public class GameEngine {

    private final static int GRID_NUMBER = 12;

    private float gridSize;
    private Players playerGrid[][];
    private Players currentPlayer = Players.None;

    protected enum Players{
        None, Player1, Player2
    }

    public GameEngine(){
        playerGrid = new Players[GRID_NUMBER][GRID_NUMBER];

        for (int y = 0; y < GRID_NUMBER; y++){
            for (int x = 0; x < GRID_NUMBER; x++){
                playerGrid[x][y] = Players.None;
            }
        }
    }

    public void addMark(int x, int y){
        if(getPlayerAt(x, y) == Players.None){
            playerGrid[x][y] = currentPlayer;
            switchPlayer();
        }
    }

    public void switchPlayer(){
        if (currentPlayer == Players.Player1){
            currentPlayer = Players.Player2;
        }
        else {
            currentPlayer = Players.Player1;
        }
    }

    public Players getPlayerAt(int x, int y){
        return playerGrid[x][y];
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

    public Players getCurrentPlayer() {
        return currentPlayer;
    }
}
