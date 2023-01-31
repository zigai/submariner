package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Physics implements Component, Pool.Poolable {
    public Vector2 velocity;
    public Vector2 acceleration;
    public float rotationSpeed = 0;


    @Override
    public void reset() {
        this.velocity.set(0, 0);
        this.acceleration.set(0, 0);
        this.rotationSpeed = 0;
    }
}
