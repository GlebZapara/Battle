package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ErrorScreen extends ScreenAdapter {
    final Game game;
    Stage stage;

    public ErrorScreen(Starter gam) {
        this.game = gam;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        showErrorDialog();
    }

    private void showErrorDialog() {
        final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        final Dialog dialog = new Dialog("Game Instance Warning", skin);
        dialog.text("Another instance of the game is already running.");
        TextButton exitButton = new TextButton("Exit", skin);
        dialog.button(exitButton, true);
        dialog.setMovable(false);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        stage.addActor(dialog);
        Gdx.input.setInputProcessor(stage);
        dialog.key(com.badlogic.gdx.Input.Keys.ENTER, true);
        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
