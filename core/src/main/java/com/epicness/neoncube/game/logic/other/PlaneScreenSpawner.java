package com.epicness.neoncube.game.logic.other;

import static com.epicness.fundamentals.constants.Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants3D.CYLINDER_RADIUS;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.PlaneProperties;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.PlaneWrapper;
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

        Material material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
            FloatAttribute.createAlphaTest(0.5f),
            IntAttribute.createCullFace(GL20.GL_NONE)
        );

        PlaneProperties properties = new PlaneProperties(
            DECAL_SCREEN_WIDTH,
            DECAL_SCREEN_HEIGHT,
            material,
            LIGHTLESS_TEXTURED_ATTRIBUTES
        );
        PlaneWrapper wrapper = new PlaneWrapper(properties);

        PlaneScreen planeScreen1 = new PlaneScreen(wrapper, stickmanWorld, 0);
        planeScreen1.translateZ(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);

        PlaneScreen planeScreen2 = new PlaneScreen(wrapper, stickmanWorld, 2);
        planeScreen2.translateX(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);
        planeScreen2.rotateY(90f);

        PlaneScreen planeScreen3 = new PlaneScreen(wrapper, stickmanWorld, 4);
        planeScreen3.translateZ(-DECAL_CUBE_XZ_RADIUS - CYLINDER_RADIUS);
        planeScreen3.rotateY(180f);

        PlaneScreen planeScreen4 = new PlaneScreen(wrapper, stickmanWorld, 6);
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