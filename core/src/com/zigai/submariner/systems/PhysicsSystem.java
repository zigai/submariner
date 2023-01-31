package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.Physics;
import com.zigai.submariner.components.Transform;

public class PhysicsSystem extends IteratingSystem {


    private static final Family FAMILY = Family.all(Transform.class, Physics.class).get();

    public PhysicsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Transform transform = Mappers.TRANSFORM.get(entity);
        Physics physics = Mappers.PHYSICS.get(entity);

        transform.position.add(physics.velocity.cpy().scl(deltaTime));
        physics.velocity.add(physics.acceleration.cpy().scl(deltaTime));
        transform.rotation += physics.rotationSpeed * deltaTime;
    }
}
