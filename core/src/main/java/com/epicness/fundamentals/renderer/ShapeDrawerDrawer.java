package com.epicness.fundamentals.renderer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.interfaces.Drawer;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class ShapeDrawerDrawer extends ShapeDrawer implements Drawer {

    public ShapeDrawerDrawer(Batch batch, Sprite sprite) {
        super(batch, sprite);
    }
}
