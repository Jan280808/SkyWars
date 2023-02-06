package org.example.skywars.gamemanager.countdown;

public abstract class Countdown {

    protected int taskID;

    public Countdown() {}

    public abstract void start();

    public abstract void stop();
}
