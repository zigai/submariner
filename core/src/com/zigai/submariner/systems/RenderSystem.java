package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.*;

import static com.zigai.submariner.Assets.explosion;


public class RenderSystem extends SortedIteratingSystem {
    private final SpriteBatch batch;
    private final Viewport viewport;

    private static final Family FAMILY = Family.all(Transform.class, Texture.class, Layer.class, Size.class).get();

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY, LayerComparator.INSTANCE);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {   // override to avoid calling batch.begin/end for each entity
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        super.update(deltaTime);    // calls processEntity method, which is wrapped with begin/end
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Transform transform = Mappers.TRANSFORM.get(entity);
        Texture texture = Mappers.TEXTURE.get(entity);
        Size size = Mappers.SIZE.get(entity);

        explosion.update(deltaTime);
        explosion.draw(batch);
        
        batch.draw(texture.region, transform.position.x, transform.position.y,
                size.width / 2, size.height / 2,
                size.width, size.height,
                transform.scale.x, transform.scale.y, transform.rotation
        );
    }
}