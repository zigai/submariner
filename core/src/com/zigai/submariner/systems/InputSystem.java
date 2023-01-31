package com.zigai.submariner.systems;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.zigai.submariner.Config.SUBMARINE;
import com.zigai.submariner.GameManager;
import com.zigai.submariner.Mappers;
import com.zigai.submariner.components.Submarine;
import com.zigai.submariner.components.Transform;
import com.zigai.submariner.Config.CONTROLS;

public class InputSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            Submarine.class,
            Transform.class
    ).get();

    public InputSystem() {
        super(FAMILY);
    }

    // we don't need to override the update Iterating system method because there is no batch begin/end
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Transform transform = Mappers.TRANSFORM.get(entity);

        if (Gdx.input.isKeyPressed(CONTROLS.FORWARD)) {
            transform.position.x += SUBMARINE.SPEED_FORWARD * deltaTime;
        }
        if (Gdx.input.isKeyPressed(CONTROLS.BACKWARD)) {
            transform.position.x += SUBMARINE.SPEED_BACKWARD * deltaTime;
        }
        if (Gdx.input.isKeyPressed(CONTROLS.UP)) {
            transform.position.y += SUBMARINE.SPEED_UP * deltaTime;
        }
        if (Gdx.input.isKeyPressed(CONTROLS.DOWN)) {
            transform.position.y += SUBMARINE.SPEED_DOWN * deltaTime;
        }
        if (Gdx.input.isKeyJustPressed(CONTROLS.SHOOT)) {
            getEngine().getSystem(SubmarineShootingSystem.class).shoot();
        }
        if (Gdx.input.isKeyJustPressed(CONTROLS.EXIT)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyJustPressed(CONTROLS.RESTART)) {
            if (GameManager.INSTANCE.isGameOver()) {
                GameManager.INSTANCE.reset();
            }
        }
        /*
        if (Gdx.input.isKeyJustPressed(CONTROLS.PAUSE)) {
            GameManager.INSTANCE.togglePause();
        }*/
    }
}