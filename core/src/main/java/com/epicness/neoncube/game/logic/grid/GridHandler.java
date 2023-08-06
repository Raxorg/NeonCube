package com.epicness.neoncube.game.logic.grid;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_COLOR_FADE_TIME;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_COLOR_PROGRESS_PROPERTY;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_ORIGINAL_COLOR_PROPERTY;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.neoncube.game.logic.GameLogicHandler;

public class GridHandler extends GameLogicHandler {

    private Cell[][] cells;

    @Override
    protected void init() {
        cells = stuff.getStickmanWorld().grid.cells;
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                Cell cell = cells[column][row];
                cell.clearProperties();
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, 0f);
                cell.setProperty(CELL_ORIGINAL_COLOR_PROPERTY, WHITE);
            }
        }
    }

    @Override
    protected void update(float delta) {
        if (Gdx.input.isKeyJustPressed(SPACE)) {
            for (int column = 0; column < cells.length; column++) {
                for (int row = 0; row < cells[column].length; row++) {
                    cells[column][row].setProperty(CELL_COLOR_PROGRESS_PROPERTY, 0f);
                }
            }
        }
        // Fade the color over time
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                Cell cell = cells[column][row];
                float colorProgress = cell.getProperty(CELL_COLOR_PROGRESS_PROPERTY);
                float newColorProgress = Math.min(colorProgress + delta / CELL_COLOR_FADE_TIME, 1f);
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, newColorProgress);
                Color originalColor = cell.getProperty(CELL_ORIGINAL_COLOR_PROPERTY);
                cell.setColor(originalColor.cpy().lerp(Color.BLACK, newColorProgress));
                cell.setProperty(CELL_COLOR_PROGRESS_PROPERTY, newColorProgress);
            }
        }
    }
}