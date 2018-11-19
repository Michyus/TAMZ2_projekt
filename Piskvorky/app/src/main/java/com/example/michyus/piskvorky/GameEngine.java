package com.example.michyus.piskvorky;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class GameEngine {

    private final static int GRID_NUMBER = 12;

    private Activity gameActivity;

    private float gridSize;
    private Players playerGrid[][];
    private Players currentPlayer;

    public TextView textView_moveOf;

    protected enum Players{
        None, Player1, Player2
    }

    public GameEngine(Activity gameActivity){
        playerGrid = new Players[GRID_NUMBER][GRID_NUMBER];
        this.gameActivity = gameActivity;
        textView_moveOf = this.gameActivity.findViewById(R.id.textView_moveOf);

        startGame(Players.None);

        new GameStartDialog(this, gameActivity);
    }

    public void startGame(Players player){
        for (int y = 0; y < GRID_NUMBER; y++){
            for (int x = 0; x < GRID_NUMBER; x++){
                playerGrid[x][y] = Players.None;
            }
        }

        currentPlayer = player;
    }

    public void addMark(int x, int y){
        if(getPlayerAt(x, y) == Players.None){
            playerGrid[x][y] = currentPlayer;
            switchPlayer();
        }
        Players winner = checkWhoWon();

        if(winner != Players.None){
            Toast.makeText(gameActivity, winner + " has won", Toast.LENGTH_SHORT).show();
        }
    }

    public void switchPlayer(){
        if (currentPlayer == Players.Player1){
            currentPlayer = Players.Player2;
        }
        else {
            currentPlayer = Players.Player1;
        }
        textView_moveOf.setText(currentPlayer.toString());
    }

    public Players checkWhoWon(){
        int length = 4;

        if (hasWinner(Players.Player1, length)){
            return Players.Player1;
        }
        else if (hasWinner(Players.Player2, length)){
            return Players.Player2;
        }


        return Players.None;
    }

    public boolean hasWinner(Players player, int len){
        // Check horizontal
        for(int y=0; y < GRID_NUMBER; y++){
            for(int x=0; x < GRID_NUMBER-len; x++){
                int cnt = 0;
                for (int n=0; n < len; n++){
                    if (getPlayerAt(x+n, y) == player)
                        cnt++;
                    if (cnt >= len)
                        return true;
                }
            }
        }

        // Check vertical
        for(int x=0; x < GRID_NUMBER; x++){
            for(int y=0; y < GRID_NUMBER-len; y++){
                int cnt = 0;
                for (int n=0; n < len; n++){
                    if (getPlayerAt(x, y+n) == player)
                        cnt++;
                    if (cnt >= len)
                        return true;
                }
            }
        }

        // Check \\\\\\
        for(int y=0; y < GRID_NUMBER-len; y++){
            for(int x=0; x < GRID_NUMBER-len; x++){
                int cnt = 0;
                for (int n=0; n < len; n++){
                    if (getPlayerAt(x+n, y+n) == player)
                        cnt++;
                    if (cnt >= len)
                        return true;
                }
            }
        }

        // Check //////
        for(int y=0; y < GRID_NUMBER-len; y++){
            for(int x=len; x < GRID_NUMBER; x++){
                int cnt = 0;
                for (int n=0; n < len; n++){
                    if (getPlayerAt(x-n, y+n) == player)
                        cnt++;
                    if (cnt >= len)
                        return true;
                }
            }
        }

        return false;
    }

    public Players checkEnd(){
        for (int y = 0; y < GRID_NUMBER; y++){
            for (int x = 0; x < GRID_NUMBER; x++){
                if (getPlayerAt(x, y) != Players.None){
                    // Store player at this position
                    Players p = getPlayerAt(x, y);

                    int signX = -1;
                    while (signX < 2){
                        int signY = -1;
                        while (signY < 2){
                            if (signX != 0 || signY != 0){
                                int i = 1;

                                while (i <= 4 && x - i >= 0 && x + i < GRID_NUMBER && y - i >= 0 && y + i < GRID_NUMBER){
                                    if (getPlayerAt(x + i * signX, y + i + signY) == p){
                                        i++;
                                    }
                                    else {
                                        break;
                                    }
                                }

                                if (i == 4){
                                    return p;
                                }
                            }
                            signY++;
                        }
                        signX++;
                    }
                }
            }
        }
        return Players.None;
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
