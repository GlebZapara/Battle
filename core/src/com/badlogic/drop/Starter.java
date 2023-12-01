package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Starter extends Game {
    SpriteBatch batch;
    BitmapFont font;
    private ServerSocket serverSocket;

    public void create() {
        if (isAppAlreadyRunning(this)) {
            this.setScreen(new ErrorScreen(this));
        } else {
            new Thread(this::startServer).start();
            this.setScreen(new MainMenuScreen(this));
        }
        cursor();
    }

    public void render() {
        super.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }

        fullScreen();
    }

    public void dispose() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void fullScreen() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);
    }

    public void cursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("Cursor.png"));
        int xHotspot = 0, yHotspot = 0;
        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        pixmap.dispose();
        Gdx.graphics.setCursor(cursor);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Font8.fnt"));
    }

    private boolean isAppAlreadyRunning(Starter game) {
        try (Socket socket = new Socket("localhost", 12345)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();

                socket.getOutputStream().write("NewInstance".getBytes());

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
