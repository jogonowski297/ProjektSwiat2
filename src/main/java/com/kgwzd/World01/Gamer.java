package com.kgwzd.World01;
import com.kgwzd.World01.Obserwator.*;

public class Gamer implements Observer{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    public void death(String name, String position){
        System.out.println(ANSI_RED + name + ": died of OLD age at: " + position + ANSI_RESET);
    }
}
