package com.epicness.neoncube.menu;

import com.epicness.fundamentals.initializer.Initializer;
import com.epicness.neoncube.menu.assets.MenuAssets;
import com.epicness.neoncube.menu.logic.MenuLogic;

public class MenuInitializer extends Initializer<MenuAssets, MenuRenderer, MenuStuff> {

    public MenuInitializer(MenuAssets menuAssets) {
        super(menuAssets, new MenuLogic(), new MenuRenderer(), new MenuStuff());
    }
}