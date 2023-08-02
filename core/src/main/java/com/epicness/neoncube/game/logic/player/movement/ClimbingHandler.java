package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.DOWN_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_CLIMBING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.UP_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.CLIMBING;

import com.badlogic.gdx.math.Vector2;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class ClimbingHandler extends GameLogicHandler {

    private Player player;
    private Vector2 playerSpeed;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        playerSpeed = player.speed;
    }

    @Override
    protected void update(float delta) {
        if (player.getStatus() != CLIMBING) return;

        if (playerSpeed.y != 0f) player.currentAnimation.addTime(delta);
        player.translateY(playerSpeed.cpy().scl(delta).y);
    }

    @Override
    public void keyDown(int keycode) {
        if (player.getStatus() != CLIMBING) return;

        switch (keycode) {
            case UP_KEY:
                playerSpeed.y += PLAYER_CLIMBING_SPEED;
                break;
            case DOWN_KEY:
                playerSpeed.y -= PLAYER_CLIMBING_SPEED;
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (player.getStatus() != CLIMBING) return;

        switch (keycode) {
            case UP_KEY:
                playerSpeed.y -= PLAYER_CLIMBING_SPEED;
                break;
            case DOWN_KEY:
                playerSpeed.y += PLAYER_CLIMBING_SPEED;
                break;
        }
    }
}