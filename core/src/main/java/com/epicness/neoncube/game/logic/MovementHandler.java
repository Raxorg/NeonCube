package com.epicness.neoncube.game.logic;

import static com.badlogic.gdx.Input.Keys.I;
import static com.badlogic.gdx.Input.Keys.J;
import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.epicness.neoncube.game.GameConstants.TRIANGLE_SPEED;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.Sprited;

public class MovementHandler extends GameLogicHandler {

    private Sprited triangle;
    private Vector2 speed;

    @Override
    protected void init() {
        triangle = stuff.getGameView().getTriangle();
        speed = new Vector2();
    }

    @Override
    public void update(float delta) {
        triangle.translate(speed.cpy().nor().scl(TRIANGLE_SPEED * delta));
    }

    public void keyPressed(int keycode) {
        switch (keycode) {
            case I:
                speed.y += 1f;
                break;
            case J:
                speed.x -= 1f;
                break;
            case K:
                speed.y -= 1f;
                break;
            case L:
                speed.x += 1f;
                break;
        }
    }

    public void keyReleased(int keycode) {
        switch (keycode) {
            case I:
                speed.y -= 1f;
                break;
            case J:
                speed.x += 1f;
                break;
            case K:
                speed.y += 1f;
                break;
            case L:
                speed.x -= 1f;
                break;
        }
    }
}