package com.epicness.neoncube.game.logic.other;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants3D.CYLINDER_RADIUS;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Plane.PlaneBuilder;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class PlaneScreenSpawner extends GameLogicHandler {

    private DelayedRemovalArray<PlaneScreen> planeScreens;

    @Override
    protected void init() {
        StickmanWorld stickmanWorld = stuff.getStickmanWorld();
        planeScreens = stuff.getPlaneScreens();
        planeScreens.clear();

        PlaneBuilder builder = new PlaneBuilder()
            .width(DECAL_SCREEN_WIDTH)
            .height(DECAL_SCREEN_HEIGHT)
            .disableLight();

        PlaneScreen planeScreen1 = new PlaneScreen(builder, stickmanWorld, 0);
        planeScreen1.translateZ(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);

        PlaneScreen planeScreen2 = new PlaneScreen(builder, stickmanWorld, 2);
        planeScreen2.translateX(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);
        planeScreen2.rotateY(90f);

        PlaneScreen planeScreen3 = new PlaneScreen(builder, stickmanWorld, 4);
        planeScreen3.translateZ(-DECAL_CUBE_XZ_RADIUS - CYLINDER_RADIUS);
        planeScreen3.rotateY(180f);

        PlaneScreen planeScreen4 = new PlaneScreen(builder, stickmanWorld, 6);
        planeScreen4.translateX(-DECAL_CUBE_XZ_RADIUS - CYLINDER_RADIUS);
        planeScreen4.rotateY(270f);

        planeScreens.add(planeScreen1);
        planeScreens.add(planeScreen2);
        planeScreens.add(planeScreen3);
        planeScreens.add(planeScreen4);
    }

    @Override
    protected void update(float delta) {
        for (int i = 0; i < planeScreens.size; i++) {

        }
    }
}