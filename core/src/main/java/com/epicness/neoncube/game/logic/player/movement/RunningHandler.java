package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.DOWN_KEY;
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
import com.epicness.neoncube.game.logic.player.PlatformDetector;
import com.epicness.neoncube.game.stuff.Ladder;
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

        checkLadder();

        if (!logic.get(PlatformDetector.class).isGrounded()) {
            player.setStatus(FALLING);
        }

        if (playerSpeed.x == 0f) {
            player.setStatus(IDLE);
        }
    }

    private void checkLadder() {
        if (!logic.get(LadderDetector.class).isLadderDetected()) return;

        Ladder ladder = logic.get(LadderDetector.class).getDetectedLadder();
        KeyHandler keyHandler = logic.get(KeyHandler.class);

        if (keyHandler.isPressed(UP_KEY) && player.getY() != ladder.getTopY()) {
            playerSpeed.x = playerSpeed.x > 0 ? PLAYER_CLIMBING_SPEED : -PLAYER_CLIMBING_SPEED;
            playerSpeed.y = PLAYER_CLIMBING_SPEED;
            player.setStatus(CLIMBING);
        } else if (keyHandler.isPressed(DOWN_KEY) && player.getY() != ladder.getY()) {
            playerSpeed.x = playerSpeed.x > 0 ? PLAYER_CLIMBING_SPEED : -PLAYER_CLIMBING_SPEED;
            playerSpeed.y = -PLAYER_CLIMBING_SPEED;
            player.setStatus(CLIMBING);
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