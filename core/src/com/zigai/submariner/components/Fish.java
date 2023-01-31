package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Fish implements Component, Pool.Poolable {
    public boolean hit;


    @Override
    public void reset() {
        this.hit = false;
    }
}
