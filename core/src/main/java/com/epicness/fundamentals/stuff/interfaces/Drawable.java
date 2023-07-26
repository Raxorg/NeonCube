package com.epicness.fundamentals.stuff.interfaces;

public interface Drawable<D1 extends Drawer, D2 extends Drawer> {

    void draw(D1 drawer);

    void drawDebug(D2 drawer);
}