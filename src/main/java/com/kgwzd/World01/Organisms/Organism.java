package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.*;

import java.util.ArrayList;

public abstract class Organism implements OrganismInterface {
    int __power;
    int __initiative;
    Position __position;
    int __liveLength;
    int __powerToReproduce;
    char __sign;
    World __world;

    public Organism (Organism organism, Position position, World world){
        if (organism != null) {
            this.__power = organism.__power;
            this.__initiative = organism.__initiative;
            this.__liveLength = organism.__liveLength;
            this.__powerToReproduce = organism.__powerToReproduce;
            this.__sign = organism.__sign;
        }
        else {
            if (position != null){
                this.__position = position;
            }
            if (world != null){
                this.__world = world;
            }
            initParams();
        }
    }

    public int getPower(){
        return this.__power;
    }

    public void setPower(int value){
        this.__power = value;
    }

    public int getInitiative(){
        return this.__initiative;
    }

    public void setInitiative(int value){
        this.__initiative = value;
    }

    public Position getPosition(){
        return this.__position;
    }

    public void setPosition(Position value){
        this.__position = value;
    }

    public int getLiveLength(){
        return this.__liveLength;
    }

    public void setLiveLength(int value){
        this.__liveLength = value;
    }

    public int getPowerToReproduce(){
        return this.__powerToReproduce;
    }

    public void setPowerToReproduce(int value){
        this.__liveLength = value;
    }

    public char getSign(){
        return this.__sign;
    }

    public void setSign(char value){
        this.__sign = value;
    }

    public World getWorld(){
        return this.__world;
    }

    public void setWorld(World value){
        this.__world = value;
    }

    public Organism clone(){
        return this;
    }

    public ArrayList<Action> consequence(Organism atackingOrganism){
        ArrayList<Action> result = new ArrayList<Action>();

        if(this.getPower() > atackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.A_REMOVE, new Position(null, -1, -1), 0, atackingOrganism)) ;
        }
        else{
            result.add(new Action(ActionEnum.A_REMOVE, new Position(null, -1, -1), 0, this));
        }

        return result;
    }

    public boolean ifReproduce(){
        boolean result = false;

        if (this.getPower() >= this.getPowerToReproduce()){
            result = true;
        }
        return result;
    }

    public String printOrganism(){
        String className = this.getClass().getSimpleName();
        return className + ":" + "\n" + " power: " + this.__power + "\n" + " initiative: " + this.__initiative + "\n" + " liveLength: " + this.__liveLength + "\n" + " position: " + this.__position.print();
    }

}
