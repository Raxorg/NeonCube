package com.epicness.fundamentals.logic.collision3d;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Hit2D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.Cylinder;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.Plane;
import com.epicness.fundamentals.utils.AngleUtils;

@SuppressWarnings("FieldCanBeLocal")
public class HitCalculator {

    private static float minY, maxY, angle, x, y, xDelta, yDelta, distance;
    private static int sign;
    private static final Vector2 circlePoint = new Vector2();

    private HitCalculator() {
    }

    public static void calculateHit(Shape3D<?, ?> shape3D, float xOffset, float yOffset,
                                    Vector3 intersection, Hit2D hit) {
        if (shape3D instanceof Cylinder) {
            calculateHit((Cylinder) shape3D, xOffset, yOffset, intersection, hit);
        } else if (shape3D instanceof Plane) {
            calculateHit((Plane) shape3D, xOffset, yOffset, intersection, hit);
        }
    }

    private static void calculateHit(Cylinder cylinder, float xOffset, float yOffset,
                                     Vector3 intersection, Hit2D hit) {
        minY = cylinder.getY() - cylinder.getHeight() / 2f;
        maxY = cylinder.getY() + cylinder.getHeight() / 2f;

        circlePoint.set(intersection.x - cylinder.getX(), intersection.z - cylinder.getZ());
        angle = (circlePoint.angleDeg() % 90f + cylinder.getYRotation() % 90f) % 90f;
        x = MathUtils.map(90f, 0f, 0f, CAMERA_WIDTH, angle) + xOffset;
        y = MathUtils.map(minY, maxY, 0f, CAMERA_HEIGHT, intersection.y) + yOffset;

        hit.setLocation(x, y);
    }

    private static void calculateHit(Plane plane, float xOffset, float yOffset,
                                     Vector3 intersection, Hit2D hit) {
        minY = plane.getY() - plane.getHeight() / 2f;
        maxY = plane.getY() + plane.getHeight() / 2f;

        angle = AngleUtils.degreesBetweenPoints(intersection.x, intersection.z, plane.getX(), plane.getZ());
        angle = MathUtils.ceil((angle + 360f + plane.getYRotation())) % 360f;
        sign = 1;
        if (angle > 90f) sign = -1;
        xDelta = plane.getX() - intersection.x;
        yDelta = plane.getZ() - intersection.z;
        distance = (float) Math.sqrt(xDelta * xDelta + yDelta * yDelta) * sign;
        float c = plane.getWidth();
        x = MathUtils.map(-plane.getWidth() / 2f, plane.getWidth() / 2f, 0f, CAMERA_WIDTH, distance) + xOffset;
        y = MathUtils.map(minY, maxY, 0f, CAMERA_HEIGHT, intersection.y) + yOffset;

        hit.setLocation(x, y);
    }
}