package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Organism;
import com.kgwzd.World01.Organisms.Plant;

import java.util.ArrayList;

public class World {
    int __worldX;
    int __worldY;
    int __turn=0;
    ArrayList<Organism> __organisms = new ArrayList<Organism>();
    ArrayList<Organism> __newOrganisms = new ArrayList<Organism>();
    char __separator = '.';

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

    public int get__separator(){
        return __separator;
    }

    public void makeTurn(){
        ArrayList<Action> actions = new ArrayList<>();

        for ( Organism org : this.__organisms){
            if (this.positiononBoard(org.getPosition())){
                actions = org.move();
                for(Action a:actions){
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
        this.__organisms.clear();
        for(Organism o : get__organisms()){
            if(this.positiononBoard(o.getPosition())){
                this.__organisms.add(o);
            }
        }
        for( Organism o : this.get__organisms()){
            o.setLiveLength(o.getLiveLength()-1);
            o.setPower(o.getPower()-1);
            if(o.getLiveLength() < 1){
                System.out.print(this.getClass().getSimpleName() + ": died of old age at: " + String.valueOf(o.getPosition()));
            }
        }
        this.__organisms.clear();
        for(Organism o : get__organisms()){
            if(o.getLiveLength() > 0){
                this.__organisms.add(o);
            }
        }

        for(Organism o : get__newOrganisms()){
            if(this.positiononBoard(o.getPosition())){
                this.__organisms.add(o);
            }
        }

        this.__organisms.addAll(this.get__newOrganisms());
        this.__newOrganisms.clear();
        this.set__turn(this.get__turn()+1);

    }

    public void makeMove(Action action){
        System.out.println(action);
        if(action.get__action() == ActionEnum.A_ADD){
            this.__newOrganisms.add(action.get__organism());
        }
        else if (action.get__action() == ActionEnum.A_INCREASEPOWER){
            action.__organism.setPower(action.__organism.getPower()+action.get__value());
        }
        else if (action.get__action() == ActionEnum.A_MOVE){
            action.__organism.setPosition(action.get__position());
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
        return position.__x >=0 && position.__y >=0 && position.__x < this.__worldX && position.__y < this.__worldY;
    }

    public Organism getOrganismFromPosition(Position position){
        Organism pomOrganism = null;

        for(Organism org:this.__organisms){
            if(org.getPosition() == position){
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
        Position pomPosition = null;

        for (int y=-1; y<=2; y++){
            for(int x=-1; x<=2; x++){
                pomPosition = new Position(null,position.__x + x, position.__y + y);
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

    public String print(){
        StringBuilder result = new StringBuilder("\nturn: " + String.valueOf(this.__turn) + "\n");

        for (int wY = 0; wY<=this.__worldY; wY++){
            for (int wX = 0; wX <= this.__worldX; wX++) {
                Organism org = this.getOrganismFromPosition(new Position(null, wX, wY));
                if (org != null) {
                    result.append(org.getSign());
                } else {
                    result.append(this.get__separator());
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
