package com.epicness.neoncube.game.logic;

import static com.epicness.neoncube.game.GameConstants.DOWN_KEY;
import static com.epicness.neoncube.game.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.GameConstants.TRIANGLE_SPEED;
import static com.epicness.neoncube.game.GameConstants.UP_KEY;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.Sprited;

public class MovementHandler extends GameLogicHandler {

    private Sprited triangle;
    private Vector2 speed;

    @Override
    protected void init() {
        triangle = stuff.getCubeWorld().getTriangle();
        speed = new Vector2();
    }

    @Override
    public void update(float delta) {
        triangle.translate(speed.cpy().nor().scl(TRIANGLE_SPEED * delta));
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case UP_KEY:
                speed.y += 1f;
                break;
            case LEFT_KEY:
                speed.x -= 1f;
                break;
            case DOWN_KEY:
                speed.y -= 1f;
                break;
            case RIGHT_KEY:
                speed.x += 1f;
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        switch (keycode) {
            case UP_KEY:
                speed.y -= 1f;
                break;
            case LEFT_KEY:
                speed.x += 1f;
                break;
            case DOWN_KEY:
                speed.y += 1f;
                break;
            case RIGHT_KEY:
                speed.x -= 1f;
                break;
        }
    }
}