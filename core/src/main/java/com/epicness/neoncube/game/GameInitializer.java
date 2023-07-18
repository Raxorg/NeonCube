package com.epicness.neoncube.game;

import com.epicness.fundamentals.initializer.Initializer;
import com.epicness.neoncube.game.assets.GameAssets;
import com.epicness.neoncube.game.logic.GameLogic;
import com.epicness.neoncube.game.stuff.GameStuff;

public class GameInitializer extends Initializer<GameAssets, GameRenderer, GameStuff> {

    public GameInitializer() {
        super(new GameAssets(), new GameLogic(), new GameRenderer(), new GameStuff());
    }
}