package com.kgwzd.World01;

import org.junit.Test;
import com.kgwzd.World01.*;
import com.kgwzd.World01.Organisms.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testWolf {
    @Test
    public void testWolfKillSheep() {
        World javaWorld = new World(3, 1);

        Organism sheep = new Sheep(null, new Position(null, 0, 0), javaWorld);
        javaWorld.addOrganism(sheep);

        Organism wolf = new Wolf(null, new Position(null, 2, 0), javaWorld);
        javaWorld.addOrganism(wolf);

        System.out.println(javaWorld.print());

        for(int i=0;i<=2;i++){
            javaWorld.makeTurn();
            System.out.println(javaWorld.print());
        }

        ArrayList<Organism> organismArray = javaWorld.get__organisms();
        assertEquals("Czy owca dalej istnieje w ogranizmach", 1, organismArray.size());
        assertEquals("Czy owca dalej istnieje w ogranizmach", 'W', organismArray.get(0).getSign());

    }
}
