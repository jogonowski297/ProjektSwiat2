package com.kgwzd.World01.Obserwator;

public interface Subject {
    void register(Observer o);
    void notifyObservers(String name, String position);
}
