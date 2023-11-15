package org.example;

public enum type {
    PREMIUM(3), STANDARD(1.5), ECONOMY(1);

    private final double multiplayer;

    type(double multiplayer) {
        this.multiplayer = multiplayer;
    }

    public double getMultiplayer(){
        return this.multiplayer;
    }
}