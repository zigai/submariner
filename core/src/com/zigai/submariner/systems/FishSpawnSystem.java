package com.zigai.submariner.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.zigai.submariner.Config;

public class FishSpawnSystem extends IntervalSystem {
    private EntityFactory factory;

    public FishSpawnSystem(float time) {
        super(time);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        factory = engine.getSystem(EntityFactory.class);
    }

    @Override
    protected void updateInterval() {
        factory.createFish();
    }
}
