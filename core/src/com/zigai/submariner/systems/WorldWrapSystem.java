package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.zigai.submariner.Config;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.Size;
import com.zigai.submariner.components.Transform;
import com.zigai.submariner.components.WorldWrap;

public class WorldWrapSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            Transform.class,
            Size.class,
            WorldWrap.class
    ).get();

    public WorldWrapSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Transform transform = Mappers.TRANSFORM.get(entity);
        Size size = Mappers.SIZE.get(entity);

        if (transform.position.x >= Config.WORLD_WIDTH - size.width) {
            transform.position.x = Config.WORLD_WIDTH - size.width;
        } else if (transform.position.x < 0) {
            transform.position.x = 0;
        }
        if (transform.position.y >= Config.WORLD_HEIGHT - size.height) {
            transform.position.y = Config.WORLD_HEIGHT - size.height;
        } else if (transform.position.y < 0) {
            transform.position.y = 0;
        }
    }
}
