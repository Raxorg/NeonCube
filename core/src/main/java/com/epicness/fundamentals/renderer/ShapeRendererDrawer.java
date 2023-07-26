package com.epicness.fundamentals.renderer;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.epicness.fundamentals.stuff.interfaces.Drawer;

public class ShapeRendererDrawer extends ShapeRenderer implements Drawer {

    public void rect(Rectangle rectangle) {
        rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}