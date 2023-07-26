package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.stuff.interfaces.Drawable;
import com.epicness.fundamentals.stuff.interfaces.Drawer;

public abstract class Grid<D1 extends Drawer> implements Drawable<D1, Drawer> {

    protected final int columns, rows;

    public Grid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public void drawDebug(Drawer drawer) {

    }

    public abstract void setColor(Color color);
}