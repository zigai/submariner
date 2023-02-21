package com.zigai.submariner;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zigai.submariner.assets.RegionNames;
import com.badlogic.gdx.Input.Keys;

import static com.zigai.submariner.Util.seconds;

public class Config {
    public static int WORLD_WIDTH = 1600;
    public static int WORLD_HEIGHT = 900;
    public static int STARTING_LIVES = 3;
    public static int MAX_LIVES = 5;

    public static class SUBMARINE {
        public static int HEIGHT = 128;
        public static int WIDTH = 210;
        public static final int SPEED_UP = 375;
        public static final int SPEED_DOWN = -625;
        public static final int SPEED_FORWARD = 550;
        public static final int SPEED_BACKWARD = -375;
        public static final TextureRegion TEXTURE = Assets.getTexture(RegionNames.SUBMARINE);
    }

    public static class FISH {
        public static final int SPEED = -200;
        public static int WIDTH = 64;
        public static int HEIGHT = 64;
        public static final float SPAWN_TIME = 1; // Spawn a fish every 1 second
        public static final int HIT_PENALTY = -5;
        public static final int PICKUP_REWARD = 10;
        public static final TextureRegion TEXTURE = Assets.getTexture(RegionNames.FISH);
    }

    public static class MINE {
        public static int SPEED = -150;
        public static int WIDTH = 96;
        public static int HEIGHT = 96;
        public static int HIT_REWARD = 2;
        public static final TextureRegion TEXTURE = Assets.getTexture(RegionNames.MINE);
        public static float SPAWN_TIME = 2; // Spawn a mine every 2 seconds
        public static final int DECREASE_SPAWN_TIME_EVERY_N_POINTS = 200; // Increase spawn rate if mines every 200 points
        public static final float DECREASE_SPAWN_TIME_BY = 0.25f;
        public static final float MIN_SPAWN_TIME = 0.5f;
        public static final int INCREASE_SPEED_EVERY_N_POINTS = 300; // Increase speed of mines every 300 points
        public static final int INCREASE_SPEED_BY = -100;
        public static final float MAX_SCALE = 1.5f;
        public static final float MIN_SCALE = 0.9f;

    }

    public static class HEART {
        public static int SPEED = -1200;
        public static final int SPAWN_EVERY_N_POINTS = 200; // Spawn a heart every 200 points
        public static int WIDTH = 48;
        public static int HEIGHT = 48;
        public static final int MAX_HEALTH_REWARD = 25;
        public static final TextureRegion TEXTURE = Assets.getTexture(RegionNames.HEART);
        public static int HIT_PENALTY = 50;

    }

    public static class PROJECTILE {
        public static int WIDTH = 32;
        public static int HEIGHT = 32;
        public static final long SHOOTING_COOLDOWN = seconds(0.3);
        public static final int SPEED = 800;
        public static final TextureRegion TEXTURE = Assets.getTexture(RegionNames.PROJECTILE);

    }

    public static class LAYERS {
        public static final int BACKGROUND = 0;
        public static final int FISH = 1;
        public static final int MINE = 2;
        public static final int HEART = 3;
        public static final int PROJECTILE = 4;
        public static final int SUBMARINE = 5;
        public static final int HUD = 6;
    }

    public static class CONTROLS {
        public static final int UP = Keys.W;
        public static final int DOWN = Keys.S;
        public static final int BACKWARD = Keys.A;
        public static final int FORWARD = Keys.D;
        public static final int SHOOT = Keys.SPACE;
        public static final int PAUSE = Keys.P;
        public static final int RESTART = Keys.R;
        public static final int EXIT = Keys.ESCAPE;

    }

    public static void reset() {
        MINE.SPAWN_TIME = 2;
        MINE.SPEED = -150;
    }

    private Config() {
    }
}
