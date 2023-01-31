package com.zigai.submariner.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.zigai.submariner.assets.AssetDescriptors;

public class SoundSystem extends EntitySystem {

    private final Sound pickSound;
    private final Sound explosionSound;

    public SoundSystem(AssetManager assetManager) {
        setProcessing(false); // passive system
        pickSound = assetManager.get(AssetDescriptors.PICKUP_SOUND);
        explosionSound = assetManager.get(AssetDescriptors.EXPLOSION_SOUND);
    }

    public void playPickupSound() {
        pickSound.play();
    }

    public void playExplosionSound() {
        explosionSound.play();
    }
}
