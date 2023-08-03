package com.epicness.neoncube.game.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class Ladder {

    private final DelayedRemovalArray<Sprited> parts;
    public final Rectangle bounds;
    public final Color debugColor;

    public Ladder(int ladderParts, Sprite ladderSprite) {
        parts = new DelayedRemovalArray<>();
        for (int i = 0; i < ladderParts; i++) {
            Sprited ladderPart = new Sprited(ladderSprite);
            ladderPart.setY(i * 200f);
            parts.add(ladderPart);
        }
        bounds = new Rectangle();
        bounds.width = 100f;
        bounds.height = ladderParts * 200f;

        debugColor = Color.GREEN.cpy();
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < parts.size; i++) {
            parts.get(i).draw(spriteBatch);
        }
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.setColor(debugColor);
        shapeRenderer.rect(bounds);
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