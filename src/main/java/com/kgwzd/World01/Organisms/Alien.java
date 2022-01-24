package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

public class Alien extends Aliens{
    public Alien(Alien alien, Position position, World world){
        super(alien,position,world);
    }

    @Override
    public void initParams(){
        this.__power = 3;
        this.__initiative = 3;
        this.__liveLength = 10;
        this.__powerToReproduce = 6;
        this.__sign = 'S';
    }
}
