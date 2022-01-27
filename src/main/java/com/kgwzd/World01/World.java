package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Animal;
import com.kgwzd.World01.Organisms.Organism;
import com.kgwzd.World01.Organisms.Plant;
import com.kgwzd.World01.Obserwator.*;
import java.util.ArrayList;
import java.util.Objects;

public class World implements Subject {
    int __worldX;
    int __worldY;
    int __turn;
    int __turnStop;
    ArrayList<Organism> __organisms;
    ArrayList<Organism> __organismsToRemove;
    ArrayList<Organism> __newOrganisms;
    ArrayList<Organism> __newOrganismsToRemove;
    ArrayList<Position> __positionsWithStop;
    Organism copyAlien;
    boolean orgToRemove;
    private ArrayList<Observer> observlist;
    boolean alienLive;
    char __separator;

    public World(int worldx, int worldy){
        __worldX = worldx;
        __worldY = worldy;
        __turn=0;
        __turnStop=0;
        __organisms = new ArrayList<Organism>();
        __organismsToRemove = new ArrayList<Organism>();
        __newOrganisms = new ArrayList<Organism>();
        __newOrganismsToRemove = new ArrayList<Organism>();
        __positionsWithStop = new ArrayList<Position>();
        orgToRemove = false;
        copyAlien = null;
        alienLive = false;
        __separator = '.';
        observlist = new ArrayList<Observer>();
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

    public ArrayList<Position> getPositionsWithStop(){
        return this.__positionsWithStop;
    }

    public void setPositionsWithStop(ArrayList<Position> positions){
        this.__positionsWithStop = positions;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public void makeTurn(){
        ArrayList<Action> actions;
        ArrayList<Organism> copyOrganisms = new ArrayList<>(this.__organisms);

        for ( Organism org : copyOrganisms){
            if(this.__turnStop >= 0 && this.isIn(org, this.getPositionsWithStop())){
                System.out.println(org.getSign() + " : Zatrzymany na: " + this.__turnStop + " rundy");
                continue;
            }
            if (this.positiononBoard(org.getPosition())){
                actions = org.move();
                for(Action a: actions){
                    this.makeMove(a);
                }
                actions.removeAll(actions);
                if(this.positiononBoard(org.getPosition())){
                    actions = org.action();
                    for (Action a : actions){
                        this.makeMove(a);
                    }
                    actions.removeAll(actions);
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
                notifyObservers(o.getClass().getSimpleName(),o.getPosition().print());
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
        this.__turnStop -= 1;

//        for(Organism o : this.__organisms){
//            if(o.getSign() == 'A'){
//                this.copyAlien = o;
//                this.__organisms.remove(o);
//                break;
//            }
//        }
//        if(this.copyAlien != null) {
//            this.addOrganism(copyAlien);
//        }
    }

//    public void makeMove(Action action){
//        System.out.println(action.print());
//        if(action.get__action() == ActionEnum.A_ADD){
//            this.__newOrganisms.add(action.get__organism());
//        }
//        else if (action.get__action() == ActionEnum.A_INCREASEPOWER){
//            action.__organism.setPower(action.__organism.getPower()+action.get__value());
//        }
//        else if (action.get__action() == ActionEnum.A_MOVE){
//            action.__organism.setPosition(action.__position);
//        }
//        else if (action.get__action() == ActionEnum.A_REMOVE){
//            action.__organism.setPosition(new Position(action.__organism.getPosition(), -1,-1));
//        }
//        else if (action.get__action() == ActionEnum.A_STOP){
//            this.__turnStop = 3;
//        }
//    }

    public void makeMove(Action action){
        System.out.println(action.print());
        switch (action.get__action()) {
            case A_ADD -> this.__newOrganisms.add(action.get__organism());
            case A_INCREASEPOWER -> action.__organism.setPower(action.__organism.getPower() + action.get__value());
            case A_MOVE -> action.__organism.setPosition(action.__position);
            case A_REMOVE -> action.__organism.setPosition(new Position(action.__organism.getPosition(), -1, -1));
            case A_STOP -> this.__turnStop = 3;
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

    public void removeAlien(){
        if(this.getAlienPosition() != null && this.positiononBoard(getAlienPosition())){
            for (Organism o: this.__organisms){
                if(o == this.getAlienOrganism()){
                    System.out.println("Alien has been removed from: "+ this.getAlienOrganism().getPosition().print());
                    o.setPosition(new Position(o.getPosition(), -1,-1));
                    break;
                }
            }
            this.__organisms.remove(this.getAlienOrganism());

        }
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

    public ArrayList<Position> getFreePositions(){
        ArrayList<Position> result = new ArrayList<Position>();
        for(int wY = 0; wY <= this.__worldY-1; wY++){
            for (int wX = 0; wX <= this.__worldX-1; wX++){
                Organism org = this.getOrganismFromPosition(new Position(null,wX,wY));
                if(org == null){
                    result.add(new Position(null, wX,wY));
                }
            }
        }

        return result;
    }

    public ArrayList<Position> getTwoNeigthboringPostions(Position position){
        ArrayList<Position> result = new ArrayList<Position>();
        Position pomPosition;

        for (int y=-2; y<3; y++){
            for(int x=-2; x<3; x++){
                pomPosition = new Position(null,position.getX() + x, position.getY() + y);
                if(this.positiononBoard(pomPosition) && !(y == 0 && x == 0)){
                    result.add(pomPosition);
                }
            }
        }
        return result;
    }

    public void stopWorld(ArrayList<Position> positions){
        ArrayList<Position> result = new ArrayList<Position>();
        for(Position postion: positions){
            if(postion != null){
                this.__positionsWithStop.add(postion);
            }
        }
    }



    private boolean isIn(Organism org, ArrayList<Position> positions){
        for(Position p : positions){
            if(Objects.equals(org.getPosition().print(), p.print())){
                return true;
            }
        }
        return false;
    }

    public Organism getAlienOrganism() {
        Organism alienOrganism = null;
        for (int wY = 0; wY <= this.__worldY - 1; wY++) {
            for (int wX = 0; wX <= this.__worldX - 1; wX++) {
                Organism org = this.getOrganismFromPosition(new Position(null, wX, wY));
                if (org != null && org.getSign() == 'A') {
                    alienOrganism = org;
                }
            }
        }
        return alienOrganism;
    }
    public Position getAlienPosition() {
        Position alienPosition = null;
        for (int wY = 0; wY <= this.__worldY - 1; wY++) {
            for (int wX = 0; wX <= this.__worldX - 1; wX++) {
                Organism org = this.getOrganismFromPosition(new Position(null, wX, wY));
                if (org != null && org.getSign() == 'A') {
                    alienPosition = org.getPosition();
                }
            }
        }
        return alienPosition;
    }


    public String print(){
        String result = "\nturn: " + String.valueOf(this.__turn) + "\n";
        Organism org = null;
        for (int wY = 0; wY <= this.__worldY-1; wY++){
            for (int wX = 0; wX <= this.__worldX-1; wX++) {
                org = this.getOrganismFromPosition(new Position(null, wX, wY));
                if (org != null) {
                    if(org.getSign() == 'A'){
                        result += ANSI_YELLOW + "[" + ANSI_RESET + ANSI_YELLOW + org.getSign() + ANSI_RESET + ANSI_YELLOW + "]" + ANSI_RESET;
                    }
                    if(org.getSign() == 'D'){
                        result += "[" + ANSI_RED + org.getSign() + ANSI_RESET + "]";
                    }
                    if(org.getSign() == 'G'){
                        result += "[" + ANSI_GREEN + org.getSign() + ANSI_RESET + "]";
                    }
                    if(org.getSign() == 'W'){
                        result += "[" + ANSI_BLACK + org.getSign() + ANSI_RESET + "]";
                    }
                    if(org.getSign() == 'S'){
                        result += "[" + ANSI_CYAN + org.getSign() + ANSI_RESET + "]";
                    }
                    if(org.getSign() == 'T'){
                        result += "[" + ANSI_PURPLE + org.getSign() + ANSI_RESET + "]";
                    }
                } else {
                    result += "[ ]";
                }
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void register(Observer o){
        observlist.add(o);
        System.out.println("register");
    }

    @Override
    public void notifyObservers(String name, String position){
        for(Observer o: observlist){
            o.death(name, position);
        }
    }

}
