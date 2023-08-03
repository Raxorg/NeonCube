package com.epicness.fundamentals.utils;

import com.badlogic.gdx.math.Rectangle;

public class CollisionUtils {

    public static boolean overlaps(float x, float y, float width, float height, Rectangle r) {
        return x <= r.x + r.width && x + width >= r.x && y <= r.y + r.height && y + height >= r.y;
    }

    public static boolean overlaps(Rectangle a, Rectangle b) {
        return overlaps(a.x, a.y, a.width, a.height, b);
    }
}