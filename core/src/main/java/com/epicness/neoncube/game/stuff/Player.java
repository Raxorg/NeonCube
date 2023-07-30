package com.epicness.neoncube.game.stuff;

import static com.epicness.neoncube.game.GameConstants.PLAYER_HEIGHT;
import static com.epicness.neoncube.game.GameConstants.PLAYER_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedAnimation;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class Player implements Movable {

    public final SpritedAnimation animation;
    private final Sprited idle;
    public final Vector2 speed;

    public Player(Sprite[] animationFrames, Sprite idleFrame) {
        animation = new SpritedAnimation(animationFrames, 0.05f);
        animation.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        animation.enableLooping();
        animation.useBilinearFilter();

        idle = new Sprited(idleFrame);
        idle.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        idle.useBilinearFilter();

        speed = new Vector2();
    }

    public void draw(SpriteBatch spriteBatch) {
        if (speed.isZero()) {
            idle.draw(spriteBatch);
        } else {
            animation.draw(spriteBatch);
        }
    }

    @Override
    public float getX() {
        return idle.getX();
    }

    @Override
    public void translateX(float amount) {
        idle.translateX(amount);
        animation.translateX(amount);
    }

    @Override
    public float getY() {
        return idle.getY();
    }

    @Override
    public void translateY(float amount) {
        idle.translateY(amount);
        animation.translateY(amount);
    }
}