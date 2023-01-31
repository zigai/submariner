package com.zigai.submariner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class Texture implements Component, Pool.Poolable {

    public TextureRegion region;

    @Override
    public void reset() {
        region = null;
    }
}
