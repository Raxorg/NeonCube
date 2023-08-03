package com.epicness.neoncube.game.logic;

import static com.badlogic.gdx.Input.Keys.R;

import com.badlogic.gdx.Gdx;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.logic.player.LadderDetector;
import com.epicness.neoncube.game.logic.player.PlatformDetector;
import com.epicness.neoncube.game.logic.player.PlayerSpawner;
import com.epicness.neoncube.game.logic.player.movement.ClimbingHandler;
import com.epicness.neoncube.game.logic.player.movement.FallingHandler;
import com.epicness.neoncube.game.logic.player.movement.IdleHandler;
import com.epicness.neoncube.game.logic.player.movement.MovementHandler;
import com.epicness.neoncube.game.logic.player.movement.RunningHandler;

public class GameLogic extends Logic {

    // Player movement
    private final MovementHandler movementHandler;
    // Player
    private final LadderDetector ladderDetector;
    private final PlatformDetector platformDetector;
    // Other
    private final CameraHandler cameraHandler;

    public GameLogic() {
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
        registerHandler(0, new KeyHandler());
    }

    @Override
    public void update() {
        // Player movement
        movementHandler.update();
        // Player
        ladderDetector.update();
        platformDetector.update();
        // Other
        cameraHandler.update();
        if (Gdx.input.isKeyJustPressed(R)) {
            restart();
        }
    }
}