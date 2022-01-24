package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aliens extends Organism{
    Position __lastPosition;
    public Aliens(Organism organism, Position position, World world){
        super(organism,position, world);
        __lastPosition = position;
    }

    public Position getLastPosition(){
        return this.__lastPosition;
    }

    public void setLastPosition(Position value){
        this.__lastPosition = value;
    }

    public ArrayList<Action> move(){
        ArrayList<Action> result = new ArrayList<Action>();

        return result;
    }

    public ArrayList<Action> action(){
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> freePositions = this.getWorld().getFreePositions();

        Position newAlienPosition = freePositions.get(new Random().nextInt(freePositions.size()));
        result.add(new Action(ActionEnum.A_ADD,newAlienPosition,0,this));

        return result;
    }

}
