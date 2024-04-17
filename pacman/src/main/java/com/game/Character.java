package com.game;

public abstract class Character {
    Direction direction;
    float speed;

    

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    //public void setVariant(){}
}