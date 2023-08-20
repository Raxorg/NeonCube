package com.epicness.neoncube.game.logic.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class ShapeRotator extends GameLogicHandler {

    private DelayedRemovalArray<CylinderScreen> cylinderScreens;
    private DelayedRemovalArray<PlaneScreen> planeScreens;
    private int shapeIndex;

    @Override
    protected void init() {
        cylinderScreens = stuff.getCylinderScreens();
        planeScreens = stuff.getPlaneScreens();
        shapeIndex = 0;
    }

    @Override
    protected void update(float delta) {
        for (shapeIndex = 0; shapeIndex < cylinderScreens.size; shapeIndex++) {
            cylinderScreens.get(shapeIndex).rotateY(delta);
        }
        for (shapeIndex = 0; shapeIndex < planeScreens.size; shapeIndex++) {
            planeScreens.get(shapeIndex).rotateY(delta);
        }
    }
}