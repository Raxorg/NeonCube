package com.epicness.fundamentals.logic.collision3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Hit2D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ScreenHit3D;
import com.epicness.fundamentals.utils.CollisionUtils;

public class RayHitHandler {

    private final Line3D line;
    private float lineLength;
    private final DelayedRemovalArray<Screen3D<?>> screens;
    private final DelayedRemovalArray<ScreenHit3D> hits;
    private final Hit2D hit;
    // Optimization
    private final Vector3 aux;
    private Screen3D<?> auxScreen;

    public RayHitHandler() {
        line = new Line3D();
        lineLength = 50f;
        screens = new DelayedRemovalArray<>();
        hits = new DelayedRemovalArray<>();
        aux = new Vector3();
        hit = new Hit2D();
    }

    public void setRayLength(float lineLength) {
        this.lineLength = lineLength;
    }

    public void registerScreens(Screen3D<?>... screensToRegister) {
        screens.addAll(screensToRegister);
    }

    public Hit2D calculateHit(PerspectiveCamera camera) {
        calculateHits(camera.getPickRay(Gdx.input.getX(), Gdx.input.getY()));
        if (hits.notEmpty()) return processClosestHit(camera);
        return null;
    }

    private void calculateHits(Ray ray) {
        line.set(ray.origin, ray.direction.cpy().scl(lineLength).add(ray.origin));

        hits.clear();
        hits.begin();
        Screen3D<?> screen;
        for (int i = 0; i < screens.size; i++) {
            screen = screens.get(i);
            if (CollisionUtils.intersects(ray, screen.shape, aux)) {
                hits.add(new ScreenHit3D(aux.cpy(), screen));
            }
        }
        hits.end();
    }

    private Hit2D processClosestHit(PerspectiveCamera camera) {
        ScreenHit3D closest = hits.first();
        if (hits.size > 1) {
            for (int i = 0; i < hits.size; i++) {
                if (hits.get(i).location().dst(camera.position) < closest.location().dst(camera.position)) {
                    closest = hits.get(i);
                }
            }
        }
        auxScreen = closest.screen();
        hit.setScreen(auxScreen);
        HitCalculator.calculateHit(auxScreen.shape, auxScreen.offsetX2D, auxScreen.offsetY2D, closest.location(), hit);
        return hit;
    }
}