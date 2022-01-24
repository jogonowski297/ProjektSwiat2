package com.kgwzd.World01;

public class Position {
    int __x;
    int __y;

    public Position(Position position, int xPosition, int yPosition){
        this.__x = 0;
        this.__y = 0;

        if (position != null){
            this.__x = position.__x;
            this.__y = position.__y;
        }
        else {
            if(xPosition != 0) {
                this.__x = xPosition;
            }
            if(yPosition != 0) {
                this.__y = yPosition;
            }
        }
    }

    public int getX(){
        return this.__x;
    }

    public void setX(int value) {
        this.__x = value;
    }

    public int getY(){
        return this.__y;
    }

    public void setY(int value){
        this.__y = value;
    }

    public boolean eq(Position position){
        return ((this.getX() == position.getX()) && (this.getY() == position.getY()));
    }

    public String print(){
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}
