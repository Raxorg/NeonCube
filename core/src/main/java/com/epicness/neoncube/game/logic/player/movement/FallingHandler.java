package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_RUNNING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.FALLING;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;
import static com.epicness.neoncube.game.constants.PlayerStatus.RUNNING;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class FallingHandler extends GameLogicHandler {

    private Player player;
    private Vector2 playerSpeed;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        playerSpeed = player.speed;
    }

    @Override
    protected void update(float delta) {
        if (player.getStatus() != FALLING) return;

        playerSpeed.y -= 1000f * delta;
        player.translate(playerSpeed.cpy().scl(delta));

        if (player.getY() <= 0f) {
            player.setY(0f);
            playerSpeed.y = 0f;
            if (playerSpeed.x != 0f) player.setStatus(RUNNING);
            else player.setStatus(IDLE);
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (player.getStatus() != FALLING) return;

        DelayedRemovalArray<Integer> pressedKeys = logic.get(MovementHandler.class).getPressedKeys();
        pressedKeys.add(keycode);

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
        if (player.getStatus() != FALLING) return;

        DelayedRemovalArray<Integer> pressedKeys = logic.get(MovementHandler.class).getPressedKeys();
        pressedKeys.removeValue(keycode, true);

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