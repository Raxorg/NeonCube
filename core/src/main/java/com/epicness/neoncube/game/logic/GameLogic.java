package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.logic.player.LadderDetector;
import com.epicness.neoncube.game.logic.player.PlayerSpawner;
import com.epicness.neoncube.game.logic.player.movement.IdleHandler;
import com.epicness.neoncube.game.logic.player.movement.MovementHandler;
import com.epicness.neoncube.game.logic.player.movement.RunningHandler;

public class GameLogic extends Logic {

    // Player movement
    private final MovementHandler movementHandler;
    // Player
    private final LadderDetector ladderDetector;
    // Other
    private final CameraHandler cameraHandler;

    public GameLogic() {
        // Player movement
        registerHandler(new IdleHandler());
        registerHandler(movementHandler = new MovementHandler());
        registerHandler(new RunningHandler());
        // Player
        registerHandler(ladderDetector = new LadderDetector());
        registerHandler(new PlayerSpawner());
        // Other
        registerHandler(cameraHandler = new CameraHandler());
    }

    @Override
    public void update() {
        // Player movement
        movementHandler.update();
        // Player
        ladderDetector.update();
        // Other
        cameraHandler.update();
    }
}