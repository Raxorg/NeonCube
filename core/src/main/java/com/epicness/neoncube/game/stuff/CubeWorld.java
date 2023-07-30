package com.epicness.neoncube.game.stuff;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.neoncube.game.assets.GameAssets;

public class CubeWorld {

    private final Sprited background;
    private final Player player;

    public CubeWorld(SharedAssets sharedAssets, GameAssets assets) {
        background = new Sprited(sharedAssets.getPixel());
        background.setSize(CAMERA_WIDTH * 4f, CAMERA_HEIGHT);
        background.setColor(BLUE.cpy().lerp(CLEAR, 0.2f));

        player = new Player(assets.getStickmanRunning(), assets.getStickmanIdle());
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        player.draw(spriteBatch);
    }

    public Player getPlayer() {
        return player;
    }
}