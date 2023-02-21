package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Pool;


public class CircleCollider implements Component, Pool.Poolable {
    public final Circle bounds = new Circle(0, 0, 0);


    @Override
    public void reset() {
        bounds.x = 0;
        bounds.y = 0;
        bounds.radius = 0;
    }
}
