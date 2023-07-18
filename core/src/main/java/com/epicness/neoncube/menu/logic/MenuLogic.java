package com.epicness.neoncube.menu.logic;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.neoncube.game.GameInitializer;

public class MenuLogic extends Logic {

    public MenuLogic() {

    }

    @Override
    public void initialLogic() {
        super.initialLogic();
        sharedLogic.getTransitionHandler().startTransition(
                () -> sharedLogic.getTransitionHandler().allowTransition(),
                new GameInitializer()
        );
    }

    @Override
    public void update(float delta) {
        sharedLogic.getTransitionHandler().update();
    }
}