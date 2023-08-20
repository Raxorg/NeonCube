package com.epicness.neoncube.game.logic.other;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class ShapeRotator extends GameLogicHandler {

    private int shapeIndex;
    private DelayedRemovalArray<CylinderScreen> cylinderScreens;
    private DelayedRemovalArray<PlaneScreen> planeScreens;

    @Override
    protected void init() {
        shapeIndex = 0;
        cylinderScreens = stuff.getCylinderScreens();
        planeScreens = stuff.getPlaneScreens();
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