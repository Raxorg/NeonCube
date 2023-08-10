package com.epicness.neoncube.game.logic.other;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_Y_RADIUS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Cylinder;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.utils.CollisionUtils;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;
import com.epicness.neoncube.game.stuff.tridimensional.WireframeCube;

public class RayHandler extends GameLogicHandler {

    private Line3D line;
    private WireframeCube wireframeCube;
    private DelayedRemovalArray<CylinderScreen> cylinders;
    private Cell[][] cells;
    private Vector3 intersection;

    @Override
    protected void init() {
        line = stuff.getLine();
        wireframeCube = stuff.getWireframeCube();
        cylinders = stuff.getCylinderScreens();
        cells = stuff.getStickmanWorld().grid.cells;
        intersection = new Vector3();
    }

    @Override
    public void touchDown(float x, float y) {
        x = Gdx.input.getX();
        y = Gdx.input.getY();
        Ray ray = renderer.getPerspectiveCamera().getPickRay(x, y);
        line.set(ray.origin, ray.direction.cpy().scl(50f).add(ray.origin));

        intersection.setZero();
        if (CollisionUtils.intersects(ray, wireframeCube, intersection)) {
            // TODO: Calculate per Plane
            System.out.println("Cube hit: " + intersection);
            calculateHit(intersection);
        }

        for (int i = 0; i < cylinders.size; i++) {
            intersection.setZero();
            Cylinder cylinder = cylinders.get(i);
            if (CollisionUtils.intersects(ray, cylinder, intersection)) {
                System.out.println("Cylinder" + i + " hit: " + intersection);
                calculateCylinderHit(cylinder, intersection);
            }
        }
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

    private void calculateCylinderHit(Cylinder cylinder, Vector3 intersection) {
        float minY = cylinder.getY() - cylinder.getHeight() / 2f;
        float maxY = cylinder.getY() + cylinder.getHeight() / 2f;

        Vector2 circlePoint = new Vector2(intersection.x - cylinder.getX(), intersection.z - cylinder.getZ());
        float x = MathUtils.map(90f, 0f, 0f, CAMERA_WIDTH, circlePoint.angleDeg());
        x += CAMERA_WIDTH;
        float y = MathUtils.map(minY, maxY, 0f, CAMERA_HEIGHT, intersection.y);

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