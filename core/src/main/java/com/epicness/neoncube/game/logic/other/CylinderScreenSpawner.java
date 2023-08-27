package com.epicness.neoncube.game.logic.other;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants3D.CYLINDER_DIAMETER;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.constants.Constants3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.Cylinder;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.CylinderCreator;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.CylinderProperties;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;
import com.epicness.neoncube.game.stuff.tridimensional.StickmanCylinder;

public class CylinderScreenSpawner extends GameLogicHandler {

    private DelayedRemovalArray<Screen3D<Cylinder>> screens;

    @Override
    protected void init() {
        StickmanWorld stickmanWorld = stuff.getStickmanWorld();
        screens = stuff.getCylinderScreens();
        screens.clear();

        Material material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
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
        CylinderCreator creator = new CylinderCreator(properties);

        StickmanCylinder cylinder1 = new StickmanCylinder(new Cylinder(creator), CAMERA_WIDTH, 0f, stickmanWorld);
        cylinder1.shape.translate(DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);

        StickmanCylinder cylinder2 = new StickmanCylinder(new Cylinder(creator), CAMERA_WIDTH * 3f, 0f, stickmanWorld);
        cylinder2.shape.translate(DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder2.shape.rotateY(90f);

        StickmanCylinder cylinder3 = new StickmanCylinder(new Cylinder(creator), CAMERA_WIDTH * 5f, 0f, stickmanWorld);
        cylinder3.shape.translate(-DECAL_CUBE_XZ_RADIUS, 0f, -DECAL_CUBE_XZ_RADIUS);
        cylinder3.shape.rotateY(180f);

        StickmanCylinder cylinder4 = new StickmanCylinder(new Cylinder(creator), CAMERA_WIDTH * 7f, 0f, stickmanWorld);
        cylinder4.shape.translate(-DECAL_CUBE_XZ_RADIUS, 0f, DECAL_CUBE_XZ_RADIUS);
        cylinder4.shape.rotateY(270f);

        screens.add(cylinder1);
        screens.add(cylinder2);
        screens.add(cylinder3);
        screens.add(cylinder4);
    }

    @Override
    protected void update(float delta) {
        for (int i = 0; i < screens.size; i++) {

        }
    }
}