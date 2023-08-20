package com.epicness.neoncube.game.logic.other;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants3D.CYLINDER_DIAMETER;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.constants.Constants3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.CylinderProperties;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.CylinderWrapper;
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

        Material material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
            FloatAttribute.createAlphaTest(0.5f),
            IntAttribute.createCullFace(GL20.GL_NONE)
        );

        CylinderProperties properties = new CylinderProperties(
            CYLINDER_DIAMETER,
            DECAL_SCREEN_HEIGHT,
            CYLINDER_DIAMETER,
            0f,
            90f,
            10,
            material,
            Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES
        );
        CylinderWrapper wrapper = new CylinderWrapper(properties);

        CylinderScreen cylinder1 = new CylinderScreen(wrapper, stickmanWorld, 1);
        cylinder1.translate(DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);

        CylinderScreen cylinder2 = new CylinderScreen(wrapper, stickmanWorld, 3);
        cylinder2.translate(DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder2.rotateY(90f);

        CylinderScreen cylinder3 = new CylinderScreen(wrapper, stickmanWorld, 5);
        cylinder3.translate(-DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder3.rotateY(180f);

        CylinderScreen cylinder4 = new CylinderScreen(wrapper, stickmanWorld, 7);
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