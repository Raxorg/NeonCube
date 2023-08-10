package com.epicness.neoncube.game.logic.other;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_Y_RADIUS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.WireframeCube;

public class RayHandler extends GameLogicHandler {

    private Line3D line;
    private WireframeCube wireframeCube;
    private Cell[][] cells;

    @Override
    protected void init() {
        line = stuff.getLine();
        wireframeCube = stuff.getWireframeCube();
        cells = stuff.getStickmanWorld().grid.cells;
    }

    @Override
    public void touchDown(float x, float y) {
        x = Gdx.input.getX();
        y = Gdx.input.getY();
        Ray ray = renderer.getPerspectiveCamera().getPickRay(x, y);
        line.set(ray.origin, ray.direction.cpy().scl(50f).add(ray.origin));
        Vector3 intersection = new Vector3();
        System.out.println(wireframeCube.intersects(ray, intersection) + " : " + intersection);
        calculateHit(intersection);
    }

    private void calculateHit(Vector3 intersection) {
        float y = MathUtils.map(-DECAL_CUBE_Y_RADIUS, DECAL_CUBE_Y_RADIUS, 0f, CAMERA_HEIGHT, intersection.y);
        // Front
        if (intersection.z == DECAL_CUBE_XZ_RADIUS) {
            float x = MathUtils.map(-DECAL_CUBE_XZ_RADIUS, DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, intersection.x);
            for (int column = 0; column < cells.length; column++) {
                for (int row = 0; row < cells[column].length; row++) {
                    Cell cell = cells[column][row];
                    if (cell.contains(x, y)) {
                        cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, GREEN);
                    }
                }
            }
        }
        // Right side
        if (intersection.x == DECAL_CUBE_XZ_RADIUS) {
            float x = MathUtils.map(DECAL_CUBE_XZ_RADIUS, -DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, intersection.z);
            x += CAMERA_WIDTH;
            for (int column = 0; column < cells.length; column++) {
                for (int row = 0; row < cells[column].length; row++) {
                    Cell cell = cells[column][row];
                    if (cell.contains(x, y)) {
                        cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, GREEN);
                    }
                }
            }
        }
        // Back
        if (intersection.z == -DECAL_CUBE_XZ_RADIUS) {
            float x = MathUtils.map(DECAL_CUBE_XZ_RADIUS, -DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, intersection.x);
            x += CAMERA_WIDTH * 2f;
            for (int column = 0; column < cells.length; column++) {
                for (int row = 0; row < cells[column].length; row++) {
                    Cell cell = cells[column][row];
                    if (cell.contains(x, y)) {
                        cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, GREEN);
                    }
                }
            }
        }
        // Left side
        if (intersection.x == -DECAL_CUBE_XZ_RADIUS) {
            float x = MathUtils.map(-DECAL_CUBE_XZ_RADIUS, DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, intersection.z);
            x += CAMERA_WIDTH * 3f;
            for (int column = 0; column < cells.length; column++) {
                for (int row = 0; row < cells[column].length; row++) {
                    Cell cell = cells[column][row];
                    if (cell.contains(x, y)) {
                        cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, GREEN);
                    }
                }
            }
        }
    }
}