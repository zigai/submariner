package com.zigai.submariner;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(Config.WORLD_WIDTH, Config.WORLD_HEIGHT);
        config.setForegroundFPS(60);
        config.setTitle("Submariner");
        new Lwjgl3Application(new SubmarinerGame(), config);
    }
}
