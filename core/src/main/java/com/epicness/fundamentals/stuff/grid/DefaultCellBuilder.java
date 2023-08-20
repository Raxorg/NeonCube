package com.epicness.fundamentals.stuff.grid;

public class DefaultCellBuilder extends CellBuilder<Cell> {

    @Override
    public Cell[][] buildColumns(int columns) {
        return new Cell[columns][];
    }

    @Override
    public Cell[] buildRow(int rows) {
        return new Cell[rows];
    }

    @Override
    public Cell buildCell() {
        return new Cell(sprite, column, row);
    }
}