package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        World javaWorld = new World(8,8);
        Organism newOrg;

        newOrg = new Grass(null, new Position(null, 4,0), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Dandelion(null, new Position(null, 0,4), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(null, new Position(null, 0,0), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Wolf(null, new Position(null, 7,7), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Toadstool(null, new Position(null, 4,4), javaWorld);
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