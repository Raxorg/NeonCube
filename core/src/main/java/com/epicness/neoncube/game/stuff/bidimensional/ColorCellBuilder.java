package com.epicness.neoncube.game.stuff.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.stuff.grid.CellBuilder;

public class ColorCellBuilder extends CellBuilder<ColorCell> {

    private Color originalColor;
    private float colorProgress;

    public ColorCellBuilder() {
        originalColor = WHITE;
        colorProgress = 0f;
    }

    public ColorCellBuilder originalColor(Color originalColor) {
        this.originalColor = originalColor;
        return this;
    }

    public ColorCellBuilder colorProgress(float colorProgress) {
        this.colorProgress = colorProgress;
        return this;
    }

    @Override
    public ColorCell[][] buildColumns(int columns) {
        return new ColorCell[columns][];
    }

    @Override
    public ColorCell[] buildRow(int rows) {
        return new ColorCell[rows];
    }

    @Override
    public ColorCell buildCell() {
        return new ColorCell(sprite, column, row, originalColor, colorProgress);
    }
}