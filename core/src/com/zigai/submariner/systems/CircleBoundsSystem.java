package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.CircleCollider;
import com.zigai.submariner.components.Size;
import com.zigai.submariner.components.Transform;


public class CircleBoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CircleCollider.class,
            Transform.class,
            Size.class
    ).get();

    public CircleBoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CircleCollider collider = Mappers.CIRCLE_COLLIDER.get(entity);
        Transform transform = Mappers.TRANSFORM.get(entity);
        Size size = Mappers.SIZE.get(entity);
        Vector2 pos = transform.position.cpy().add(size.width / 2, size.height / 2);
        collider.bounds.setPosition(pos);
    }
}

