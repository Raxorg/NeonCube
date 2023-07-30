package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.logic.player.MovementHandler;
import com.epicness.neoncube.game.logic.player.PlayerSpawner;

public class GameLogic extends Logic {

    // Player
    private final MovementHandler movementHandler;
    // Other
    private final CameraHandler cameraHandler;

    public GameLogic() {
        // Player
        registerHandler(movementHandler = new MovementHandler());
        registerHandler(new PlayerSpawner());
        // Other
        registerHandler(cameraHandler = new CameraHandler());
    }

    @Override
    public void update(float delta) {
        movementHandler.update(delta);
        cameraHandler.update();
    }
}