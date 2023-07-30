package com.epicness.neoncube.game.logic.player;

import static com.epicness.neoncube.game.GameConstants.PLAYER_STARTING_X;

import com.epicness.neoncube.game.logic.GameLogicHandler;

public class PlayerSpawner extends GameLogicHandler {

    @Override
    protected void init() {
        stuff.getCubeWorld().getPlayer().setX(PLAYER_STARTING_X);
    }
}