package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;

public abstract class Organism {
    int __power = 0;
    int __initiative = 0;
    Position __position = null;
    int __liveLength = 0;
    int __powerToReproduce = 0;
    int __sign = 0;
    World __world = null;

    public Organism (Organism organism, Position position, World world){
        if (organism != null) {
            this.__power = organism.__power;
            this.__initiative = organism.__initiative;
            this.__position = organism.__position;
            this.__liveLength = organism.__liveLength;
            this.__powerToReproduce = organism.__powerToReproduce;
            this.__sign = organism.__sign;
            this.__world = organism.__world;
        }
        else {
            if (position != null){
                this.__position = position;
            }
            if (world != null){
                this.__world = world;
            }
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

    public int getSign(){
        return this.__sign;
    }

    public void setSign(int value){
        this.__sign = value;
    }

    public World getWorld(){
        return this.__world;
    }

    public void setWorld(World value){
        this.__world = value;
    }

    public abstract ArrayList<Action> move();

    public abstract ArrayList<Action> action();

//    abstract Animal initParams();
//
//    abstract Animal _clone();

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

    public String getString(){
        String className = this.getClass().getSimpleName();
        return className + ": power: " + this.__power + "initiative: " + this.__initiative + "liveLength: " + this.__liveLength + "position: " + this.__position;
    }

}
