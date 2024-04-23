package com.game;
import java.util.Arrays; //Fjern senere
import java.io.File;
import java.net.URL;

public class World {
    private Grid grid;
    private Player player;
    private Direction desiredDirection;

    public World(String filepath) {
        URL url = this.getClass().getResource("/" + filepath);
        File map = new File(url.getPath());
        grid = new Grid(map);
        
        player = new Player(15,15);
    }

    public float getPlayerPosX() {
        return player.getPosX();
    }

    public float getPlayerPosY() {
        return player.getPosY();
    }

    public Entity getEntity(int x, int y) {
        return grid.getEntity(x,y);
    }

    public Variant getVariant(int x, int y) {
        return grid.getEntity(x,y).getVariant();
    }

    public int yLength() {
        return grid.getYLength();
    }

    public int xLength() {
        return grid.getXLength();
    }

    public void setSpeedPlayer(float s) {
        player.setSpeed(s);
    }

    public void setDesiredDirection(Direction direction) {
        desiredDirection = direction;

        //placeholder
        setPlayerDirection();
    }

    public void setPlayerDirection() {
        player.setDirection(desiredDirection);
    }

    public void movePlayerPos() {
        player.move();
    }

    public void stopMove() {
        int offset;
        switch (player.getDirection()) {

            case north:
                offset = (int) Math.floor(player.getPosY() - player.getSpeed());
                break;
            case south:
                offset = (int) Math.ceil(player.getPosY() + player.getSpeed() + 1);
                break;
            case east:
                offset = (int) Math.floor(player.getPosX() + player.getSpeed() + 1);
                break;
            case west:
                offset = (int) Math.ceil(player.getPosX() - player.getSpeed());
                break;
            default:
                offset = 0;
                break;
        }
        
        Variant variant = grid.getEntity(offset, (int) player.getPosX()).getVariant();

        if (CollisionDetection.isWall(variant)){
            player.setSpeed(0);
            //player.addPosition();
        }
    }



    // public void legalMove(){
    //     if (CollisionDetection.turnableSection(playerPos, playerOffsetPos, player.getDesiredDirection(), grid[playerOffsetPos[0]][playerOffsetPos[1]].type)){
    //         player.setDirection(player.getDesiredDirection());
    //         playerPos[0] = playerOffsetPos[0];
    //         playerPos[1] = playerOffsetPos[1];

    //     }
    // }

    

}
