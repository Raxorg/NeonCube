package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.graphics.g3d.Model;

public abstract class ModelWrapper<T extends ModelProperties> {

    protected static ModelBuilderPlus modelBuilder;
    public final T properties;
    public final Model model;

    protected ModelWrapper(T properties) {
        if (modelBuilder == null) modelBuilder = new ModelBuilderPlus();
        this.properties = properties;
        model = build(properties);
    }

    protected abstract Model build(T properties);
}