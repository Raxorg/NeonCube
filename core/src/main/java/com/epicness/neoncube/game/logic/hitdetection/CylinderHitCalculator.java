package com.epicness.neoncube.game.logic.hitdetection;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.ColorCell;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;

public class CylinderHitCalculator extends GameLogicHandler {

    private ColorCell[][] cells;
    private float minY, maxY, angle, x, y;
    private Vector2 circlePoint;
    private int column, row;
    private ColorCell currentCell;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
        minY = maxY = angle = x = y = 0f;
        circlePoint = new Vector2();
        column = row = 0;
        currentCell = null;
    }

    public void calculateCylinderHit(CylinderScreen cylinder, Vector3 intersection) {
        minY = cylinder.getY() - cylinder.getHeight() / 2f;
        maxY = cylinder.getY() + cylinder.getHeight() / 2f;

        circlePoint.set(intersection.x - cylinder.getX(), intersection.z - cylinder.getZ());
        angle = (circlePoint.angleDeg() % 90f + cylinder.getYRotation() % 90f) % 90f;
        x = MathUtils.map(90f, 0f, 0f, CAMERA_WIDTH, angle);
        x += CAMERA_WIDTH * cylinder.screenPortionIndex;
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