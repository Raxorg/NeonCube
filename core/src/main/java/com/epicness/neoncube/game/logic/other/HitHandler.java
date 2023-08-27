package com.epicness.neoncube.game.logic.other;

import static com.badlogic.gdx.graphics.Color.GREEN;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.collision.Ray;
import com.epicness.fundamentals.logic.collision3d.RayHitHandler;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Hit2D;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.ColorCell;

@SuppressWarnings("FieldCanBeLocal")
public class HitHandler extends GameLogicHandler {

    private RayHitHandler rayHitHandler;
    private ColorCell[][] cells;
    // Optimization
    private Hit2D hit;
    private int column, row;
    private ColorCell currentCell;

    @Override
    protected void init() {
        rayHitHandler = new RayHitHandler();
        for (int i = 0; i < stuff.getPlaneScreens().size; i++) {
            rayHitHandler.registerScreens(stuff.getPlaneScreens().get(i));
        }
        for (int i = 0; i < stuff.getCylinderScreens().size; i++) {
            rayHitHandler.registerScreens(stuff.getCylinderScreens().get(i));
        }
        hit = new Hit2D();
        cells = stuff.getStickmanWorld().grid.cells;
    }

    @Override
    public void touchDown(float x, float y, int button) {
        x = Gdx.input.getX();
        y = Gdx.input.getY();
        Ray ray = renderer.getPerspectiveCamera().getPickRay(x, y);
        stuff.getLine().set(ray.origin, ray.direction.cpy().scl(50f).add(ray.origin));

        hit = rayHitHandler.calculateHit(renderer.getPerspectiveCamera());
        if (hit == null) return;

        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                currentCell = cells[column][row];
                if (currentCell.contains(hit.getX(), hit.getY())) {
                    currentCell.originalColor.set(GREEN);
                }
            }
        }
    }
}