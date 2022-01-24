package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;


public class Grass extends Plant {
    public Grass(Grass grass, Position position, World world){
        super(grass,position,world);
    }

    @Override
    public Grass clone(){
        return new Grass(this,null,null);
    }

    @Override
    public void initParams(){
        this.__power = 0;
        this.__initiative = 0;
        this.__liveLength = 6;
        this.__powerToReproduce = 3;
        this.__sign = 'G' ;
    }

}
