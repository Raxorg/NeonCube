package com.epicness.neoncube.game.logic.hitdetection;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.tridimensional.CylinderScreen;

public class CylinderHitCalculator extends GameLogicHandler {

    private Cell[][] cells;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
    }

    public void calculateCylinderHit(CylinderScreen cylinder, Vector3 intersection) {
        float minY = cylinder.getY() - cylinder.getHeight() / 2f;
        float maxY = cylinder.getY() + cylinder.getHeight() / 2f;

        Vector2 circlePoint = new Vector2(intersection.x - cylinder.getX(), intersection.z - cylinder.getZ());
        float angle = (circlePoint.angleDeg() % 90f + cylinder.getYRotation() % 90f) % 90f;
        float x = MathUtils.map(90f, 0f, 0f, CAMERA_WIDTH, angle);
        x += CAMERA_WIDTH * cylinder.screenPortionIndex;
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