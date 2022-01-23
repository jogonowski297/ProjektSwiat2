package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;
import java.util.Random;

public abstract class Plant extends Organism{
    public Plant(Plant plant, Position position, World world){
        super(plant, position, world);
    }

    public ArrayList<Action> move(){
        return new ArrayList<Action>();
    }

    public ArrayList<Action> action(){
        ArrayList<Action> result = new ArrayList<Action>();
        Organism newPlant;
        Position newPosition;

        if(this.ifReproduce()){
            ArrayList<Position> pomPositions = this.getFreeNeighboringPosition(this.getPosition());

            if(pomPositions != null){
                newPosition = pomPositions.get(new Random().nextInt(pomPositions.size()));
                newPlant = this.clone();
                newPlant.initParams();
                newPlant.setPosition(newPosition);
                this.setPower(this.getPower()/2);
                result.add(new Action(ActionEnum.A_ADD,newPosition,0,newPlant));
            }
        }
        return result;
    }


    public ArrayList<Position> getFreeNeighboringPosition(Position position){
        return this.__world.filterFreePositions(this.__world.getNeighboringPositions(position));
    }
}