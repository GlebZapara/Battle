package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class scene2dui extends ApplicationAdapter {
    Stage stage;
    Skin skin;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Texture buttonUpTexture = new Texture(Gdx.files.internal("my-button-up.png"));
        Texture buttonDownTexture = new Texture(Gdx.files.internal("my-button-down.png"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        final TextButton button = new TextButton("Click Me", skin, "default");
        button.setWidth(200);
        button.setHeight(50);
        final Dialog dialog = new Dialog("Click Message", skin);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
