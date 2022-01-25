package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;

import java.util.ArrayList;
import java.util.Objects;
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
        ArrayList<Position> pomPositions = this.__world.filterFreePositions(this.getNeighboringPositions());
        Position newPosition;

        if(pomPositions != null){
            newPosition = pomPositions.get(new Random().nextInt(pomPositions.size()));
            result.add(new Action(ActionEnum.A_MOVE,newPosition,0,this));
            this.__lastPosition = this.__position;
        }


        return result;
    }

    public ArrayList<Action> action(){
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> twoNeighboringPositions = this.getTwoNeighboringPositions();
        for(Position p : this.getTwoNeighboringPositionsTrue()){
            System.out.println("Organizm z pozycji: " + p.print() + " został zatrzymany");
        }
        System.out.println("pozycje true: " +this.getTwoNeighboringPositionsTrue());
        __world.setPositionsWithStop(this.getTwoNeighboringPositionsTrue());
        __world.stopWorld(twoNeighboringPositions);

        result.add(new Action(ActionEnum.A_STOP, this.getPosition(), 0, this));

        return result;
    }

    public ArrayList<Position> getNeighboringPositions(){
        return this.__world.getNeighboringPositions(this.getPosition());
    }

    public ArrayList<Position> getTwoNeighboringPositions(){
        return this.__world.getTwoNeigthboringPostions(this.getPosition());
    }

    public ArrayList<Position> getTwoNeighboringPositionsTrue(){
        ArrayList<Position> result = new ArrayList<Position>();
        for(Organism o : __world.get__organisms()){
            for ( Position p : this.__world.getTwoNeigthboringPostions(this.getPosition())) {
                if (Objects.equals(o.getPosition().print(), p.print())) {
                    result.add(p);
                }
            }
        }
        return result;
    }

}
