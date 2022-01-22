package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;


import java.util.ArrayList;
import java.util.Random;

public class Animal extends Organism{
    Position __lastPosition = null;

    public Animal(Organism organism, Position position, World world) {
        super(organism, position, world);
        this.__lastPosition = position;
    }

    public Position getLastPosition(){
        return this.__lastPosition;
    }

    public void setLastPosition(Position value){
        this.__lastPosition = value;
    }

    public ArrayList<Action> move(){

        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> pomPositions = this.getNeighboringPositions();
        Position newPosition = null;

        if (pomPositions != null && !pomPositions.isEmpty()) {
            newPosition = pomPositions.get(new Random().nextInt(pomPositions.size()));
            result.add(new Action(ActionEnum.A_MOVE,newPosition,0,this));
            this.__lastPosition = this.__position;
            Organism metOrganism = this.__world.getOrganismFromPosition(newPosition);
            if(metOrganism != null){
                result.addAll(metOrganism.consequence(this));
            }
        }
        return result;
    }

    public ArrayList<Action> action(){
        ArrayList<Action> result = new ArrayList<Action>();
        Animal newAnimal = null;
        ArrayList<Position> birthPositions = this.getNeighboringBirthPositions();

        if(this.ifReproduce() && !birthPositions.isEmpty()){
            Position newAnimalPosition = birthPositions.get(new Random().nextInt(birthPositions.size()));
//            newAnimal = this._clone();
//            newAnimal.initParams();
            newAnimal.__position = newAnimalPosition;
            this.__power = (this.__power/2);
            result.add(new Action(ActionEnum.A_ADD, newAnimalPosition, 0, newAnimal));
        }
        return result;
    }

    public ArrayList<Position> getNeighboringPositions(){
        return this.__world.getNeighboringPositions(this.getPosition());
    }

    public ArrayList<Position> getNeighboringBirthPositions(){
        return this.__world.filterFreePositions(this.__world.getNeighboringPositions(this.getPosition()));
    }
}
