package com.epicness.neoncube.game.logic.other;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
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
            .depth(CYLINDER_DIAMETER)
            .disableLight()
            .angleTo(90f);

        CylinderScreen cylinder1 = new CylinderScreen(builder, stickmanWorld, 1);
        cylinder1.translate(DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);

        builder.angleRange(270f, 360f);
        CylinderScreen cylinder2 = new CylinderScreen(builder, stickmanWorld, 3);
        cylinder2.translate(DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);

        builder.angleRange(180f, 270f);
        CylinderScreen cylinder3 = new CylinderScreen(builder, stickmanWorld, 5);
        cylinder3.translate(-DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);

        builder.angleRange(90f, 180f);
        CylinderScreen cylinder4 = new CylinderScreen(builder, stickmanWorld, 7);
        cylinder4.translate(-DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);

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