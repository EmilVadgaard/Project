package com.game;

import com.game.Direction;

public class Player extends Character{
    private Direction desiredDirection;

    public Player () {
        this.speed = 0;
        this.direction = Direction.west;
        this.desiredDirection = Direction.west;
    }

    public void setDesiredDirection(Direction direction){
        this.desiredDirection = direction;
    }

    public Direction getDesiredDirection(){
        return desiredDirection;
    }
}