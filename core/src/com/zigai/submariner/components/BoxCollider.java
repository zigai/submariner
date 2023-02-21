package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class BoxCollider implements Component, Pool.Poolable {
    public final Rectangle bounds = new Rectangle(0, 0, 1, 1);


    @Override
    public void reset() {
        bounds.setPosition(0, 0);
        bounds.setSize(1, 1);
    }
}
