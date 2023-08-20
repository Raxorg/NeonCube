package com.epicness.neoncube.game.logic.hitdetection;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.utils.AngleUtils;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class PlaneHitCalculator extends GameLogicHandler {

    private Cell[][] cells;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
    }

    public void calculatePlaneHit(PlaneScreen plane, Vector3 intersection) {
        float minY = plane.getY() - plane.getHeight() / 2f;
        float maxY = plane.getY() + plane.getHeight() / 2f;

        float angle = AngleUtils.degreesBetweenPoints(intersection.x, intersection.z, plane.getX(), plane.getZ());
        angle = MathUtils.ceil((angle + plane.getYRotation())) % 360f;
        int sign = 1;
        if (angle > 90f) sign = -1;
        float x_d = plane.getX() - intersection.x;
        float y_d = plane.getZ() - intersection.z;
        float distance = (float) Math.sqrt(x_d * x_d + y_d * y_d) * sign;
        float x = MathUtils.map(-DECAL_CUBE_XZ_RADIUS, DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, distance);
        x += CAMERA_WIDTH * plane.screenPortionIndex;
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