package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.BoxCollider;
import com.zigai.submariner.components.Size;
import com.zigai.submariner.components.Transform;

public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            BoxCollider.class,
            Transform.class,
            Size.class
    ).get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoxCollider collider = Mappers.BOX_COLLIDER.get(entity);
        Transform transform = Mappers.TRANSFORM.get(entity);
        Size size = Mappers.SIZE.get(entity);
        collider.bounds.setPosition(transform.position);
        //bounds.rectangle.setSize(size.width * transform.scale.x, size.height * transform.scale.y);
    }
}

