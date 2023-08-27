package com.epicness.neoncube.game.logic.other;

import static com.epicness.fundamentals.constants.Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
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
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.Plane;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.PlaneCreator;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.PlaneProperties;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;
import com.epicness.neoncube.game.stuff.tridimensional.StickmanPlane;

public class PlaneScreenSpawner extends GameLogicHandler {

    private DelayedRemovalArray<Screen3D<Plane>> screens;

    @Override
    protected void init() {
        StickmanWorld stickmanWorld = stuff.getStickmanWorld();
        screens = stuff.getPlaneScreens();
        screens.clear();

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
        PlaneCreator creator = new PlaneCreator(properties);

        StickmanPlane planeScreen1 = new StickmanPlane(new Plane(creator), 0f, 0f, stickmanWorld);
        planeScreen1.shape.translateZ(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);

        StickmanPlane planeScreen2 = new StickmanPlane(new Plane(creator), CAMERA_WIDTH * 2f, 0f, stickmanWorld);
        planeScreen2.shape.translateX(DECAL_CUBE_XZ_RADIUS + CYLINDER_RADIUS);
        planeScreen2.shape.rotateY(90f);

        StickmanPlane planeScreen3 = new StickmanPlane(new Plane(creator), CAMERA_WIDTH * 4f, 0f, stickmanWorld);
        planeScreen3.shape.translateZ(-DECAL_CUBE_XZ_RADIUS - CYLINDER_RADIUS);
        planeScreen3.shape.rotateY(180f);

        StickmanPlane planeScreen4 = new StickmanPlane(new Plane(creator), CAMERA_WIDTH * 6f, 0f, stickmanWorld);
        planeScreen4.shape.translateX(-DECAL_CUBE_XZ_RADIUS - CYLINDER_RADIUS);
        planeScreen4.shape.rotateY(270f);

        screens.add(planeScreen1);
        screens.add(planeScreen2);
        screens.add(planeScreen3);
        screens.add(planeScreen4);
    }

    @Override
    protected void update(float delta) {
        for (int i = 0; i < screens.size; i++) {

        }
    }
}