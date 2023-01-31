package com.zigai.submariner;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Logger;
import com.zigai.submariner.Config.FISH;
import com.zigai.submariner.Config.MINE;
import com.zigai.submariner.Config.HEART;
import com.zigai.submariner.screens.GameScreen;
import com.zigai.submariner.systems.EntityFactory;
import com.zigai.submariner.systems.MineSpawnSystem;

public class GameManager {
    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), Logger.DEBUG);

    private boolean paused = false;
    private boolean debug = true;
    private int score;
    private int lives;
    private final Preferences PREFS;
    public static GameManager INSTANCE = new GameManager();
    private int nextHearthSpawn;
    private int nextMineSpawnRateIncrease;
    private int nextMineSpeedIncrease;
    PooledEngine engine;
    EntityFactory entityFactory;

    private GameManager() {
        PREFS = Gdx.app.getPreferences(SubmarinerGame.class.getSimpleName());
        score = 0;
        lives = Config.STARTING_LIVES;
        nextHearthSpawn = HEART.SPAWN_EVERY_N_POINTS;
        nextMineSpawnRateIncrease = MINE.DECREASE_SPAWN_TIME_EVERY_N_POINTS;
        nextMineSpeedIncrease = MINE.INCREASE_SPEED_EVERY_N_POINTS;
    }


    public void addEngine(PooledEngine engine) {
        this.engine = engine;
        this.entityFactory = engine.getSystem(EntityFactory.class);
    }

    private void updateScore(int change) {
        score += change;
        if (score < 0) {
            score = 0;
        }
    }

    private void updateScoreAndCheck(int change) {
        updateScore(change);
        if (shouldSpawnHeart()) {
            entityFactory.createHeart();
            nextHearthSpawn += HEART.SPAWN_EVERY_N_POINTS;
            log.info("Spawned heart");
        }
        if (shouldIncreaseMineSpawnRate()) {
            MINE.SPAWN_TIME -= MINE.DECREASE_SPAWN_TIME_BY;
            if (MINE.SPAWN_TIME < MINE.MIN_SPAWN_TIME) {
                MINE.SPAWN_TIME = MINE.MIN_SPAWN_TIME;
            }
            engine.addSystem(new MineSpawnSystem(MINE.SPAWN_TIME));
            log.info("Mine spawn rate updated to: " + MINE.SPAWN_TIME);
            nextMineSpawnRateIncrease += MINE.DECREASE_SPAWN_TIME_EVERY_N_POINTS;
        }
        if (shouldIncreaseMineSpeed()) {
            MINE.SPEED += MINE.INCREASE_SPEED_BY;
            nextMineSpeedIncrease += MINE.INCREASE_SPEED_EVERY_N_POINTS;
            log.info("Mine speed updated to: " + MINE.SPEED);
        }
    }

    public void onProjectileMineCollision() {
        updateScore(MINE.HIT_REWARD);
    }

    public void onProjectileFishCollision() {
        updateScore(FISH.HIT_PENALTY);
    }

    public void onProjectileHeartCollision() {
        updateScore(HEART.HIT_PENALTY);
    }

    public void onFishCollision() {
        updateScoreAndCheck(FISH.PICKUP_REWARD);
    }

    public void onMineCollision() {
        lives -= 1;
    }

    public void onHeartCollision() {
        if (lives == Config.MAX_LIVES) {
            updateScoreAndCheck(HEART.MAX_HEALTH_REWARD);
        } else {
            lives += 1;
        }
    }

    public void updateHighScore() {
        if (getBestResult() < score) {
            PREFS.putInteger("HIGHSCORE", score);
            PREFS.flush();
        }
    }

    public int getBestResult() {
        return PREFS.getInteger("HIGHSCORE", 0);
    }


    public void reset() {
        INSTANCE = new GameManager();
    }

    public boolean shouldSpawnHeart() {
        return score >= nextHearthSpawn;
    }

    public boolean shouldIncreaseMineSpawnRate() {
        return score >= nextMineSpawnRateIncrease;
    }

    public boolean shouldIncreaseMineSpeed() {
        return score >= nextMineSpeedIncrease;
    }


    public boolean isDebugMode() {
        return debug;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return lives == 0;
    }

    public void togglePause() {
        paused = !paused;
    }

    public void toggleDebugMode() {
        debug = !debug;
    }
}
