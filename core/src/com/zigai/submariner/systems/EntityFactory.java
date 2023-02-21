package com.zigai.submariner.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.zigai.submariner.Assets;
import com.zigai.submariner.Config;
import com.zigai.submariner.Util;
import com.zigai.submariner.assets.RegionNames;
import com.zigai.submariner.components.*;
import com.zigai.submariner.Config.FISH;
import com.zigai.submariner.Config.MINE;
import com.zigai.submariner.Config.LAYERS;
import com.zigai.submariner.Config.SUBMARINE;
import com.zigai.submariner.Config.HEART;
import com.zigai.submariner.Config.PROJECTILE;

public class EntityFactory extends EntitySystem {
    private PooledEngine engine;

    public EntityFactory() {
        setProcessing(false);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createFish() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2();
        transform.position.x = Config.WORLD_WIDTH;
        transform.position.y = MathUtils.random(0, Config.WORLD_HEIGHT - FISH.HEIGHT);

        Size size = engine.createComponent(Size.class);
        size.width = FISH.WIDTH;
        size.height = FISH.HEIGHT;

        BoxCollider bounds = engine.createComponent(BoxCollider.class);
        bounds.bounds.setPosition(transform.position);
        bounds.bounds.setSize(size.width * transform.scale.x, size.height * transform.scale.y);

        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.FISH;

        Physics physics = engine.createComponent(Physics.class);
        physics.velocity = new Vector2(FISH.SPEED, 0);
        physics.acceleration = new Vector2(0, 0);
        physics.rotationSpeed = 0;

        Texture texture = engine.createComponent(Texture.class);
        texture.region = FISH.TEXTURE;

        CleanUp cleanUp = engine.createComponent(CleanUp.class);
        Fish fishComponent = engine.createComponent(Fish.class);

        Entity fish = engine.createEntity().add(transform).add(size).add(texture).add(layer).add(fishComponent).add(physics).add(bounds).add(cleanUp);
        engine.addEntity(fish);
    }

    public void createMine() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2();
        transform.position.x = Config.WORLD_WIDTH;
        transform.position.y = MathUtils.random(0, Config.WORLD_HEIGHT - MINE.HEIGHT);
        float scl = Util.getMineScale(1, MINE.MAX_SCALE);
        System.out.println("SCALE: " + scl);
        transform.scale.set(scl, scl);

        Size size = engine.createComponent(Size.class);
        size.width = MINE.WIDTH;
        size.height = MINE.HEIGHT;

        CircleCollider bounds = engine.createComponent(CircleCollider.class);
        bounds.bounds.setPosition(transform.position);

        bounds.bounds.setRadius(size.width * transform.scale.x / 2 * 0.7f);
        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.MINE;

        Physics physics = engine.createComponent(Physics.class);
        physics.velocity = new Vector2(MINE.SPEED, 0);
        physics.acceleration = new Vector2(0, 0);
        physics.rotationSpeed = MathUtils.random(3, 30);

        Texture texture = engine.createComponent(Texture.class);
        texture.region = MINE.TEXTURE;
        CleanUp cleanUp = engine.createComponent(CleanUp.class);
        Mine mineComponent = engine.createComponent(Mine.class);

