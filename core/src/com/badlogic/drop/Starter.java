package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.util.Random;

public class Starter extends ApplicationAdapter {
    SpriteBatch batch;
    Player1 player1;
    Player2 player2;
    Random random;
    BitmapFont font;
    int totalDamage1;
    int totalDamage2;
    float damageTime1;
    float damageTime2;
    float healthTime1;
    float healthTime2;
    float armorTime1;
    float armorTime2;
    float player1TeleportTime = 0;
    final float PLAYER1_TELEPORT_DELAY = 1.0f;
    private boolean gameStarted = false;

    public void create() {
        batch = new SpriteBatch();
        random = new Random();
        font = new BitmapFont();
        player1 = new Player1(27, 0, random.nextInt(100) + 1, 100, 100, "Player1");
        player2 = new Player2(1601, 0, random.nextInt(100) + 1, 100, 100, "Player2");
        totalDamage1 = 0;
        totalDamage2 = 0;
        damageTime1 = 0;
        damageTime2 = 0;
        healthTime1 = 0;
        healthTime2 = 0;
        armorTime1 = 0;
        armorTime2 = 0;
    }

    public void render() {

        super.render();
        Texture backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player1.render(batch);
        player2.render(batch);
        if (!gameStarted) {
            font.draw(batch, "TAP TO START", Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f - 45);
        }

        if (player2.health <= 0) {
            font.setColor(0, 0, 0, healthTime2);
        } else if (healthTime2 > 0) {
            font.setColor(0, 1, 0, healthTime2);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Health: " + player2.health, 1823, 361);

        if (player1.health <= 0) {
            font.setColor(0, 0, 0, healthTime1);
        } else if (healthTime1 > 0) {
            font.setColor(0, 1, 0, healthTime1);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Health: " + player1.health, 20, 361);

        if (damageTime2 >= 0) {
            font.setColor(1, 0, 0, damageTime2);
            font.draw(batch, "Damage: " + totalDamage2, 20, 381);
            font.setColor(1, 1, 1, 1);
        } else {
            font.setColor(1, 1, 1, 1);
            font.draw(batch, "Damage: " + totalDamage2, 20, 381);
        }

        if (damageTime1 >= 0) {
            font.setColor(1, 0, 0, damageTime1);
            font.draw(batch, "Damage: " + totalDamage1, 1823, 381);
            font.setColor(1, 1, 1, 1);
        } else {
            font.setColor(1, 1, 1, 1);
            font.draw(batch, "Damage: " + totalDamage1, 1823, 381);
        }

        if (armorTime2 > 0) {
            font.setColor(0, 0, 1, armorTime2);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Armor: " + player2.armor, 1823, 341);

        if (armorTime1 > 0) {
            font.setColor(0, 0, 1, armorTime1);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Armor: " + player1.armor, 20, 341);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            gameStarted = true;
        }

        batch.end();

        if (gameStarted) {
            if (player1.health > 0 && player2.health > 0) {
                int damage = random.nextInt(player1.attack) + 1;
                int damageDifference;
                if (player2.armor > 0) {
                    damageDifference = Math.min(player2.armor, damage);
                    player2.armor -= damageDifference;
                    totalDamage2 += damageDifference;
                    healthTime1 = 1;
                    damageTime1 = 1;
                    armorTime2 = 1;
                    System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage to armor.");
                } else {
                    damageDifference = damage;
                    if (player2.armor == 0) {
                        healthTime1 = 1;
                        damageTime1 = 1;
                        armorTime2 = 1;
                    }
                    player2.health -= damageDifference;
                    totalDamage2 += damageDifference;
                    System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage.");
                }
                if (player2.health <= 0) {
                    System.out.println(player1.name + " Wins!!!");
                }
                Texture backgroundTexture1 = new Texture(Gdx.files.internal("winner-1.png"));
                batch.draw(backgroundTexture1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

                damage = random.nextInt(player2.attack) + 1;
                if (player1.armor > 0) {
                    damageDifference = Math.min(player1.armor, damage);
                    player1.armor -= damageDifference;
                    totalDamage1 += damageDifference;
                    healthTime2 = 1;
                    damageTime2 = 1;
                    armorTime1 = 1;
                    System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage to armor.");
                } else {
                    damageDifference = damage;
                    if (player1.armor == 0) {
                        healthTime2 = 1;
                        damageTime2 = 1;
                        armorTime1 = 1;
                    }
                    player1.health -= damageDifference;
                    totalDamage1 += damageDifference;
                    System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage.");
                }
                if (player1.health <= 0) {
                    System.out.println(player2.name + " Wins!!!");
                }
                Texture backgroundTexture2 = new Texture(Gdx.files.internal("winner-2.png"));
                batch.draw(backgroundTexture2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

                if (player1.health > 0) {
                    player1.setX(player2.getX());
                    player1.setY(player2.getY());
                    batch.begin();
                    player1.render(batch);
                    sleep(500);
                    batch.end();

                    player1.setX(player1.getInitialX());
                    player1.setY(player1.getInitialY());
                    batch.begin();
                    player1.render(batch);
                    sleep(500);
                    batch.end();
                }
            }
        }
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        setFullscreenMode(displayMode);
    }

    public void dispose() {
        super.dispose();
        batch.dispose();
        player1.dispose();
        player2.dispose();
        font.dispose();
    }

    public void attackAction(Player1 player1, Player2 player2) {
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        MoveToAction moveAction1 = new MoveToAction();
        moveAction1.setPosition(player2.getX(), player2.getY());
        moveAction1.setDuration(500f);
        player1.addAction(moveAction1);
        stage.addActor(player1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        MoveToAction moveAction2 = new MoveToAction();
        moveAction2.setPosition(player1.getX(), player1.getY());
        moveAction2.setDuration(500f);
        player1.addAction(moveAction2);
        stage.addActor(player1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    boolean setFullscreenMode(Graphics.DisplayMode displayMode) {
        return Gdx.graphics.setFullscreenMode(displayMode);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}