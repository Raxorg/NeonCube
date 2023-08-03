package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.DOWN_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_CLIMBING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_RUNNING_SPEED;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.UP_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.CLIMBING;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;
import static com.epicness.neoncube.game.constants.PlayerStatus.RUNNING;

import com.badlogic.gdx.math.Vector2;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.logic.KeyHandler;
import com.epicness.neoncube.game.logic.player.LadderDetector;
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

        KeyHandler keyHandler = logic.get(KeyHandler.class);
        LadderDetector ladderDetector = logic.get(LadderDetector.class);

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x -= PLAYER_RUNNING_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case RIGHT_KEY:
                playerSpeed.x += PLAYER_RUNNING_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case UP_KEY:
                if (keyHandler.isPressed(DOWN_KEY)) break;

                if (ladderDetector.isLadderDetected()) {
                    playerSpeed.y += PLAYER_CLIMBING_SPEED;
                    player.setStatus(CLIMBING);
                    consumeInput();
                }
                break;
            case DOWN_KEY:
                if (keyHandler.isPressed(UP_KEY)) break;

                if (ladderDetector.isLadderDetected() && ladderDetector.getDetectedLadder().getY() != player.getY()) {
                    playerSpeed.y -= PLAYER_CLIMBING_SPEED;
                    player.setStatus(CLIMBING);
                    consumeInput();
                }
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (player.getStatus() != IDLE) return;

        KeyHandler keyHandler = logic.get(KeyHandler.class);
        LadderDetector ladderDetector = logic.get(LadderDetector.class);

        switch (keycode) {
            case LEFT_KEY:
                playerSpeed.x += PLAYER_RUNNING_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case RIGHT_KEY:
                playerSpeed.x -= PLAYER_RUNNING_SPEED;
                player.setStatus(RUNNING);
                consumeInput();
                break;
            case UP_KEY:
                break;
            case DOWN_KEY:
                if (keyHandler.isPressed(UP_KEY) && ladderDetector.isLadderDetected()) {
                    playerSpeed.y += PLAYER_CLIMBING_SPEED;
                    player.setStatus(CLIMBING);
                    consumeInput();
                }
                break;
        }
    }
}