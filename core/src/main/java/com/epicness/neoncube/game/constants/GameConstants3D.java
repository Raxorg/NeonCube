package com.epicness.neoncube.game.constants;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class GameConstants3D {

    public static final float CYLINDER_RADIUS = (DECAL_SCREEN_WIDTH / (2 * MathUtils.PI)) * 4f;
    public static final float CYLINDER_DIAMETER = CYLINDER_RADIUS * 2f;
}