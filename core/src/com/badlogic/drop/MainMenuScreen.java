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
    private final Starter game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private BitmapFont font;
    private boolean gameStarted;

    public MainMenuScreen(final Starter game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        font = new BitmapFont();
        gameStarted = false;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (!gameStarted) {
            font.draw(batch, "TYPE TO START", 350, 240);
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) && !gameStarted) {
            gameStarted = true;
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
        batch.dispose();
        backgroundTexture.dispose();
        font.dispose();
    }
}
