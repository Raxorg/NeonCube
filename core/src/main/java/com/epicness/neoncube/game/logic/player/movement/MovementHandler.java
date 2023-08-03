package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.GameConstants.DOWN_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.LEFT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.RIGHT_KEY;
import static com.epicness.neoncube.game.constants.GameConstants.UP_KEY;
import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.constants.PlayerStatus;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class MovementHandler extends GameLogicHandler {

    private Player player;
    private DelayedRemovalArray<Integer> pressedKeys;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        player.speed.setZero();
        player.setStatus(IDLE);
        pressedKeys = new DelayedRemovalArray<>();
        checkKey(UP_KEY);
        checkKey(DOWN_KEY);
        checkKey(LEFT_KEY);
        checkKey(RIGHT_KEY);
    }

    private void checkKey(int keycode) {
        if (Gdx.input.isKeyPressed(keycode)) {
            logic.get(IdleHandler.class).keyDown(keycode);
        }
    }

    @Override
    public void update(float delta) {
        PlayerStatus status = player.getStatus();
        switch (status) {
            case IDLE:
                logic.get(IdleHandler.class).update();
                break;
            case RUNNING:
                logic.get(RunningHandler.class).update();
                break;
            case CLIMBING:
                logic.get(ClimbingHandler.class).update();
                break;
            case FALLING:
                logic.get(FallingHandler.class).update();
                break;
        }
    }

    public DelayedRemovalArray<Integer> getPressedKeys() {
        return pressedKeys;
    }
}