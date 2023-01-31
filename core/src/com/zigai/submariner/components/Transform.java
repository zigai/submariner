package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Transform implements Component, Pool.Poolable {
    public Vector2 position;
    public Vector2 scale = new Vector2(1, 1);
    public float rotation = 0;

    @Override
    public void reset() {
        position.set(0, 0);
        scale.set(1, 1);
        rotation = 0;
    }
}
