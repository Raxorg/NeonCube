package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.DOWN_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.GRAVITY;
import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_CLIMBING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_RUNNING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.UP_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.CLIMBING;
import static com.epicness.neoncube.game.constants.PlayerStatus.FALLING;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;
import static com.epicness.neoncube.game.constants.PlayerStatus.RUNNING;

import com.badlogic.gdx.math.Vector2;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.logic.KeyHandler;
import com.epicness.neoncube.game.logic.player.LadderDetector;
import com.epicness.neoncube.game.stuff.Ladder;
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

        playerSpeed.y += GRAVITY * delta;
        player.translate(playerSpeed.cpy().scl(delta));

        checkLadder();

        if (player.getY() <= 0f) {
            player.setY(0f);
            playerSpeed.y = 0f;
            if (playerSpeed.x != 0f) player.setStatus(RUNNING);
            else player.setStatus(IDLE);
        }
    }

    private void checkLadder() {
        if (!logic.get(LadderDetector.class).isLadderDetected()) return;

        Ladder ladder = logic.get(LadderDetector.class).getDetectedLadder();
        KeyHandler keyHandler = logic.get(KeyHandler.class);

        float playerSpeedX = playerSpeed.x == 0f ? 0f : PLAYER_CLIMBING_SPEED * (playerSpeed.x / Math.abs(playerSpeed.x));
        if (keyHandler.isPressed(UP_KEY) && player.getY() != ladder.getTopY()) {
            playerSpeed.x = playerSpeedX;
            playerSpeed.y = PLAYER_CLIMBING_SPEED;
            player.setStatus(CLIMBING);
        } else if (keyHandler.isPressed(DOWN_KEY) && player.getY() != ladder.getY()) {
            playerSpeed.x = playerSpeedX;
            playerSpeed.y = -PLAYER_CLIMBING_SPEED;
            player.setStatus(CLIMBING);
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (player.getStatus() != FALLING) return;

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