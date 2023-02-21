package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.zigai.submariner.GameManager;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.*;

import static com.zigai.submariner.Assets.explosion;

public class CollisionSystem extends EntitySystem {

    private static final Family SUBMARINE_FAMILY = Family.all(Submarine.class, BoxCollider.class).get();
    private static final Family MINE_FAMILY = Family.all(Mine.class, CircleCollider.class).get();
    private static final Family FISH_FAMILY = Family.all(Fish.class, BoxCollider.class).get();
    private static final Family HEART_FAMILY = Family.all(Heart.class, BoxCollider.class).get();
    private static final Family PROJECTILE_FAMILY = Family.all(Projectile.class, BoxCollider.class).get();

    private SoundSystem soundSystem;

    public CollisionSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        soundSystem = engine.getSystem(SoundSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;

        ImmutableArray<Entity> submarines = getEngine().getEntitiesFor(SUBMARINE_FAMILY);
        ImmutableArray<Entity> mines = getEngine().getEntitiesFor(MINE_FAMILY);
        ImmutableArray<Entity> fishes = getEngine().getEntitiesFor(FISH_FAMILY);
        ImmutableArray<Entity> hearts = getEngine().getEntitiesFor(HEART_FAMILY);
        ImmutableArray<Entity> projectiles = getEngine().getEntitiesFor(PROJECTILE_FAMILY);

        for (Entity submarineEntity : submarines) {
            BoxCollider submarineBounds = Mappers.BOX_COLLIDER.get(submarineEntity);
            // check collision with mines
            for (Entity mineEntity : mines) {
                Mine mine = Mappers.MINE.get(mineEntity);
                if (mine.hit)
                    continue;

                CircleCollider mineBounds = Mappers.CIRCLE_COLLIDER.get(mineEntity);
                Transform mineTransform = Mappers.TRANSFORM.get(mineEntity);
                // check projectile collisions with mines
                for (Entity projectileEntity : projectiles) {
                    BoxCollider projectileBounds = Mappers.BOX_COLLIDER.get(projectileEntity);
                    if (Intersector.overlaps(mineBounds.bounds, projectileBounds.bounds)) {
                        GameManager.INSTANCE.onProjectileMineCollision();
                        mine.hit = true;
                        soundSystem.playExplosionSound();
                        explosion.reset();
                        explosion.getEmitters().first().setPosition(mineTransform.position.x, mineTransform.position.y);
                        explosion.start();
                        getEngine().removeEntity(mineEntity);
                        getEngine().removeEntity(projectileEntity);

                    }
                }
                if (mine.hit)
                    continue;

                if (Intersector.overlaps(mineBounds.bounds, submarineBounds.bounds)) {
                    mine.hit = true;
                    soundSystem.playExplosionSound();
                    explosion.reset();
                    explosion.getEmitters().first().setPosition(mineTransform.position.x, mineTransform.position.y);
                    explosion.start();
                    GameManager.INSTANCE.onMineCollision();
                    getEngine().removeEntity(mineEntity);

                }
            }

            // check collision with fishes
            for (Entity fishEntity : fishes) {
                Fish fish = Mappers.FISH.get(fishEntity);
                if (fish.hit)
                    continue;

                BoxCollider fishBounds = Mappers.BOX_COLLIDER.get(fishEntity);
                // check projectile collisions with fishes
                for (Entity projectileEntity : projectiles) {
                    BoxCollider projectileBounds = Mappers.BOX_COLLIDER.get(projectileEntity);
                    if (Intersector.overlaps(projectileBounds.bounds, fishBounds.bounds)) {
                        GameManager.INSTANCE.onProjectileFishCollision();
                        fish.hit = true;
                        Transform fishTransform = Mappers.TRANSFORM.get(fishEntity);
                        soundSystem.playExplosionSound();
                        explosion.reset();
                        explosion.getEmitters().first().setPosition(fishTransform.position.x, fishTransform.position.y);
                        explosion.start();
                        getEngine().removeEntity(fishEntity);
                        getEngine().removeEntity(projectileEntity);

                    }
                }

                if (fish.hit)
                    continue;

                if (Intersector.overlaps(submarineBounds.bounds, fishBounds.bounds)) {
                    fish.hit = true;
                    GameManager.INSTANCE.onFishCollision();
                    soundSystem.playPickupSound();
                    getEngine().removeEntity(fishEntity);
                }
            }

            // check collision with hearts
            for (Entity heartEntity : hearts) {
                Heart heart = Mappers.HEART.get(heartEntity);
                if (heart.hit)
                    continue;
                BoxCollider heartBounds = Mappers.BOX_COLLIDER.get(heartEntity);
                // check projectile collisions with hearts
                for (Entity projectileEntity : projectiles) {
                    BoxCollider projectileBounds = Mappers.BOX_COLLIDER.get(projectileEntity);
                    if (Intersector.overlaps(projectileBounds.bounds, heartBounds.bounds)) {
                        GameManager.INSTANCE.onProjectileFishCollision();
                        heart.hit = true;
                        soundSystem.playExplosionSound();
                        explosion.reset();
                        Transform heartTransform = Mappers.TRANSFORM.get(heartEntity);
                        explosion.getEmitters().first().setPosition(heartTransform.position.x, heartTransform.position.y);
                        explosion.start();
                        GameManager.INSTANCE.onProjectileHeartCollision();
                        getEngine().removeEntity(heartEntity);
                        getEngine().removeEntity(projectileEntity);

                    }
                }
                if (heart.hit)
                    continue;
                if (Intersector.overlaps(submarineBounds.bounds, heartBounds.bounds)) {
                    heart.hit = true;
                    GameManager.INSTANCE.onHeartCollision();
                    soundSystem.playPickupSound();
                    getEngine().removeEntity(heartEntity);
                }
            }
        }
    }
}