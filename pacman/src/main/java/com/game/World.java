package com.game;
import java.util.Arrays; //Fjern senere

public class World {
    private Square[][] grid = new Square[28][32];
    private float[] playerPos = new float[2];
    private int[] playerOffsetPos = new int[2];
    private int[] ghost1 = new int[2];
    private Player player;

    private class Square{
        Entity entity;
        int type;

        Square(Entity entity, int type){
            this.entity = entity;
            this.type = type;

        }

        public Entity getEntity(){
            return entity;
        }

        public int getType(){
            return type;
        }
    }
    public World(int world) {
        
    }

    public World(){
        // Creates the grid world with walls on all sides.
        for (int y = 0; y < grid.length; y++ ){
            for (int x = 0; x < grid[y].length; x++){
                if (y == 0 || y == grid.length - 1){
                    grid[y][x] = new Square(new Wall(Variant.wall), 0);
                } else if (x == 0 || x == grid[y].length - 1) {
                    grid[y][x] = new Square(new Wall(Variant.wall), 0);
                } else {
                    grid[y][x] = new Square(new Empty(), 1);
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

    public void movePlayerPos(){
        switch (player.getDesiredDirection()) {
            //collisiondetection
            case north:
                playerPos[1] -= player.getSpeed();
                // playerOffsetPos[0] = (int) Math.floor(playerPos[0]);
                // playerOffsetPos[1] = Math.round(playerPos[1]);
                break;
            case south:
                playerPos[1] += player.getSpeed();
                // playerOffsetPos[0] = (int) Math.ceil(playerPos[0]);
                // playerOffsetPos[1] = Math.round(playerPos[1]);
                break;
            case east:
                playerPos[0] += player.getSpeed();
                // playerOffsetPos[1] = (int) Math.ceil(playerPos[1]);
                // playerOffsetPos[0] = Math.round(playerPos[0]);
                break;
            case west:
                playerPos[0] -= player.getSpeed();
                // playerOffsetPos[1] = (int) Math.floor(playerPos[1]);
                // playerOffsetPos[0] = Math.round(playerPos[0]);
                break;
            default:
                break;
           
        }

    }

    public float[] getPlayerPos(){
        return playerPos;
    }
    
    public Entity getEntity(int x, int y) {
        return grid[y][x].getEntity();
    }

    public void setSpeedPlayer(float s){
        player.setSpeed(s);
    }

    public void setDesiredDirection(Direction direction){
        player.setDesiredDirection(direction);
        if (player.getSpeed() == 0){
            player.setSpeed(0.075f);
        }
    }

    public Character playerChar(){
        return player;
    }


    public void stopMove(){
        Direction playerDirection = player.getDesiredDirection();
        int selectedSquare;
        
        // if (playerDirection == Direction.south){
        //     selectedSquare = grid[playerOffsetPos[0]][playerOffsetPos[1]+1].getType();
        // } else if (playerDirection == Direction.east){
        //     selectedSquare = grid[playerOffsetPos[0]+1][playerOffsetPos[1]].getType();
        // } else {
        //     selectedSquare = grid[playerOffsetPos[0]-1][playerOffsetPos[1]-1].getType();
        // }
        

        if (CollisionDetection.wallCollide(playerDirection, playerPos)){
            player.setDirection(player.getDesiredDirection());
            player.setSpeed(0);
            
            // playerPos[0] = playerOffsetPos[0];
            // playerPos[1] = playerOffsetPos[1];
        }
    }

    public Variant getVariant(int X, int Y){
        return grid[X][Y].getEntity().getVariant();
    }

    public void legalMove(){
        if (CollisionDetection.turnableSection(playerPos, playerOffsetPos, player.getDesiredDirection(), grid[playerOffsetPos[0]][playerOffsetPos[1]].type)){
            player.setDirection(player.getDesiredDirection());
            playerPos[0] = playerOffsetPos[0];
            playerPos[1] = playerOffsetPos[1];

        }
    }

    

}
