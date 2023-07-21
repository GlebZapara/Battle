package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Player3 {
    private final Vector2 position = new Vector2();
    private boolean shouldAppear;

    public String name;
    private final Texture texture;
    public int attack;
    public int health;
    public int armor;
    public String damage;

    public Player3(float x, float y, int attack, int health, int armor, String name) {
        texture = new Texture("Зеленый.png");
        position.set(x, y);
        this.attack = attack;
        this.health = health;
        this.armor = armor;
        this.name = name;
        this.shouldAppear = true;
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }


    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public boolean shouldAppear() {
        return shouldAppear;
    }

    public void setShouldAppear(boolean shouldAppear) {
        this.shouldAppear = shouldAppear;
    }

    public void update(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void render(Batch batch) {
        if (shouldAppear) {
            batch.draw(texture, position.x, position.y);
        }
    }
}
