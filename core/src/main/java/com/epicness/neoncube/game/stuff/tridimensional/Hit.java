package com.epicness.neoncube.game.stuff.tridimensional;

import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Hit {

    public final Vector3 location;
    public final Shape3D shape;

    public Hit(Vector3 location, Shape3D shape) {
        this.location = location;
        this.shape = shape;
    }
}