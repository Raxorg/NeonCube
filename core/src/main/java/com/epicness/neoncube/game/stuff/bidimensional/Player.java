package com.epicness.neoncube.game.stuff.bidimensional;

import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_WIDTH;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritedAnimation;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.utils.CollisionUtils;
import com.epicness.neoncube.game.assets.GameAssets;
import com.epicness.neoncube.game.constants.PlayerStatus;

public class Player implements Movable {

    private final SpritedAnimation idle, running, climbing, falling;
    public SpritedAnimation currentAnimation;
    public final Vector2 speed;
    private PlayerStatus status;

    public Player(GameAssets assets) {
        idle = new SpritedAnimation(1f, assets.getStickmanIdle());
        idle.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        idle.useBilinearFilter();

        running = new SpritedAnimation(0.05f, assets.getStickmanRunning());
        running.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        running.enableLooping();
        running.useBilinearFilter();

        climbing = new SpritedAnimation(0.1f, assets.getStickmanClimbing());
        climbing.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        climbing.enableLooping();
        climbing.useBilinearFilter();

        falling = new SpritedAnimation(1f, assets.getStickmanFalling());
        falling.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        falling.useBilinearFilter();

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

    public float getCenterX() {
        return currentAnimation.getCenterX();
    }

    public float getEndX() {
        return currentAnimation.getEndX();
    }

    @Override
    public void translateX(float amount) {
        currentAnimation.translateX(amount);
    }

    @Override
    public float getY() {
        return currentAnimation.getY();
    }

    public float getCenterY() {
        return currentAnimation.getCenterY();
    }

    @Override
    public void translateY(float amount) {
        currentAnimation.translateY(amount);
    }

    public boolean overlaps(Rectangle other) {
        return CollisionUtils.overlaps(currentAnimation.getBoundingRectangle(), other);
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
            case FALLING:
                currentAnimation = falling;
                break;
        }
        currentAnimation.setPosition(position);
    }
}