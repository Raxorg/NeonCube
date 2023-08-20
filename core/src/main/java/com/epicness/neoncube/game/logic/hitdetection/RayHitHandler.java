package com.epicness.neoncube.game.logic.hitdetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.utils.CollisionUtils;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;
import com.epicness.neoncube.game.stuff.tridimensional.Hit;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class RayHitHandler extends GameLogicHandler {

    private PerspectiveCamera camera;
    private Line3D line;
    private DelayedRemovalArray<PlaneScreen> planes;
    private DelayedRemovalArray<CylinderScreen> cylinders;
    private DelayedRemovalArray<Hit> hits;
    private Vector3 aux;

    @Override
    protected void init() {
        camera = renderer.getPerspectiveCamera();
        line = stuff.getLine();
        planes = stuff.getPlaneScreens();
        cylinders = stuff.getCylinderScreens();
        hits = new DelayedRemovalArray<>();
        aux = new Vector3();
    }

    @Override
    public void touchDown(float x, float y) {
        x = Gdx.input.getX();
        y = Gdx.input.getY();
        Ray ray = renderer.getPerspectiveCamera().getPickRay(x, y);
        line.set(ray.origin, ray.direction.cpy().scl(50f).add(ray.origin));

        hits.clear();
        hits.begin();
        for (int i = 0; i < planes.size; i++) {
            PlaneScreen plane = planes.get(i);
            if (CollisionUtils.intersects(ray, plane, aux)) {
                hits.add(new Hit(aux.cpy(), plane));
            }
        }

        for (int i = 0; i < cylinders.size; i++) {
            CylinderScreen cylinder = cylinders.get(i);
            if (CollisionUtils.intersects(ray, cylinder, aux)) {
                hits.add(new Hit(aux.cpy(), cylinder));
            }
        }
        hits.end();

        if (hits.notEmpty()) processClosestHit();
    }

    private void processClosestHit() {
        Hit closest = hits.first();
        if (hits.size > 1) {
            for (int i = 0; i < hits.size; i++) {
                if (hits.get(i).location.dst(camera.position) < closest.location.dst(camera.position)) {
                    closest = hits.get(i);
                }
            }
        }
        if (closest.shape instanceof PlaneScreen) {
            logic.get(PlaneHitCalculator.class).calculatePlaneHit((PlaneScreen) closest.shape, closest.location);
        } else if (closest.shape instanceof CylinderScreen) {
            logic.get(CylinderHitCalculator.class).calculateCylinderHit((CylinderScreen) closest.shape, closest.location);
        }
    }
}