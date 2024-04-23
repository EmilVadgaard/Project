package com.game;

import com.game.Direction;

public abstract class Character {
    protected Direction direction;
    protected float speed;
    protected float posX;
    protected float posY;

    public float getPosX(){
        return posX;
    }


    public float getPosY() {
        return posY;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //Slet igen
    public void addPosition(){
        this.posY += 0.15f;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void move() {
        switch (this.direction) {
            case north:
                this.posY -= this.speed;
                break;
            case west:
                this.posX -= this.speed;
                break;
            case east:
                this.posX += this.speed;
                break;
            case south:
                this.posY += this.speed;
                break;
            default:
                break;
        }
    }
}