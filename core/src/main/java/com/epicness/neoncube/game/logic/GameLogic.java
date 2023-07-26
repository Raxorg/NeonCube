package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.menu.MenuInitializer;
import com.epicness.neoncube.menu.assets.MenuAssets;

public class GameLogic extends Logic {

    private final CameraHandler cameraHandler;
    private final MovementHandler movementHandler;

    public GameLogic() {
        registerHandler(cameraHandler = new CameraHandler());
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
        movementHandler.update(delta);
    }
}