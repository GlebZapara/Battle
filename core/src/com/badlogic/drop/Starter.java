package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Starter extends Game {
    SpriteBatch batch;
    BitmapFont font;


    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Font8.fnt"));
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        fullScreen();
    }

    public void dispose() {
    }

    void fullScreen() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);
    }


}