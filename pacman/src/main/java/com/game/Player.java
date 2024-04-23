package com.game;

import com.game.Direction;

public class Player extends Character{

    

    public Player (float initX, float initY){
        this.speed = 0;
        this.direction = Direction.west;
        this.posX = initX;
        this.posY = initY;
    }

    public Player () {
        this.speed = 0;
        this.direction = Direction.west;
    }

    public Direction getDirection(){
        return direction;
    }
}