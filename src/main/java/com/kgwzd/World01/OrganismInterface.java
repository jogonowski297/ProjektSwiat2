package com.kgwzd.World01;

import com.kgwzd.World01.Organisms.Organism;

import java.util.ArrayList;

public interface OrganismInterface {

    ArrayList<Action> move();

    ArrayList<Action> action();

    void initParams();

    Organism clone();
}
