package com.epicness.neoncube.game.stuff;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.neoncube.game.assets.GameAssets;

public class GameView {

    private final Sprited background, triangle;

    public GameView(SharedAssets sharedAssets, GameAssets assets) {
        background = new Sprited(sharedAssets.getPixel());
        background.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        background.setColor(BLUE.cpy().lerp(CLEAR, 0.2f));

        triangle = new Sprited(sharedAssets.getTriangle());
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        triangle.draw(spriteBatch);
    }

    public Sprited getTriangle() {
        return triangle;
    }
}