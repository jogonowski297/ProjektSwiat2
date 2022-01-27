package com.kgwzd.World01;


import org.junit.Test;
import com.kgwzd.World01.*;
import com.kgwzd.World01.Organisms.*;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class testAlien {

    //Sprawdza czy owca zatrzyma sie na 2 rundy
    @Test
    public void testAlienStopSheep(){
        World javaWorld = new World(3,3);

        Organism sheep = new Sheep(null, new Position(null, 1, 1), javaWorld);
        javaWorld.addOrganism(sheep);

        Organism alien = new Alien(null, new Position(null, 2, 2), javaWorld);
        javaWorld.addOrganism(alien);

        int stop1 = 0;
        int stop2 = 0;


        for(int i=0; i<=4;i++){
            if(i==1){
                stop1=javaWorld.__turnStop;
            }
            if(i==3){
                stop2=javaWorld.__turnStop;
            }
            System.out.println("\nTurn: " + (i+1));
            javaWorld.makeTurn();
            System.out.println(javaWorld.print());
            if(i==0){
                javaWorld.removeAlien();
            }
        }

        assertEquals("Czy suma zatrzymanych rund jest rowna 2", 2, stop2+stop1);

    }


    // Sprawdza czy Alien wybrał jedna z sasiednich pozycji
    @Test
    public void testAlienMove(){
        World javaWorld = new World(3,3);

        Organism alien = new Alien(null, new Position(null, 2, 2), javaWorld);
        javaWorld.addOrganism(alien);
        boolean ifTruePosition = false;

        for(int i = 0; i<=1; i++){
            javaWorld.makeTurn();
            Position alienPosition = javaWorld.getAlienOrganism().getPosition();
            javaWorld.makeTurn();
            Position alienPosition2 = javaWorld.getAlienOrganism().getPosition();
            for(Position p : javaWorld.getTwoNeigthboringPostions(alienPosition)){
                if(Objects.equals(alienPosition2.print(), p.print())){
                    ifTruePosition = true;
                    break;
                }
            }
        }
        assertEquals("Czy alien porusza sie o jedno pole", true, ifTruePosition);
    }
}
