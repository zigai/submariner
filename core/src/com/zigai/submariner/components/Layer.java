package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Layer implements Component, Pool.Poolable {
    public int value = 0;

    @Override
    public void reset() {
        value = 0;
    }
}
