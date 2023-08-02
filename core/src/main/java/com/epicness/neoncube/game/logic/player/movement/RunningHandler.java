package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_RUNNING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;
import static com.epicness.neoncube.game.constants.PlayerStatus.RUNNING;

import com.badlogic.gdx.math.Vector2;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class RunningHandler extends GameLogicHandler {

    private Player player;
    private Vector2 playerSpeed;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        playerSpeed = player.speed;
    }

    @Override
    protected void update(float delta) {
        if (player.getStatus() != RUNNING) return;

        player.currentAnimation.addTime(delta);
        player.currentAnimation.setFlipX(playerSpeed.x < 0f);
        player.translateX(playerSpeed.cpy().scl(delta).x);

        if (playerSpeed.x == 0f) {
            player.setStatus(IDLE);
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (player.getStatus() != RUNNING) return;

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x -= PLAYER_RUNNING_SPEED;
                break;
            case RIGHT_KEY:
                playerSpeed.x += PLAYER_RUNNING_SPEED;
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (player.getStatus() != RUNNING) return;

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x += PLAYER_RUNNING_SPEED;
                break;
            case RIGHT_KEY:
                playerSpeed.x -= PLAYER_RUNNING_SPEED;
                break;
        }
    }
}