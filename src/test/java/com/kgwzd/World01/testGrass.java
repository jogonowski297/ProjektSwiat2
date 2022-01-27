package com.kgwzd.World01;


import org.junit.Test;
import com.kgwzd.World01.*;
import com.kgwzd.World01.Organisms.*;

import static org.junit.Assert.assertEquals;

public class testGrass {
    @Test
    public void addGrass(){
        World javaWorld = new World(5,5);
        Organism newOrg;

        newOrg = new Grass(null, new Position(null, 3, 3), javaWorld);
        javaWorld.addOrganism(newOrg);

        System.out.println(javaWorld.print());
    }

    @Test
    public void grassLiveLengh(){
        World javaWorld = new World(5,5);
        Organism newOrg;

        newOrg = new Grass(null, new Position(null, 3, 3), javaWorld);
        javaWorld.addOrganism(newOrg);

        for(int i=0; i<=5; i++){
            javaWorld.makeTurn();
        }
        Organism firstPositionGrass = javaWorld.getOrganismFromPosition(newOrg.getPosition());

        assertEquals("Czy pierwasz dodana trawa dalej istnieje", null, firstPositionGrass);
    }


}
