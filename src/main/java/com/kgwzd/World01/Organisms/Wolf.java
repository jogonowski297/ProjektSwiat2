package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;
import java.util.Random;

public class Wolf extends Animal{
    public Wolf(Wolf wolf, Position position, World world){
        super(wolf,position,world);
    }

    @Override
    public Wolf clone(){
        return new Wolf(this,null,null);
    }

    @Override
    public void initParams(){
        this.__power = 8;
        this.__initiative = 5;
        this.__liveLength = 20;
        this.__powerToReproduce = 16;
        this.__sign = 'W';
    }

    public ArrayList<Action> move(){
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> pomPositions = this.getNeighboringPositions();
        Position newPosition;
        Organism metOrganism;

        if(pomPositions != null){
            newPosition = pomPositions.get(new Random().nextInt(pomPositions.size()));
            result.add(new Action(ActionEnum.A_MOVE, newPosition, 0, this));
            this.__lastPosition = this.__position;
            metOrganism = this.__world.getOrganismFromPosition(newPosition);
            if(metOrganism != null && !(metOrganism instanceof Plant)){
                result.addAll(metOrganism.consequence(this));
            }
        }
        return result;
    }

    public ArrayList<Position> getNeighboringPosition(){
        return this.__world.filterPositionsWithOtherSpecies(this.__world.getNeighboringPositions(this.getPosition()), this);
    }
}