        Entity mine = engine.createEntity().add(transform).add(size).add(texture).add(layer).add(mineComponent).add(physics).add(bounds).add(cleanUp);
        engine.addEntity(mine);
    }

    public void createSubmarine() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2();
        transform.position.x = 30;
        transform.position.y = Config.WORLD_WIDTH / 2 - SUBMARINE.WIDTH;

        Size size = engine.createComponent(Size.class);
        size.width = SUBMARINE.WIDTH;
        size.height = SUBMARINE.HEIGHT;

        BoxCollider bounds = engine.createComponent(BoxCollider.class);
        bounds.bounds.setPosition(transform.position);
        bounds.bounds.setSize(size.width * transform.scale.x, size.height * transform.scale.y);

        Texture texture = engine.createComponent(Texture.class);
        texture.region = SUBMARINE.TEXTURE;

        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.SUBMARINE;
        WorldWrap worldWrap = engine.createComponent(WorldWrap.class);

        Submarine submarineComponent = engine.createComponent(Submarine.class);
        Entity submarine = engine.createEntity().add(transform).add(size).add(texture).add(layer).add(submarineComponent).add(bounds).add(worldWrap);
        engine.addEntity(submarine);
    }

    public void createHeart() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2();
        transform.position.x = Config.WORLD_WIDTH;
        transform.position.y = MathUtils.random(0, Config.WORLD_HEIGHT - HEART.HEIGHT);

        Size size = engine.createComponent(Size.class);
        size.width = HEART.WIDTH;
        size.height = HEART.HEIGHT;

        BoxCollider bounds = engine.createComponent(BoxCollider.class);
        bounds.bounds.setPosition(transform.position);
        bounds.bounds.setSize(size.width * transform.scale.x, size.height * transform.scale.y);

        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.HEART;

        Physics physics = engine.createComponent(Physics.class);
        physics.velocity = new Vector2(HEART.SPEED, 0);
        physics.acceleration = new Vector2(0, 0);
        physics.rotationSpeed = MathUtils.random(3, 30);

        Texture texture = engine.createComponent(Texture.class);
        texture.region = HEART.TEXTURE;
        CleanUp cleanUp = engine.createComponent(CleanUp.class);
        Heart heartComponent = engine.createComponent(Heart.class);

        Entity heart = engine.createEntity().add(transform).add(size).add(texture).add(layer).add(heartComponent).add(physics).add(bounds).add(cleanUp);
        engine.addEntity(heart);
    }

    public void createProjectile() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2();
        Family SUBMARINE_FAMILY = Family.all(Submarine.class).get();

        ImmutableArray<Entity> sub = engine.getEntitiesFor(SUBMARINE_FAMILY);
        Entity subEntity = sub.get(0);
        BoxCollider subBounds = subEntity.getComponent(BoxCollider.class);
        Vector2 subCenter = new Vector2();
        subBounds.bounds.getCenter(subCenter);
        subCenter.sub(SUBMARINE.WIDTH / 2f, SUBMARINE.HEIGHT / 2f);
        transform.position.set(subCenter);

        Size size = engine.createComponent(Size.class);
        size.width = PROJECTILE.WIDTH;
        size.height = PROJECTILE.HEIGHT;

        BoxCollider bounds = engine.createComponent(BoxCollider.class);
        bounds.bounds.setPosition(transform.position);
        bounds.bounds.setSize(size.width * transform.scale.x, size.height * transform.scale.y);

        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.PROJECTILE;

        Physics physics = engine.createComponent(Physics.class);
        physics.velocity = new Vector2(PROJECTILE.SPEED, 0);
        physics.acceleration = new Vector2(0, 0);

        Texture texture = engine.createComponent(Texture.class);
        texture.region = PROJECTILE.TEXTURE;
        CleanUp cleanUp = engine.createComponent(CleanUp.class);
        Projectile projectileComponent = engine.createComponent(Projectile.class);

        Entity projectile = engine.createEntity().add(transform).add(size).add(texture).add(layer).add(projectileComponent).add(physics).add(bounds).add(cleanUp);
        engine.addEntity(projectile);
    }

    public void createBackground() {
        Transform transform = engine.createComponent(Transform.class);
        transform.position = new Vector2(0, (float) Config.WORLD_HEIGHT / 2);
        transform.scale = new Vector2(6, 7);
        Texture texture = engine.createComponent(Texture.class);
        texture.region = Assets.getTexture(RegionNames.BACKGROUND);
        Layer layer = engine.createComponent(Layer.class);
        layer.value = LAYERS.BACKGROUND;
        Size size = engine.createComponent(Size.class);
        size.width = texture.region.getRegionWidth();
        size.height = texture.region.getRegionHeight();
        Entity background = engine.createEntity().add(transform).add(size).add(texture).add(layer);
        engine.addEntity(background);
    }
}
