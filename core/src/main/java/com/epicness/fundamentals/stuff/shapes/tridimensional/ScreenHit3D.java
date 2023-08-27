package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.math.Vector3;

public record ScreenHit3D(Vector3 location, Screen3D<?> screen) {
}