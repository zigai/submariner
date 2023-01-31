package com.zigai.submariner.screens;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zigai.submariner.Assets;
import com.zigai.submariner.Config;
import com.zigai.submariner.GameManager;
import com.zigai.submariner.SubmarinerGame;
import com.zigai.submariner.assets.AssetDescriptors;
import com.zigai.submariner.systems.*;
import com.zigai.submariner.systems.debug.*;
import com.zigai.submariner.Config.FISH;
import com.zigai.submariner.Config.MINE;

public class GameScreen extends ScreenAdapter {
    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), Logger.DEBUG);

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private BitmapFont font;
    SubmarinerGame game;

    public GameScreen(SubmarinerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Config.WORLD_WIDTH, Config.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer = new ShapeRenderer();
        font = Assets.assetManager.get(AssetDescriptors.FONT64);
        engine = new PooledEngine();
        initSystems();
    }

    private void initSystems() {
        EntityFactory entityFactory = new EntityFactory();
        engine.addSystem(entityFactory);
        engine.addSystem(new StartupSystem());
        engine.addSystem(new PhysicsSystem());
        engine.addSystem(new RenderSystem(game.getBatch(), viewport));
        engine.addSystem(new SoundSystem(Assets.assetManager));
        engine.addSystem(new FishSpawnSystem(FISH.SPAWN_TIME));
        engine.addSystem(new MineSpawnSystem(MINE.SPAWN_TIME));
        engine.addSystem(new InputSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new SubmarineShootingSystem(Config.PROJECTILE.SHOOTING_COOLDOWN));
        engine.addSystem(new HudRenderSystem(game.getBatch(), hudViewport, font));
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CleanUpSystem());
        if (GameManager.INSTANCE.isDebugMode()) {
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugRenderSystem(viewport, renderer));
            engine.addSystem(new DebugCameraSystem(
                    (float) Config.WORLD_WIDTH / 2, (float) Config.WORLD_HEIGHT / 2,
                    camera
            ));
            engine.addSystem(new DebugInputSystem());
        }
        EntityFactory factory = engine.getSystem(EntityFactory.class);
        factory.createSubmarine();
        GameManager.INSTANCE.addEngine(engine);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Config.CONTROLS.EXIT)) Gdx.app.exit();
        if ((Gdx.input.isKeyJustPressed(Config.CONTROLS.PAUSE))) {
            GameManager.INSTANCE.togglePause();
        }
        if (GameManager.INSTANCE.isPaused()) {
            engine.update(0);
            return;
        }
        ScreenUtils.clear(Color.BLACK);

        if (GameManager.INSTANCE.isGameOver()) {
            GameManager.INSTANCE.updateHighScore();
            engine.update(0);
            if ((Gdx.input.isKeyPressed(Config.CONTROLS.RESTART))) {
                Config.reset();
                game.setScreen(new GameScreen(game));
            }
        } else {
            engine.update(delta);
        }
    }
}
