package com.kgwzd.World01;

import com.kgwzd.World01.Obserwator.Observer;
import com.kgwzd.World01.Organisms.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int worldXY = 8;
        boolean bool;
        int tour = 0;
        ArrayList<Position> freePositions;
        Organism newOrg;
        Gamer gamer = new Gamer();

        World javaWorld = new World(worldXY, worldXY);
        javaWorld.register(gamer);

        newOrg = new Grass(null, new Position(null, 4, 0), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Dandelion(null, new Position(null, 0, 4), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(null, new Position(null, 0, 0), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Wolf(null, new Position(null, 7, 7), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Toadstool(null, new Position(null, 4, 4), javaWorld);
        javaWorld.addOrganism(newOrg);

        System.out.println(javaWorld.print());

        Scanner input = new Scanner(System.in);
        for (int i = 0; i <= 50; i++) {
            input.nextLine();
            if(javaWorld.getAlienOrganism() == null){
                tour = 0;
            }
            bool = new Random().nextBoolean();
            if (bool == true && javaWorld.alienLive == false && tour == 0) {
                tour += 1;
                freePositions = javaWorld.getFreePositions();
                Position position = freePositions.get(new Random().nextInt(freePositions.size()));
                newOrg = new Alien(null, new Position(null, position.__x, position.__y), javaWorld);
                javaWorld.alienLive = true;
                javaWorld.addOrganism(newOrg);
                System.out.println("Alien has beed added to position: " + position.print());
            }

            javaWorld.makeTurn();

            bool = new Random().nextBoolean();
            if(bool == true && javaWorld.alienLive == true && tour >= 2){
                tour = 0;
                javaWorld.alienLive = false;
                javaWorld.removeAlien();
            }
            System.out.println(javaWorld.print());
            tour += 1;
        }

    }


}