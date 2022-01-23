package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Animal;
import com.kgwzd.World01.Organisms.Organism;
import com.kgwzd.World01.Organisms.Plant;
import com.kgwzd.World01.Organisms.Wolf;

import java.util.ArrayList;

public class World {
    int __worldX;
    int __worldY;
    int __turn=0;
    ArrayList<Organism> __organisms = new ArrayList<Organism>();
    ArrayList<Organism> __organismsToRemove = new ArrayList<Organism>();
    ArrayList<Organism> __newOrganisms = new ArrayList<Organism>();
    ArrayList<Organism> __newOrganismsToRemove = new ArrayList<Organism>();
    char __separator = '.';
    World __worldCopy;

    public World(int worldx, int worldy){
        __worldX = worldx;
        __worldY = worldy;
    }

    public int get__worldX(){
        return __worldX;
    }

    public int get__worldY(){
        return __worldY;
    }

    public int get__turn(){
        return __turn;
    }

    public void set__turn(int value){
        __turn = value;
    }

    public ArrayList<Organism> get__organisms(){
        return __organisms;
    }

    public void set_organisms(ArrayList<Organism> value){
        __organisms = value;
    }

    public ArrayList<Organism> get__newOrganisms(){
        return __newOrganisms;
    }

    public void set__newOrganisms(ArrayList<Organism> value){
        __newOrganisms = value;
    }

    public char get__separator(){
        return __separator;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[32m";

    public void makeTurn(){
        ArrayList<Action> actions;
        for ( Organism org : this.__organisms){
            if (this.positiononBoard(org.getPosition())){
                actions = org.move();
                for(Action a: actions){
                    this.makeMove(a);
                }
                actions.clear();
                if(this.positiononBoard(org.getPosition())){
                    actions = org.action();
                    for (Action a : actions){
                        this.makeMove(a);

                    }
                    actions.clear();
                }
            }
        }

        for ( Organism o : this.__organisms){
            if(!this.positiononBoard(o.getPosition())){
                this.__organisms.remove(o);
            }
        }

        for( Organism o : this.__organisms){
            o.setLiveLength(o.getLiveLength()-1);
            o.setPower(o.getPower()+1);
            if(o.getLiveLength() < 1){
                System.out.println(o.getClass().getSimpleName() + ": died of old age at: " + o.getPosition().print());
                this.__organismsToRemove.add(o);
            }
        }
        this.__organisms.removeAll(this.__organismsToRemove);

        for(Organism o : this.__newOrganisms){
            if(!this.positiononBoard(o.getPosition())){
                this.__newOrganismsToRemove.add(o);
            }
        }
        this.__newOrganisms.removeAll(this.__newOrganismsToRemove);

        this.__organisms.addAll(this.get__newOrganisms());
        this.__newOrganisms.clear();
        this.set__turn(this.get__turn()+1);

    }

    public void makeMove(Action action){
        System.out.println(action.print());
        if(action.get__action() == ActionEnum.A_ADD){
            this.__newOrganisms.add(action.get__organism());
        }
        else if (action.get__action() == ActionEnum.A_INCREASEPOWER){
            action.__organism.setPower(action.__organism.getPower()+action.get__value());
        }
        else if (action.get__action() == ActionEnum.A_MOVE){
            action.__organism.setPosition(action.__position);
        }
        else if (action.get__action() == ActionEnum.A_REMOVE){
            action.__organism.setPosition(new Position(action.__organism.getPosition(), -1,-1));
        }
    }

    public boolean addOrganism(Organism newOrganism){
        Position newOrgPosition = new Position(null, newOrganism.getPosition().__x, newOrganism.getPosition().__y);

        if(this.positiononBoard(newOrgPosition)){
            this.__organisms.add(newOrganism);
            return true;
        }
        return false;
    }

    public boolean positiononBoard(Position position){
        return (position.__x >=0 && position.__y >=0) && (position.__x < this.__worldX && position.__y < this.__worldY);
    }

    public Organism getOrganismFromPosition(Position position){
        Organism pomOrganism = null;
        for(Organism org : this.__organisms){
            if(org.getPosition().print().equals(position.print())){
                pomOrganism = org;
                break;
            }
        }
        if (pomOrganism == null){
            for (Organism org : this.__newOrganisms){
                if(org.getPosition() == position){
                    pomOrganism = org;
                    break;
                }
            }
        }
        return pomOrganism;
    }

    public ArrayList<Position> getNeighboringPositions(Position position){

        ArrayList<Position> result = new ArrayList<Position>();
        Position pomPosition;

        for (int y=-1; y<2; y++){
            for(int x=-1; x<2; x++){
                pomPosition = new Position(null,position.getX() + x, position.getY() + y);
                if(this.positiononBoard(pomPosition) && !(y == 0 && x == 0)){
                    result.add(pomPosition);
                }
            }
        }
        return result;
    }

    public ArrayList<Position> filterFreePositions( ArrayList<Position> fields){
        ArrayList<Position> result = new ArrayList<Position>();

        for (Position field : fields){
            if(this.getOrganismFromPosition(field) == null){
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> filterPositionsWithoutAnimals(ArrayList<Position> fields){
        ArrayList<Position> result = new ArrayList<Position>();
        Organism pomOrg = null;

        for (Position field : fields){
            pomOrg = this.getOrganismFromPosition(field);
            if ((pomOrg == null) || (pomOrg instanceof Plant)){
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> filterPositionsWithOtherSpecies(ArrayList<Position> fields, Animal species){
        ArrayList<Position> result = new ArrayList<Position>();
        Organism pomOrg;

        for(Position field : fields){
            pomOrg = this.getOrganismFromPosition(field);
            if(!(pomOrg instanceof Animal)){
                result.add(field);
            }
        }
        return result;
    }

    public String print(){
        String result = "\nturn: " + String.valueOf(this.__turn) + "\n";
        Organism org = null;
        for (int wY = 0; wY <= this.__worldY-1; wY++){
            for (int wX = 0; wX <= this.__worldX-1; wX++) {
                org = this.getOrganismFromPosition(new Position(null, wX, wY));
                if (org != null) {
                    result += "[" + ANSI_YELLOW + org.getSign() + ANSI_RESET + "]";
                } else {
                    result += "[" + this.get__separator() + "]";
                }
            }
            result += "\n";
        }
        return result;
    }
}
