package com.epicness.fundamentals.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.WireframeCube;

public class CollisionUtils {

    public static boolean overlaps(float x, float y, float width, float height, Rectangle r) {
        return x <= r.x + r.width && x + width >= r.x && y <= r.y + r.height && y + height >= r.y;
    }

    public static boolean overlaps(Rectangle a, Rectangle b) {
        return overlaps(a.x, a.y, a.width, a.height, b);
    }

    public static boolean intersects(Ray ray, Shape3D<?, ?> shape, Vector3 intersection) {
        return Intersector.intersectRayTriangles(ray, shape.getVertices(), shape.getIndices(), 3, intersection);
    }

    public static boolean intersects(Ray ray, WireframeCube wireframeCube, Vector3 intersection) {
        return Intersector.intersectRayOrientedBounds(ray, wireframeCube.orientedBoundingBox, intersection);
    }

    public static boolean intersects(Line3D line, WireframeCube wireframeCube, Vector3 intersection) {
        return intersects(line.getRay(), wireframeCube, intersection);
    }
}