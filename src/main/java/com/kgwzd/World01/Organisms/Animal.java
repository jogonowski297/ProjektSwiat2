package com.kgwzd.World01.Organisms;

import com.kgwzd.World01.Action;
import com.kgwzd.World01.ActionEnum;
import com.kgwzd.World01.Position;
import com.kgwzd.World01.World;


import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism{
    Position __lastPosition = null;

    public Animal(Organism organism, Position position, World world) {
        super(organism, position, world);
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
        ArrayList<Position> pomPositions = this.getNeighboringPositions();
        Position newPosition;

        if (pomPositions != null) {
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
        Organism newAnimal;
        ArrayList<Position> birthPositions = this.getNeighboringBirthPosition();

        if(this.ifReproduce() && !birthPositions.isEmpty()){
            Position newAnimalPosition = birthPositions.get(new Random().nextInt(birthPositions.size()));
            newAnimal = this.clone();
            newAnimal.initParams();
            newAnimal.__position = newAnimalPosition;
            this.__power = (this.__power/2);
            result.add(new Action(ActionEnum.A_ADD, newAnimalPosition, 0, newAnimal));
        }
        return result;
    }


    public ArrayList<Position> getNeighboringPositions(){
        System.out.println("This world: " + this.__world);
        return this.__world.getNeighboringPositions(this.getPosition());
    }

    public ArrayList<Position> getNeighboringBirthPosition(){
        return this.__world.filterFreePositions(this.__world.getNeighboringPositions(this.getPosition()));
    }
}
