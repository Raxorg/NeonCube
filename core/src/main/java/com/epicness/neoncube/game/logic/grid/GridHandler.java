package com.epicness.neoncube.game.logic.grid;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_COLOR_FADE_TIME;

import com.badlogic.gdx.graphics.Color;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.ColorCell;

public class GridHandler extends GameLogicHandler {

    private ColorCell[][] cells;
    private ColorCell cell;
    private int column, row;
    private float newColorProgress;
    private Color lerpedColor;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                cell = cells[column][row];
                cell.originalColor.set(WHITE);
                cell.colorProgress = 0f;
            }
        }
        cell = null;
        column = row = 0;
        newColorProgress = 0f;
        lerpedColor = new Color();
    }

    @Override
    protected void update(float delta) {
        // Fade the color over time
        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                cell = cells[column][row];
                newColorProgress = Math.min(cell.colorProgress + delta / CELL_COLOR_FADE_TIME, 1f);
                cell.colorProgress = newColorProgress;
                lerpedColor.set(cell.originalColor);
                cell.setColor(lerpedColor.lerp(Color.BLACK, newColorProgress));
            }
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == SPACE) {
            for (column = 0; column < cells.length; column++) {
                for (row = 0; row < cells[column].length; row++) {
                    cells[column][row].colorProgress = 0f;
                }
            }
        }
    }
}