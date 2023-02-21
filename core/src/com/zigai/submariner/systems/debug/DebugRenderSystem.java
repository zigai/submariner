package com.zigai.submariner.systems.debug;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.BoxCollider;
import com.zigai.submariner.components.CircleCollider;

public class DebugRenderSystem extends IteratingSystem {

    // == constants ==
    private static final Family FAMILY = Family.one(
            BoxCollider.class, CircleCollider.class
    ).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor().cpy();

        viewport.apply();
        renderer.setColor(Color.RED);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        // calls processEntity internally in for loop
        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoxCollider bounds = Mappers.BOX_COLLIDER.get(entity);
        if (bounds == null) {
            CircleCollider boundCircle = Mappers.CIRCLE_COLLIDER.get(entity);
            renderer.circle(boundCircle.bounds.x, boundCircle.bounds.y, boundCircle.bounds.radius);
            return;
        }
        renderer.rect(bounds.bounds.x, bounds.bounds.y,
                bounds.bounds.width, bounds.bounds.height);
    }
}
