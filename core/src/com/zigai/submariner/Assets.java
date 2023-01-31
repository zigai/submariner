package com.zigai.submariner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Logger;
import com.zigai.submariner.assets.AssetDescriptors;


public class Assets {
    public static AssetManager assetManager;
    private static TextureAtlas gameplayAtlas;
    public static ParticleEffect explosion;

    public static void load() {
        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);
        assetManager.load(AssetDescriptors.FONT32);
        assetManager.load(AssetDescriptors.FONT64);
        assetManager.load(AssetDescriptors.GAMEPLAY);
        assetManager.load(AssetDescriptors.PICKUP_SOUND);
        assetManager.load(AssetDescriptors.EXPLOSION_SOUND);
        assetManager.finishLoading();
        gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        explosion = new ParticleEffect();
        explosion.load(Gdx.files.internal("explosion"), Gdx.files.internal(""));
    }

    public static void dispose() {
        assetManager.dispose();
        explosion.dispose();
    }

    public static TextureRegion getTexture(String name) {
        return gameplayAtlas.findRegion(name);
    }


    private Assets() {
    }
}
