package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Organism;

import java.util.HashMap;
import java.util.Map;


public class Action {
    ActionEnum __action;
    Position __position;
    int __value;
    Organism __organism;

    public Action(ActionEnum action, Position position, int value, Organism organism){
        __action = action;
        __position = position;
        __value = value;
        __organism = organism;
    }


    public ActionEnum get__action(){
        return __action;
    }

    public Position get__position(){
        return __position;
    }

    public int get__value(){
        return __value;
    }

    public Organism get__organism(){
        return __organism;
    }

    public String print(){
        String className = this.__organism.getClass().getSimpleName();
        Map<ActionEnum,String> dictionary = new HashMap<ActionEnum,String>();
        dictionary.put(ActionEnum.A_ADD, className + ": add at: " + this.get__position().print());
        dictionary.put(ActionEnum.A_INCREASEPOWER, className + ": increase power: " + this.get__value());
        dictionary.put(ActionEnum.A_MOVE, className + ": move form: " + this.__organism.getPosition().print() + " to: " + this.get__position().print());
        dictionary.put(ActionEnum.A_REMOVE, className + ": remove form: " + this.__organism.getPosition().print());
        dictionary.put(ActionEnum.A_STOP, className + ": Zatzymał organizmy na 2 rundy: ");
        return dictionary.get(this.get__action());
    }
}
