package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.menu.MenuInitializer;
import com.epicness.neoncube.menu.assets.MenuAssets;

public class GameLogic extends Logic {

    private final CameraHandler cameraHandler;
    private final DecalMover decalMover;
    private final MovementHandler movementHandler;

    public GameLogic() {
        registerHandler(cameraHandler = new CameraHandler());
        registerHandler(decalMover = new DecalMover());
        registerHandler(movementHandler = new MovementHandler());
    }

    @Override
    public void initialLogic() {
        super.initialLogic();
        sharedLogic.getTransitionHandler().startTransition(() -> {
        }, new MenuInitializer(new MenuAssets()));
    }

    @Override
    public void update(float delta) {
        sharedLogic.getTransitionHandler().update();
        cameraHandler.update();
        decalMover.update(delta);
        movementHandler.update(delta);
    }
}