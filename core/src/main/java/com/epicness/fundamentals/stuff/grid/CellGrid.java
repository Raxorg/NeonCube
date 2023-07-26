package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.renderer.SpriteBatchDrawer;

public class CellGrid extends Grid<SpriteBatchDrawer> {

    private final Cell[][] cells;

    public CellGrid(int columns, int rows, Sprite cellSprite) {
        super(columns, rows);
        cells = new Cell[columns][];
        for (int column = 0; column < columns; column++) {
            cells[column] = new Cell[rows];
            for (int row = 0; row < rows; row++) {
                cells[column][row] = new Cell(cellSprite, column, row);
            }
        }
    }

    @Override
    public void draw(SpriteBatchDrawer spriteBatch) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].draw(spriteBatch);
            }
        }
    }

    @Override
    public void setColor(Color color) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].setColor(color);
            }
        }
    }

    public void setCellSize(float size) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].setPosition(column * size, row * size);
                cells[column][row].setSize(size);
            }
        }
    }

    public void translate(float x, float y) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].translate(x, y);
            }
        }
    }

    public float getWidth() {
        return columns * cells[0][0].getWidth();
    }

    public float getHeight() {
        return rows * cells[0][0].getHeight();
    }

    public Cell[][] getCells() {
        return cells;
    }
}