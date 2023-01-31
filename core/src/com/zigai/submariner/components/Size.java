package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Size implements Component, Pool.Poolable {
    public float width;
    public float height;

    @Override
    public void reset() {
        width = 1;
        height = 1;
    }
}
