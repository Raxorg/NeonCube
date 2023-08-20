package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.logic.grid.GridHandler;
import com.epicness.neoncube.game.logic.hitdetection.CylinderHitCalculator;
import com.epicness.neoncube.game.logic.hitdetection.PlaneHitCalculator;
import com.epicness.neoncube.game.logic.hitdetection.RayHitHandler;
import com.epicness.neoncube.game.logic.other.CameraHandler;
import com.epicness.neoncube.game.logic.other.CylinderScreenSpawner;
import com.epicness.neoncube.game.logic.other.DebugHandler;
import com.epicness.neoncube.game.logic.other.KeyHandler;
import com.epicness.neoncube.game.logic.other.PlaneScreenSpawner;
import com.epicness.neoncube.game.logic.other.ShapeRotator;
import com.epicness.neoncube.game.logic.other.WorldCornerHandler;
import com.epicness.neoncube.game.logic.player.LadderDetector;
import com.epicness.neoncube.game.logic.player.PlatformDetector;
import com.epicness.neoncube.game.logic.player.PlayerSpawner;
import com.epicness.neoncube.game.logic.player.movement.ClimbingHandler;
import com.epicness.neoncube.game.logic.player.movement.FallingHandler;
import com.epicness.neoncube.game.logic.player.movement.IdleHandler;
import com.epicness.neoncube.game.logic.player.movement.MovementHandler;
import com.epicness.neoncube.game.logic.player.movement.RunningHandler;

public class GameLogic extends Logic {

    // Grid
    private final GridHandler gridHandler;
    // Player movement
    private final MovementHandler movementHandler;
    // Player
    private final LadderDetector ladderDetector;
    private final PlatformDetector platformDetector;
    // Other
    private final CameraHandler cameraHandler;
    private final DebugHandler debugHandler;
    private final ShapeRotator shapeRotator;
    private final WorldCornerHandler worldCornerHandler;

    public GameLogic() {
        // Grid
        registerHandler(gridHandler = new GridHandler());
        // Hit detection
        registerHandler(new CylinderHitCalculator());
        registerHandler(new PlaneHitCalculator());
        registerHandler(new RayHitHandler());
        // Player
        registerHandler(ladderDetector = new LadderDetector());
        registerHandler(platformDetector = new PlatformDetector());
        registerHandler(new PlayerSpawner());
        // Player movement
        registerHandler(new ClimbingHandler());
        registerHandler(new FallingHandler());
        registerHandler(new IdleHandler());
        registerHandler(movementHandler = new MovementHandler());
        registerHandler(new RunningHandler());
        // Other
        registerHandler(cameraHandler = new CameraHandler());
        registerHandler(new CylinderScreenSpawner());
        registerHandler(debugHandler = new DebugHandler());
        registerHandler(0, new KeyHandler());
        registerHandler(new PlaneScreenSpawner());
        registerHandler(shapeRotator = new ShapeRotator());
        registerHandler(worldCornerHandler = new WorldCornerHandler());
    }

    @Override
    public void update() {
        // Grid
        gridHandler.update();
        // Player movement
        movementHandler.update();
        // Player
        ladderDetector.update();
        platformDetector.update();
        // Other
        cameraHandler.update();
        debugHandler.update();
        shapeRotator.update();
        worldCornerHandler.update();
    }
}