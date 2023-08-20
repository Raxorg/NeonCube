package com.epicness.neoncube.game.logic.player.movement;

import static com.epicness.neoncube.game.constants.PlayerStatus.IDLE;

import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.Player;

public class MovementHandler extends GameLogicHandler {

    private Player player;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        player.speed.setZero();
        player.setStatus(IDLE);
    }

    @Override
    public void update(float delta) {
        switch (player.getStatus()) {
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
}