package com.epicness.neoncube.game.logic.hitdetection;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_CUBE_XZ_RADIUS;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.utils.AngleUtils;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.ColorCell;
import com.epicness.neoncube.game.stuff.tridimensional.PlaneScreen;

public class PlaneHitCalculator extends GameLogicHandler {

    private ColorCell[][] cells;
    private float minY, maxY, angle, xDelta, yDelta, distance, x, y;
    private int sign, column, row;
    private ColorCell currentCell;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
        minY = maxY = angle = xDelta = yDelta = distance = x = y = 0f;
        sign = 1;
        column = row = 0;
        currentCell = null;
    }

    public void calculatePlaneHit(PlaneScreen plane, Vector3 intersection) {
        minY = plane.getY() - plane.getHeight() / 2f;
        maxY = plane.getY() + plane.getHeight() / 2f;

        angle = AngleUtils.degreesBetweenPoints(intersection.x, intersection.z, plane.getX(), plane.getZ());
        angle = MathUtils.ceil((angle + plane.getYRotation())) % 360f;
        sign = 1;
        if (angle > 90f) sign = -1;
        xDelta = plane.getX() - intersection.x;
        yDelta = plane.getZ() - intersection.z;
        distance = (float) Math.sqrt(xDelta * xDelta + yDelta * yDelta) * sign;
        x = MathUtils.map(-DECAL_CUBE_XZ_RADIUS, DECAL_CUBE_XZ_RADIUS, 0f, CAMERA_WIDTH, distance);
        x += CAMERA_WIDTH * plane.screenPortionIndex;
        y = MathUtils.map(minY, maxY, 0f, CAMERA_HEIGHT, intersection.y);

        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                currentCell = cells[column][row];
                if (currentCell.contains(x, y)) {
                    currentCell.originalColor.set(GREEN);
                }
            }
        }
    }
}