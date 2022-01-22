package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Grass;
import com.kgwzd.World01.Organisms.Organism;
import com.kgwzd.World01.Organisms.Sheep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        World javaWorld = new World(10,10);

        Organism newOrg = null;

        newOrg = new Grass(null, new Position(null, 9,9), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Grass(null, new Position(null, 1,1), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(null, new Position(null, 2,2), javaWorld);
        javaWorld.addOrganism(newOrg);

        System.out.println(javaWorld.print());

        Scanner input = new Scanner(System.in);
        for(int i = 0; i <= 50; i++){
            input.nextLine();
            javaWorld.makeTurn();
            System.out.println(javaWorld.print());
        }

    }
}