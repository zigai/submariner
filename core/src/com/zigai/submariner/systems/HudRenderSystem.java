package com.zigai.submariner.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zigai.submariner.Assets;
import com.zigai.submariner.GameManager;
import com.zigai.submariner.assets.RegionNames;

public class HudRenderSystem extends EntitySystem {
    private final SpriteBatch batch;
    private final Viewport hudViewport;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();
    private static final float PADDING_SCORE = 20.0f;
    private static final float PADDING_HEARTS = 10.0f;

    public HudRenderSystem(SpriteBatch batch, Viewport hudViewport, BitmapFont font) {
        this.batch = batch;
        this.hudViewport = hudViewport;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();
        font.setColor(Color.WHITE);

        renderScore();
        renderHealth();
        if (GameManager.INSTANCE.isGameOver()) {
            renderGameOverHUD();
        }
        if (GameManager.INSTANCE.isPaused()) {
            renderPauseScreen();
        }
        batch.end();
    }

    private void renderHealth() {
        TextureRegion heart = Assets.getTexture(RegionNames.HEART);
        float currentPadding = PADDING_SCORE;
        for (int i = 0; i < GameManager.INSTANCE.getLives(); i++) {
            batch.draw(heart, currentPadding, hudViewport.getWorldHeight() - heart.getRegionHeight() - PADDING_SCORE);
            currentPadding += PADDING_HEARTS + heart.getRegionWidth();
        }
    }

    private void renderScore() {
        float y = hudViewport.getWorldHeight() - PADDING_SCORE;
        layout.setText(font, "SCORE: " + GameManager.INSTANCE.getScore());
        float x = hudViewport.getWorldWidth() - layout.width - PADDING_SCORE;
        font.draw(batch, layout, x, y);
        y -= font.getLineHeight();
        layout.setText(font, "BEST:  " + GameManager.INSTANCE.getBestResult());
        x = hudViewport.getWorldWidth() - layout.width - PADDING_SCORE;
        font.draw(batch, layout, x, y);
    }

    private void renderGameOverHUD() {
        font.setColor(Color.RED);
        layout.setText(font, "GAME OVER! Press 'R' to play again");
        float x = (hudViewport.getWorldWidth() + layout.width) / 2 - layout.width;
        float y = (hudViewport.getWorldHeight() + layout.height) / 2 - layout.height;
        font.draw(batch, layout, x, y);
    }

    private void renderPauseScreen() {
        font.setColor(Color.RED);
        layout.setText(font, "PAUSED");
        float x = (hudViewport.getWorldWidth() + layout.width) / 2 - layout.width;
        float y = (hudViewport.getWorldHeight() + layout.height) / 2 - layout.height;
        font.draw(batch, layout, x, y);
    }
}
