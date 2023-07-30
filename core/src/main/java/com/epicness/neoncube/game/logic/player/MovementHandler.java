package com.epicness.neoncube.game.logic.player;

import static com.epicness.neoncube.game.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.GameConstants.PLAYER_SPEED;
import static com.epicness.neoncube.game.GameConstants.RIGHT_KEY;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.SpritedAnimation;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class MovementHandler extends GameLogicHandler {

    private Player player;
    private SpritedAnimation animation;
    private Vector2 speed;

    @Override
    protected void init() {
        player = stuff.getCubeWorld().getPlayer();
        animation = player.animation;
        speed = player.speed;
    }

    @Override
    public void update(float delta) {
        player.translate(speed.cpy().nor().scl(PLAYER_SPEED * delta));
        animation.addTime(delta);
        animation.setFlipX(speed.x < 0);
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case LEFT_KEY:
                speed.x -= 1f;
                break;
            case RIGHT_KEY:
                speed.x += 1f;
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        switch (keycode) {
            case LEFT_KEY:
                speed.x += 1f;
                break;
            case RIGHT_KEY:
                speed.x -= 1f;
                break;
        }
    }
}