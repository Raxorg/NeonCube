package com.epicness.fundamentals.stuff.interfaces;

public interface Actor<D1 extends Drawer, D2 extends Drawer> extends Drawable<D1, D2>, Transformable {
}