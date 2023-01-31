package com.zigai.submariner.systems.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.zigai.submariner.systems.debug.support.DebugCameraController;

public class DebugCameraSystem extends EntitySystem {

    // == constants ==
    private static final DebugCameraController DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    // == attributes ==
    private final OrthographicCamera camera;

    // == constructors ==
    public DebugCameraSystem(float startX, float startY, OrthographicCamera camera) {
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX, startY);
    }

    // == update ==
    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);
    }
}
