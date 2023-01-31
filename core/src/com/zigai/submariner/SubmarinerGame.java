package com.zigai.submariner;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zigai.submariner.screens.GameScreen;

public class SubmarinerGame extends Game {
    SpriteBatch batch;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new GameScreen(this));
    }


    @Override
    public void dispose() {
        Assets.dispose();
        batch.dispose();
        super.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
