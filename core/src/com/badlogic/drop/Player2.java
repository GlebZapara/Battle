package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


public class Player2 {
    private final Vector2 position = new Vector2();

    public String name;
    private final Texture texture;
    public int attack;
    public int health;
    public int armor;

    public Player2(float x, float y, int attack, int health, int armor, String name) {
        texture = new Texture("Зеленый.png");
        position.set(x, y);
        this.attack = attack;
        this.health = health;
        this.armor = armor;
        this.name = name;
    }

    public void render(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }
}
