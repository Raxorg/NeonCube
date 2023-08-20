package com.epicness.neoncube.game.logic.other;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants3D.CYLINDER_DIAMETER;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Cylinder.CylinderBuilder;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;

public class CylinderScreenSpawner extends GameLogicHandler {

    private DelayedRemovalArray<CylinderScreen> cylinderScreens;

    @Override
    protected void init() {
        StickmanWorld stickmanWorld = stuff.getStickmanWorld();
        cylinderScreens = stuff.getCylinderScreens();
        cylinderScreens.clear();

        CylinderBuilder builder = new CylinderBuilder()
            .width(CYLINDER_DIAMETER)
            .height(DECAL_SCREEN_HEIGHT)
            .depth(CYLINDER_DIAMETER)
            .disableLight()
            .angleTo(90f);

        CylinderScreen cylinder1 = new CylinderScreen(builder, stickmanWorld, 1);
        cylinder1.translate(DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);

        CylinderScreen cylinder2 = new CylinderScreen(builder, stickmanWorld, 3);
        cylinder2.translate(DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder2.rotateY(90f);

        CylinderScreen cylinder3 = new CylinderScreen(builder, stickmanWorld, 5);
        cylinder3.translate(-DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder3.rotateY(180f);

        CylinderScreen cylinder4 = new CylinderScreen(builder, stickmanWorld, 7);
        cylinder4.translate(-DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);
        cylinder4.rotateY(270f);

        cylinderScreens.add(cylinder1);
        cylinderScreens.add(cylinder2);
        cylinderScreens.add(cylinder3);
        cylinderScreens.add(cylinder4);
    }

    @Override
    protected void update(float delta) {
        for (int i = 0; i < cylinderScreens.size; i++) {

        }
    }
}