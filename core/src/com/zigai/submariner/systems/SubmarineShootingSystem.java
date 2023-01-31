package com.zigai.submariner.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.TimeUtils;
import com.zigai.submariner.assets.AssetDescriptors;

public class SubmarineShootingSystem extends EntitySystem {
    private final long cooldown;
    private long lastShot;

    private boolean canShoot() {
        return TimeUtils.nanoTime() - lastShot > cooldown;
    }

    public SubmarineShootingSystem(long cooldown) {
        this.cooldown = cooldown;
        lastShot = TimeUtils.nanoTime();
    }

    public void shoot() {
        if (canShoot()) {
            getEngine().getSystem(EntityFactory.class).createProjectile();
            lastShot = TimeUtils.nanoTime();
        }
    }
}
