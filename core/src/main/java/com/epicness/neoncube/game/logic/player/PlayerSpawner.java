package com.epicness.neoncube.game.logic.player;

import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_STARTING_X;

import com.epicness.neoncube.game.logic.GameLogicHandler;

public class PlayerSpawner extends GameLogicHandler {

    @Override
    protected void init() {
        stuff.getStickmanWorld().player.setPosition(PLAYER_STARTING_X, 0f);
        logic.get(LadderDetector.class).detectLadder();
    }
}