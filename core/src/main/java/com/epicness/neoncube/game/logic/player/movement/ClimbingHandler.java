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
import com.epicness.neoncube.game.logic.player.LadderDetector;
import com.epicness.neoncube.game.stuff.Ladder;
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
        player.translate(playerSpeed.cpy().scl(delta));

        Ladder ladder = logic.get(LadderDetector.class).getDetectedLadder();
        // Player leaves ladder horizontally
        if (ladder == null) {
            leaveLadder();
            player.setStatus(FALLING);
            player.currentAnimation.setFlipX(playerSpeed.x < 0f);
            return;
        }
        // Player reaches bottom of the ladder
        if (player.getY() <= ladder.getY()) {
            player.setY(ladder.getY());
            leaveLadder();
        }
        // Player reaches top of the ladder
        if (player.getY() > ladder.getTopY()) {
            player.setY(ladder.getTopY());
            leaveLadder();
        }
    }

    private void leaveLadder() {
        playerSpeed.y = 0f;
        if (playerSpeed.x > 0f) {
            playerSpeed.x = PLAYER_RUNNING_SPEED;
            player.setStatus(RUNNING);
        } else if (playerSpeed.x < 0f) {
            playerSpeed.x = -PLAYER_RUNNING_SPEED;
            player.setStatus(RUNNING);
        } else player.setStatus(IDLE);
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
            case LEFT_KEY:
                playerSpeed.x -= PLAYER_CLIMBING_SPEED;
                break;
            case RIGHT_KEY:
                playerSpeed.x += PLAYER_CLIMBING_SPEED;
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
            case LEFT_KEY:
                playerSpeed.x += PLAYER_CLIMBING_SPEED;
                break;
            case RIGHT_KEY:
                playerSpeed.x -= PLAYER_CLIMBING_SPEED;
                break;
        }
    }
}