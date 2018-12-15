package com.example.michyus.piskvorky;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {

    //TODO remove UI updates from GameEngine and move them to separate class

    private int GRID_NUMBER = 12;

    private DBHelper games_db;

    public Activity gameActivity;

    private float gridSize;
    private Players playerGrid[][];
    private Players currentPlayer;

    private int countToWin;
    private int aiLevel;

    private String name_1;
    private String name_2;
    public String winner_name;

    public TextView textView_moveOf;
    public ImageView imageView_moveOf;

    public int moves;

    private boolean check = true; // TODO: get rid of this

    protected enum Players{
        None, Player1, Player2
    }

    public GameEngine(Activity gameActivity, int aiLevel, int size, int countToWin, String name_1, String name_2){
        playerGrid = new Players[GRID_NUMBER][GRID_NUMBER];

        this.aiLevel = aiLevel;
        this.gameActivity = gameActivity;
        this.GRID_NUMBER = size;
        this.countToWin = countToWin;
        this.name_1 = name_1;
        this.name_2 = name_2;
        this.winner_name = "Nikdo";

        this.moves = 0;

        textView_moveOf = this.gameActivity.findViewById(R.id.textView_moveOf);
        imageView_moveOf = this.gameActivity.findViewById(R.id.imageView_moveOf);

        startGame(Players.Player1);
    }

    public void startGame(Players player){
        for (int y = 0; y < GRID_NUMBER; y++){
            for (int x = 0; x < GRID_NUMBER; x++){
                playerGrid[x][y] = Players.None;
            }
        }
        currentPlayer = player;
        imageView_moveOf.setImageResource(R.drawable.circle);
        textView_moveOf.setText(name_1);
    }

    public void addMark(int x, int y){
        moves++;
        if(getPlayerAt(x, y) == Players.None){
            playerGrid[x][y] = currentPlayer;
            switchPlayer();
        }
        Players winner = checkWhoWon();

        if(winner != Players.None && this.check || moves >= this.GRID_NUMBER*this.GRID_NUMBER){
            if (moves >= this.GRID_NUMBER*this.GRID_NUMBER){
                this.winner_name = "Nikdo";
            }
            this.check = false;
            saveToDatabase();
            new GameEndDialog(this, gameActivity);
            this.gameActivity.findViewById(R.id.gameFrame).setOnTouchListener(null);
        }
    }

    public void switchPlayer(){
        if (currentPlayer == Players.Player1){
            currentPlayer = Players.Player2;
            imageView_moveOf.setImageResource(R.drawable.cross);
            textView_moveOf.setText(name_2);
            aiMove();
        }
        else {
            currentPlayer = Players.Player1;
            imageView_moveOf.setImageResource(R.drawable.circle);
            textView_moveOf.setText(name_1);
        }

    }

    private void aiMove(){
        if (this.aiLevel == 1){
            boolean placed = false;
            while (placed == false){
                int randomX = ThreadLocalRandom.current().nextInt(0, this.GRID_NUMBER);
                int randomY = ThreadLocalRandom.current().nextInt(0, this.GRID_NUMBER);

                if(getPlayerAt(randomX, randomY) == Players.None){
                    addMark(randomX, randomY);
                    placed = true;
                }
            }
        }else if (this.aiLevel == 2){
            boolean placed = false;
            if(moves < 2){
                while (placed == false){
                    int randomX = ThreadLocalRandom.current().nextInt(1, this.GRID_NUMBER - 1);
                    int randomY = ThreadLocalRandom.current().nextInt(1, this.GRID_NUMBER - 1);

                    if(getPlayerAt(randomX, randomY) == Players.None){
                        addMark(randomX, randomY);
                        placed = true;
                    }
                }
            }else {
                for(int y=1; y < GRID_NUMBER-1; y++){
                    for(int x=1; x < GRID_NUMBER-1; x++){
                        if(getPlayerAt(x, y) == Players.Player2 && !placed){
                            if (getPlayerAt(x+1, y+1) == Players.None){
                                addMark(x+1,y+1);
                                placed = true;
                            } else if (getPlayerAt(x-1, y-1) == Players.None){
                                addMark(x-1,y-1);
                                placed = true;
                            } else if (getPlayerAt(x+1, y-1) == Players.None){
                                addMark(x+1,y-1);
                                placed = true;
                            } else if (getPlayerAt(x-1, y+1) == Players.None){
                                addMark(x-1,y+1);
                                placed = true;
                            }
                        }
                    }
                }
                while (placed == false){
                    int randomX = ThreadLocalRandom.current().nextInt(0, this.GRID_NUMBER);
                    int randomY = ThreadLocalRandom.current().nextInt(0, this.GRID_NUMBER);

                    if(getPlayerAt(randomX, randomY) == Players.None){
                        addMark(randomX, randomY);
                        placed = true;
                    }
                }
            }
        }
    }

    public Players checkWhoWon(){
        if (hasWinner(Players.Player1, countToWin)){
            this.winner_name = this.name_1;
            return Players.Player1;
        }
        else if (hasWinner(Players.Player2, countToWin)){
            this.winner_name = this.name_2;
            return Players.Player2;
        }

        return Players.None;
    }

    public boolean hasWinner(Players player, int len){
        // Check horizontal
        for(int y=0; y < GRID_NUMBER; y++){
            for(int x=0; x <= GRID_NUMBER-len; x++){
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
            for(int y=0; y <= GRID_NUMBER-len; y++){
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
        for(int y=0; y <= GRID_NUMBER-len; y++){
            for(int x=0; x <= GRID_NUMBER-len; x++){
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
        for(int y=0; y <= GRID_NUMBER-len; y++){
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

    private void saveToDatabase(){
        games_db = new DBHelper(gameActivity);

        games_db.insertGame(this.winner_name, this.name_1, this.name_2, this.moves,this.GRID_NUMBER,this.countToWin, this.aiLevel);
    }
}
//this.name_1, this.name_2, this.moves, this.GRID_NUMBER, this.countToWin)