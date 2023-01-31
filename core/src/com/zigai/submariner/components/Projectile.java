package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;


public class Projectile implements Component, Pool.Poolable {
    public boolean hit = false;

    @Override
    public void reset() {
        this.hit = false;
    }
}
