package com.epicness.neoncube.game.logic;

import static com.badlogic.gdx.Input.Keys.R;

import com.badlogic.gdx.Gdx;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.logic.grid.GridHandler;
import com.epicness.neoncube.game.logic.other.CameraHandler;
import com.epicness.neoncube.game.logic.other.CylinderScreenSpawner;
import com.epicness.neoncube.game.logic.other.KeyHandler;
import com.epicness.neoncube.game.logic.other.PlaneScreenSpawner;
import com.epicness.neoncube.game.logic.other.RayHandler;
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
    private final WorldCornerHandler worldCornerHandler;

    public GameLogic() {
        // Grid
        registerHandler(gridHandler = new GridHandler());
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
        registerHandler(0, new KeyHandler());
        registerHandler(new PlaneScreenSpawner());
        registerHandler(new RayHandler());
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
        worldCornerHandler.update();

        if (Gdx.input.isKeyJustPressed(R)) {
            restart();
        }
    }
}