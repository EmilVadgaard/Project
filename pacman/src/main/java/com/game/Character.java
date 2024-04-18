package com.game;

import com.game.Direction;

public abstract class Character {
    Direction direction;
    float speed;

    

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public float getSpeed(){
        return this.speed;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    //public void setVariant(){}
}