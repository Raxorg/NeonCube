package com.epicness.neoncube.game.stuff;

import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_WIDTH;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedAnimation;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.neoncube.game.constants.PlayerStatus;

public class Player implements Movable {

    private final SpritedAnimation idle, running, climbing;
    public SpritedAnimation currentAnimation;
    public final Vector2 speed;
    private PlayerStatus status;

    public Player(Sprite idleFrame, Sprite[] runningFrames, Sprite[] climbingFrames) {
        idle = new SpritedAnimation(1f, idleFrame);
        idle.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        idle.enableLooping();
        idle.useBilinearFilter();

        running = new SpritedAnimation(0.05f, runningFrames);
        running.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        running.enableLooping();
        running.useBilinearFilter();

        climbing = new SpritedAnimation(0.2f, climbingFrames);
        climbing.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        climbing.enableLooping();
        climbing.useBilinearFilter();

        currentAnimation = idle;

        speed = new Vector2();
        status = IDLE;
    }

    public void draw(SpriteBatch spriteBatch) {
        currentAnimation.draw(spriteBatch);
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(currentAnimation.getBoundingRectangle());
    }

    @Override
    public float getX() {
        return currentAnimation.getX();
    }

    @Override
    public void translateX(float amount) {
        currentAnimation.translateX(amount);
    }

    @Override
    public float getY() {
        return currentAnimation.getY();
    }

    @Override
    public void translateY(float amount) {
        currentAnimation.translateY(amount);
    }

    public boolean overlaps(Sprited other) {
        return currentAnimation.getBoundingRectangle().overlaps(other.getBoundingRectangle());
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus newStatus) {
        status = newStatus;
        Vector2 position = currentAnimation.getPosition();
        switch (status) {
            case IDLE:
                currentAnimation = idle;
                break;
            case RUNNING:
                currentAnimation = running;
                break;
            case CLIMBING:
                currentAnimation = climbing;
                break;
        }
        currentAnimation.setPosition(position);
    }
}