package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.UP_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;
import static com.epicness.neoncube.game.constants.PlayerStatus.RUNNING;

import com.badlogic.gdx.math.Vector2;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class IdleHandler extends GameLogicHandler {

    private Player player;
    private Vector2 playerSpeed;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        playerSpeed = player.speed;
    }

    @Override
    public void keyDown(int keycode) {
        if (player.getStatus() != IDLE) return;

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x -= PLAYER_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case RIGHT_KEY:
                playerSpeed.x += PLAYER_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case UP_KEY:
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (player.getStatus() != IDLE) return;

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x += PLAYER_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case RIGHT_KEY:
                playerSpeed.x -= PLAYER_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case UP_KEY:
                break;
        }
    }
}