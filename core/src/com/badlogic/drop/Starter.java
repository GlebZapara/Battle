package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;
import java.awt.Font;
import java.util.Random;


public class Starter extends ApplicationAdapter {
    SpriteBatch batch;
    Player1 player1;
    Player2 player2;
    Random random;
    BitmapFont font;
    Sound sound;
    Sound sound2;
    Music music;
    int totalDamage1;
    int totalDamage2;
    float damageTime1;
    float damageTime2;
    float healthTime1;
    float healthTime2;
    float armorTime1;
    float armorTime2;
    private float screenDelayTime = 0;
    private final float SCREEN_DELAY_DURATION = 1f;
    private boolean gameStarter = false;
    private boolean player1Wins = false;
    private boolean player2Wins = false;
    private Texture backgroundTexture;
    private Texture backgroundTexture1;
    private Texture backgroundTexture2;

    public void create() {
        batch = new SpriteBatch();
        random = new Random();
        font = new BitmapFont(Gdx.files.internal("Font8.fnt"));
        font.getData().setScale(0.5F);
        player1 = new Player1(27, 0, random.nextInt(100) + 1, 1000, 100, "Player1");
        player2 = new Player2(1601, 0, random.nextInt(100) + 1, 1000, 100, "Player2");
        sound = Gdx.audio.newSound((Gdx.files.internal("sound.mp3")));
        music = Gdx.audio.newMusic((Gdx.files.internal("music.mp3")));
        sound2 = Gdx.audio.newSound((Gdx.files.internal("sound2.mp3")));
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        totalDamage1 = 0;
        totalDamage2 = 0;
        damageTime1 = 0;
        damageTime2 = 0;
        healthTime1 = 0;
        healthTime2 = 0;
        armorTime1 = 0;
        armorTime2 = 0;
        music.setVolume(0.3f);
        Gdx.graphics.setForegroundFPS(170);
        Gdx.graphics.setVSync(true);
    }

    public void render() {
        super.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player1.render(batch);
        player2.render(batch);

        if (!gameStarter) {
            font.draw(batch, "TAP TO START", Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f - 45);
        }

        if (player2.health <= 0) {
            font.setColor(0, 0, 0, healthTime2);
        } else if (healthTime2 > 0) {
            font.setColor(0, 1, 0, healthTime2);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Health: " + player2.health, 1790, 361);

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
            font.draw(batch, "Damage: " + totalDamage1, 1790, 381);
            font.setColor(1, 1, 1, 1);
        } else {
            font.setColor(1, 1, 1, 1);
            font.draw(batch, "Damage: " + totalDamage1, 1790, 381);
        }

        if (armorTime2 > 0) {
            font.setColor(0, 0, 1, armorTime2);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Armor: " + player2.armor, 1790, 341);

        if (armorTime1 > 0) {
            font.setColor(0, 0, 1, armorTime1);
        } else {
            font.setColor(1, 1, 1, 1);
        }
        font.draw(batch, "Armor: " + player1.armor, 20, 341);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            gameStarter = true;
        }

        batch.end();

        if (gameStarter) {
            music.play();

            if (player1Wins || player2Wins) {
                music.stop();
            }

            if (screenDelayTime > 0) {
                screenDelayTime -= Gdx.graphics.getDeltaTime();
                return;
            }

            if (player1.health > 0 && player2.health > 0) {
                int damage = random.nextInt(player1.attack) + 1;
                int damageDifference;

                if (player2.armor > 0) {
                    damageDifference = Math.min(player2.armor, damage);
                    player2.armor -= damageDifference;
                    totalDamage2 += damageDifference;
                    sound.play(0.3f);
                    screenDelayTime = SCREEN_DELAY_DURATION;
                    healthTime1 = 1;
                    damageTime1 = 1;
                    armorTime2 = 1;
                    System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage to armor.");
                } else {

                    damageDifference = damage;

                    if (player2.armor == 0) {
                        sound.play(0.3f);
                        healthTime1 = 1;
                        damageTime1 = 1;
                        armorTime2 = 1;
                    }
                    player2.health -= damageDifference;
                    totalDamage2 += damageDifference;
                    screenDelayTime = SCREEN_DELAY_DURATION;
                    System.out.println(player1.name + " attacks " + player2.name + " and deals " + damageDifference + " damage.");
                }

                if (player2.health <= 0) {
                    sound2.play(0.4f);
                    player1Wins = true;
                    System.out.println(player1.name + " Wins!!!");
                    return;
                } else if (player1Wins) {
                    backgroundTexture1 = new Texture(Gdx.files.internal("winner-1.png"));
                    batch.begin();
                    batch.draw(backgroundTexture1, 0, 50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.end();
                }


                damage = random.nextInt(player2.attack) + 1;

                if (player1.armor > 0) {
                    damageDifference = Math.min(player1.armor, damage);
                    player1.armor -= damageDifference;
                    totalDamage1 += damageDifference;
                    sound.play(0.3f);
                    screenDelayTime = SCREEN_DELAY_DURATION;
                    healthTime2 = 1;
                    damageTime2 = 1;
                    armorTime1 = 1;
                    System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage to armor.");
                } else {

                    damageDifference = damage;

                    if (player1.armor == 0) {
                        sound.play(0.3f);
                        healthTime2 = 1;
                        damageTime2 = 1;
                        armorTime1 = 1;
                    }
                    player1.health -= damageDifference;
                    totalDamage1 += damageDifference;
                    screenDelayTime = SCREEN_DELAY_DURATION;
                    System.out.println(player2.name + " attacks " + player1.name + " and deals " + damageDifference + " damage.");
                }

                if (player1.health <= 0) {
                    sound2.play(0.4f);
                    player2Wins = true;
                    System.out.println(player2.name + " Wins!!!");
                    return;
                } else if (player2Wins) {
                    backgroundTexture2 = new Texture(Gdx.files.internal("winner-2.png"));
                    batch.begin();
                    batch.draw(backgroundTexture2, 0, 50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    batch.end();
                }

//              if (player1.health > 0) {
//                  player1.setX(player2.getX());
//                  player1.setY(player2.getY());
//                  batch.begin();
//                  player1.render(batch);
//                  sleep(500);
//                  batch.end();
//
//                  player1.setX(player1.getInitialX());
//                  player1.setY(player1.getInitialY());
//                  batch.begin();
//                  player1.render(batch);
//                  sleep(500);
//                  batch.end();
//              }
            }
        }
        fullScreen();
    }

    public void dispose() {
        super.dispose();
        batch.dispose();
        player1.dispose();
        player2.dispose();
        font.dispose();
    }

    void fullScreen() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);
    }

//    public void attackAction(Player1 player1, Player2 player2) {
//        Stage stage = new Stage();
//        Gdx.input.setInputProcessor(stage);
//        MoveToAction moveAction1 = new MoveToAction();
//        moveAction1.setPosition(player2.getX(), player2.getY());
//        moveAction1.setDuration(500f);
//        player1.addAction(moveAction1);
//        stage.addActor(player1);
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
//
//        MoveToAction moveAction2 = new MoveToAction();
//        moveAction2.setPosition(player1.getX(), player1.getY());
//        moveAction2.setDuration(500f);
//        player1.addAction(moveAction2);
//        stage.addActor(player1);
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
//    }

//    private void sleep(int time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}