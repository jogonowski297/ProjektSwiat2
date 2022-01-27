package com.kgwzd.World01;

import org.junit.Test;
import com.kgwzd.World01.Organisms.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testSheep {
    @Test
    public void testSheepKillGrass(){
        World javaWorld = new World(2,1);

        Organism sheep = new Sheep(null, new Position(null, 0, 0), javaWorld);
        javaWorld.addOrganism(sheep);

        Organism grass = new Grass(null, new Position(null, 1, 0), javaWorld);
        javaWorld.addOrganism(grass);

        System.out.println(javaWorld.print());

        for(int i=0; i<=2; i++){
            javaWorld.makeTurn();
            System.out.println(javaWorld.print());
        }
        ArrayList<Organism> organismArray = javaWorld.get__organisms();
        assertEquals("Czy trawa dalej istnieje w ogranizmach", 1, organismArray.size());
        assertEquals("Czy trawa dalej istnieje w ogranizmach", 'S', organismArray.get(0).getSign());
    }
}
