package com.epicness.neoncube.game.logic.grid;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_COLOR_FADE_TIME;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_COLOR_PROGRESS_PROPERTY;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.neoncube.game.logic.GameLogicHandler;

public class GridHandler extends GameLogicHandler {

    private Cell[][] cells;
    private Cell cell;
    private int column, row;
    private float colorProgress, newColorProgress;
    private Color originalColor;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                cell = cells[column][row];
                cell.clearProperties();
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, 0f);
                cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, WHITE);
            }
        }
        cell = null;
        column = row = 0;
        colorProgress = newColorProgress = 0f;
        originalColor = null;
    }

    @Override
    protected void update(float delta) {
        // Fade the color over time
        for (column = 0; column < cells.length; column++) {
            for (row = 0; row < cells[column].length; row++) {
                cell = cells[column][row];
                colorProgress = cell.getProperty(CELL_COLOR_PROGRESS_PROPERTY);
                newColorProgress = Math.min(colorProgress + delta / CELL_COLOR_FADE_TIME, 1f);
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, newColorProgress);
                originalColor = cell.getProperty(CELL_ORIGINAL_COLOR_PROPERTY);
                cell.setColor(originalColor.cpy().lerp(Color.BLACK, newColorProgress));
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, newColorProgress);
            }
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == SPACE) {
            for (column = 0; column < cells.length; column++) {
                for (row = 0; row < cells[column].length; row++) {
                    cells[column][row].setProperty(CELL_COLOR_PROGRESS_PROPERTY, 0f);
                }
            }
        }
    }
}