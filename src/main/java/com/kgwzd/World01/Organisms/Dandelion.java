package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

public class Dandelion extends Plant {
    public Dandelion(Dandelion dandelion, Position position, World world){
        super(dandelion,position,world);
    }

    @Override
    public Dandelion clone(){
        return new Dandelion(this,null,null);
    }

    @Override
    public void initParams(){
        this.__power = 0;
        this.__initiative = 0;
        this.__liveLength = 6;
        this.__powerToReproduce = 2;
        this.__sign = 'D' ;
    }
}
