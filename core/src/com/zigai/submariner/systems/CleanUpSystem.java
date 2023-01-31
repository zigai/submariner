package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.CleanUp;
import com.zigai.submariner.components.Size;
import com.zigai.submariner.components.Transform;

public class CleanUpSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CleanUp.class,
            Transform.class,
            Size.class
    ).get();

    public CleanUpSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Transform transform = Mappers.TRANSFORM.get(entity);
        Size size = Mappers.SIZE.get(entity);

        if (transform.position.x < -size.height) {
            getEngine().removeEntity(entity);
        }
    }
}

