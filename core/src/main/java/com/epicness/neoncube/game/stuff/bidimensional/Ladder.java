package com.epicness.neoncube.game.stuff.bidimensional;

import static com.epicness.neoncube.game.constants.GameConstants.LADDER_PART_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.LADDER_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class Ladder {

    private final DelayedRemovalArray<Sprited> parts;
    public final Rectangle bounds;
    public final Color debugColor;

    public Ladder(int ladderParts, Sprite ladderSprite) {
        parts = new DelayedRemovalArray<>();
        for (int i = 0; i < ladderParts; i++) {
            Sprited ladderPart = new Sprited(ladderSprite);
            ladderPart.setSize(LADDER_WIDTH, LADDER_PART_HEIGHT);
            ladderPart.setY(i * LADDER_PART_HEIGHT);
            parts.add(ladderPart);
        }
        bounds = new Rectangle();
        bounds.width = LADDER_WIDTH;
        bounds.height = ladderParts * LADDER_PART_HEIGHT;

        debugColor = Color.GREEN.cpy();
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < parts.size; i++) {
            parts.get(i).draw(spriteBatch);
        }
    }

    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.setColor(debugColor);
        shapeDrawer.rectangle(bounds);
    }

    public void setX(float x) {
        for (int i = 0; i < parts.size; i++) {
            parts.get(i).setX(x);
        }
        bounds.x = x;
    }

    public float getY() {
        return parts.get(0).getY();
    }

    public float getTopY() {
        return bounds.y + bounds.height;
    }
}