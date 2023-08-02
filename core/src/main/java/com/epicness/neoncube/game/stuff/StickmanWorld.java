package com.epicness.neoncube.game.stuff;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.neoncube.game.assets.GameAssets;

public class StickmanWorld {

    private final Sprited background;
    public final DelayedRemovalArray<Sprited> ladders;
    public final Player player;

    public StickmanWorld(SharedAssets sharedAssets, GameAssets assets) {
        background = new Sprited(sharedAssets.getPixel());
        background.setSize(CAMERA_WIDTH * 4f, CAMERA_HEIGHT);
        background.setColor(BLUE.cpy().lerp(CLEAR, 0.2f));

        ladders = new DelayedRemovalArray<>();
        for (int i = 0; i < 5; i++) {
            Sprited ladder = new Sprited(assets.getLadder());
            ladder.setPosition(CAMERA_HALF_WIDTH - 50f, i * 200f);
            ladders.add(ladder);
        }

        player = new Player(assets.getStickmanIdle(), assets.getStickmanRunning(), assets.getStickmanClimbing());
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        for (int i = 0; i < ladders.size; i++) {
            ladders.get(i).draw(spriteBatch);
        }
        player.draw(spriteBatch);
    }
}