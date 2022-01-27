package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;

public class Toadstool extends Plant{
    public Toadstool(Toadstool toadstool, Position position, World world){
        super(toadstool,position,world);
    }

    @Override
    public Toadstool clone(){
        return new Toadstool(this, null,null);
    }

    @Override
    public void initParams() {
        this.__power = 0;
        this.__initiative = 0;
        this.__liveLength = 12;
        this.__powerToReproduce = 4;
        this.__sign = 'T';
    }

    public ArrayList<Action> consequence(Organism atackingOrganism){
        ArrayList<Action> result = new ArrayList<Action>();

        if(this.getPower() > atackingOrganism.getPower()){
            result.add(new Action(ActionEnum.A_REMOVE, new Position(null, -1,-1),0, atackingOrganism));
            __world.get__organisms().remove(atackingOrganism);
        }
        else {
            result.add(new Action(ActionEnum.A_REMOVE, new Position(null, -1,-1),0, this));
            result.add(new Action(ActionEnum.A_REMOVE, new Position(null, -1,-1),0, atackingOrganism));
            __world.get__organisms().remove(atackingOrganism);
            __world.get__organisms().remove(this);
        }
        return result;
    }
}
