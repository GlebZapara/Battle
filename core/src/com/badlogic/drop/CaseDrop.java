package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;

import java.util.Random;

public class CaseDrop extends ApplicationAdapter {
    Random random = new Random();
    public int coins = 1000;

    public void create() {
        codeDrop();
    }

    public void render() {
        System.out.println("Current coins: " + coins);
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }

    public int codeDrop() {
        if (coins >= 1000) {
            double dropValue = drop();
            coins -= 1000;
            System.out.println("You got a drop: " + dropValue);
        } else {
            System.out.println("YOU ARE LACKING COINS!!!");
        }

        return coins;
    }

    public double drop() {
        int drop = random.nextInt(100) + 1;
        return drop;
    }
}
