package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player1 extends Actor {
    private final Vector2 position = new Vector2();
    private final Vector2 initialPosition = new Vector2();

    public String name;
    private final Texture texture;
    public int attack;
    public int health;
    public int armor;
    public String damage;
    private boolean shouldAppear;
    float initialXx;
    float initialYy;
    private float width;
    private float height;

    public Player1(float x, float y, float width, float height, int attack, int health, int armor, String name) {
         texture = new Texture("Зеленый.png");
         position.set(x, y);
        this.setX(x);
        this.setY(y);
        this.attack = attack;
        this.health = health;
        this.armor = armor;
        this.name = name;
        initialPosition.set(x, y);
        this.initialXx = x;
        this.initialYy = y;
        this.width = width;
        this.height = height;
    }
    public float getInitialX() {
        return initialXx;
    }

    public float getInitialY() {
        return initialYy;
    }
    public void teleportToInitialPosition() {
        position.x = initialPosition.x;
        position.y = initialPosition.y;
    }

    public boolean shouldAppear() {
        return shouldAppear;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setShouldAppear(boolean shouldAppear) {
        this.shouldAppear = shouldAppear;
    }

    public void render(Batch batch) {
        batch.draw(texture, getX(), getY(), width, height);
     }

     public void dispose() {
         texture.dispose();
     }
}
