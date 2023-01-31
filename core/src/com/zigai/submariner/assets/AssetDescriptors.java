package com.zigai.submariner.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {
    public static final AssetDescriptor<BitmapFont> FONT32 = new AssetDescriptor<>(AssetPaths.UI_FONT32, BitmapFont.class);
    public static final AssetDescriptor<BitmapFont> FONT64 = new AssetDescriptor<>(AssetPaths.UI_FONT64, BitmapFont.class);
    public static final AssetDescriptor<TextureAtlas> GAMEPLAY = new AssetDescriptor<>(AssetPaths.GAMEPLAY, TextureAtlas.class);
    public static final AssetDescriptor<Sound> PICKUP_SOUND = new AssetDescriptor<>(AssetPaths.PICKUP_SOUND, Sound.class);
    public static final AssetDescriptor<Sound> EXPLOSION_SOUND = new AssetDescriptor<>(AssetPaths.EXPLOSION_SOUND, Sound.class);

    private AssetDescriptors() {
    }
}
