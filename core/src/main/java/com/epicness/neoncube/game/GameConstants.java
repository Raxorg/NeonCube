package com.epicness.neoncube.game;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.W;
import static com.epicness.fundamentals.SharedConstants.RATIO;

public class GameConstants {

    public static final float TRIANGLE_SPEED = 1000f;

    public static final int UP_KEY = W;
    public static final int LEFT_KEY = A;
    public static final int DOWN_KEY = S;
    public static final int RIGHT_KEY = D;

    public static final float DECAL_SCREEN_HEIGHT = 5f;
    public static final float DECAL_SCREEN_WIDTH = DECAL_SCREEN_HEIGHT * RATIO;
}