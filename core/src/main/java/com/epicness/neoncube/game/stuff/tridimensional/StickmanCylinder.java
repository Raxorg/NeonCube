package com.epicness.neoncube.game.stuff.tridimensional;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.Cylinder;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;

public class StickmanCylinder extends Screen3D<Cylinder> {

    private final StickmanWorld stickmanWorld;

    public StickmanCylinder(Cylinder shape, float cameraX, float cameraY, StickmanWorld stickmanWorld) {
        super(shape, cameraX, cameraY, CAMERA_WIDTH, CAMERA_HEIGHT);
        this.stickmanWorld = stickmanWorld;
    }

    @Override
    protected void draw2D(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        stickmanWorld.draw(spriteBatch);
    }
}