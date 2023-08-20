package com.epicness.neoncube.game.stuff.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.grid.Cell;

public class ColorCell extends Cell {

    public final Color originalColor;
    public float colorProgress;

    public ColorCell(Sprite cellSprite, int column, int row, Color originalColor, float colorProgress) {
        super(cellSprite, column, row);
        this.originalColor = new Color().set(originalColor);
        this.colorProgress = colorProgress;
    }

    public ColorCell(Sprite cellSprite, int column, int row, Color originalColor) {
        this(cellSprite, column, row, originalColor, 0f);
    }
}