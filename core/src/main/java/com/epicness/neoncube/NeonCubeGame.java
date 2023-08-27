package com.epicness.neoncube;

import com.badlogic.gdx.Game;
import com.epicness.fundamentals.SharedResources;
import com.epicness.neoncube.menu.MenuInitializer;
import com.epicness.neoncube.menu.assets.MenuAssets;

public class NeonCubeGame extends Game {

    @Override
    public void create() {
        MenuAssets menuAssets = new MenuAssets();
        menuAssets.queueAssetLoading();
        menuAssets.finishLoading();
        menuAssets.initAssets();
        new MenuInitializer(menuAssets).initialize(new SharedResources());
    }
}