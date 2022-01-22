package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;

public class Sheep extends Animal {
    public Sheep(Sheep sheep, Position position, World world){
        super(sheep,position,world);
    }

    public Sheep clone(){
        return new Sheep(this,null,null);
    }

    public void initParams(){
        this.__power = 3;
        this.__initiative = 3;
        this.__liveLength = 10;
        this.__powerToReproduce = 6;
        this.__sign = 'S';
    }

    public ArrayList<Position> getNeighboringPosition(){
        return this.__world.filterPositionsWithoutAnimals(this.__world.getNeighboringPositions(this.getPosition()));
    }
}
