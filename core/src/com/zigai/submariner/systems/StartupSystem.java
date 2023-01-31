package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

public class StartupSystem extends EntitySystem {
    public StartupSystem() {
        setProcessing(false);   // it doesn't call update
    }

    @Override
    public void addedToEngine(Engine engine) {
        EntityFactory factory = engine.getSystem(EntityFactory.class);
        factory.createBackground();
        factory.createSubmarine();
    }
}