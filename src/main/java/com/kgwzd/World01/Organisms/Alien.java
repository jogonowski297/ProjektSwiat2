package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

public class Alien extends Aliens{
    public Alien(Alien alien, Position position, World world){
        super(alien,position,world);
    }

    @Override
    public void initParams(){
        this.__power = 99;
        this.__initiative = 99;
        this.__liveLength = 99;
        this.__powerToReproduce = 99;
        this.__sign = 'A';
    }
}
