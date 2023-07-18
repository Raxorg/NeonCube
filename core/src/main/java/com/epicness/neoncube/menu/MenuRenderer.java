package com.epicness.neoncube.menu;

import static com.badlogic.gdx.graphics.Color.RED;

import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.Renderer;

public class MenuRenderer extends Renderer<MenuStuff> {

    @Override
    public void render() {
        ScreenUtils.clear(RED);
    }
}