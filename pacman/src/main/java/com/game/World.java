package com.game;
import java.util.Arrays; //Fjern senere

public class World {
    private Entity[][] grid = new Entity[32][28];
    private float[] playerPos = new float[2];
    private int[] ghost1 = new int[2];
    private Player player;


    public World(){
        // Creates the grid world with walls on all sides.
        for (int y = 0; y < grid.length; y++ ){
            for (int x = 0; x < grid[y].length; x++){
                if (y == 0 || y == grid.length - 1){
                    grid[y][x] = new Wall(Variant.wall);
                } else if (x == 0 || x == grid[y].length - 1) {
                    grid[y][x] = new Wall(Variant.wall);
                } else {
                    grid[y][x] = new Empty();
                }
            }
        }
        
        // Sets the players starting positon
        playerPos[0] = 15;
        playerPos[1] = 15;

        player = new Player();
    }

    public int yLength(){
        return grid.length;
    }
    
    public int xLength(){
        return grid[0].length;
    }

    public void movePlayerPos(float x, float y){
        playerPos[0] += x;
        playerPos[1] += y;
    }

    public float[] getPlayerPos(){
        return playerPos;
    }
    
    public Entity getEntity(int x, int y) {
        return grid[y][x];
    }

    public void setSpeedPlayer(float s){
        player.setSpeed(s);
    }

    public Character playerChar(){
        return player;
    }


}
