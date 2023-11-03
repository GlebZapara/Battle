package com.badlogic.drop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Hammer");
		config.setWindowIcon("free-icon-thor-hammer-9650896.png");
		config.setWindowedMode(1920, 1080);
		new Lwjgl3Application(new scene2dui(), config);
	}
}

