package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
    Player1 player1;
    final Starter game;
    OrthographicCamera camera;
     SpriteBatch batch;
     BitmapFont font;
     Texture backgroundTexture;

    public MainMenuScreen(Starter gam) {
        this.game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Razer.fnt"));
        Gdx.graphics.setForegroundFPS(170);
        Gdx.graphics.setVSync(true);
        backgroundTexture = new Texture(Gdx.files.internal("Lobby.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(game.batch, "START", 600, 356);

        if (player1 != null) {
            player1.render(game.batch);
        }

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        backgroundTexture.dispose();
        font.dispose();
        batch.dispose();
    }
}