package com.kgwzd.World01;

import org.junit.Test;
import com.kgwzd.World01.Organisms.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TestToadstool {
    @Test
    public void testToadstoolKillAnimal() {
        World javaWorld = new World(2,1);

        Organism toadstool = new Toadstool(null, new Position(null, 0, 0), javaWorld);
        javaWorld.addOrganism(toadstool);

        Organism sheep = new Sheep(null, new Position(null, 1, 0), javaWorld);
        javaWorld.addOrganism(sheep);

        System.out.println(javaWorld.print());

        javaWorld.makeTurn();
        System.out.println(javaWorld.print());

        int organisms = javaWorld.get__organisms().size();

        assertEquals("Czy muchomor zabije zwierze oraz sam zniknie",0, organisms);
    }
}
