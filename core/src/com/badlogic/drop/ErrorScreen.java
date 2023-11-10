package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ErrorScreen extends ScreenAdapter {
    final Game game;
    Stage stage;

    public ErrorScreen(Starter gam) {
        this.game = gam;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        showErrorDialog();
    }

    private void showErrorDialog() {
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Dialog dialog = new Dialog("Game Instance Warning", skin);
        dialog.text("Another instance of the game is already running.");
        dialog.button("Exit", true);
        dialog.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (event.getListenerActor().getName().equals("Exit")) {
                    Gdx.app.exit();
                }
            }
        });
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