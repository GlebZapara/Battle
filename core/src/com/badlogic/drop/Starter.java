package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Starter extends Game {
    SpriteBatch batch;
    BitmapFont font;


    public void create() {
        cursor();
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

    public void cursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("Cursor.png"));
        int xHotspot = 25, yHotspot = 25;
        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        pixmap.dispose();
        Gdx.graphics.setCursor(cursor);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Font8.fnt"));
    }


}