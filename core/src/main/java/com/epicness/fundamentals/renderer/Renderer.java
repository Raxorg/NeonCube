package com.epicness.fundamentals.renderer;

import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Renderer<S extends Stuff<?>> {

    // Structure
    protected SharedScreen screen;
    protected SharedStuff sharedStuff;
    protected S stuff;
    // Rendering related
    protected final SpriteBatchDrawer spriteBatch;
    protected final ShapeRendererDrawer shapeBatch;

    public Renderer() {
        spriteBatch = new SpriteBatchDrawer();
        shapeBatch = new ShapeRendererDrawer();
        shapeBatch.setAutoShapeType(true);
    }

    public abstract void render();

    public void useStaticCamera() {
        spriteBatch.setProjectionMatrix(screen.getStaticCamera().combined);
        shapeBatch.setProjectionMatrix(screen.getStaticCamera().combined);
    }

    public void useDynamicCamera() {
        spriteBatch.setProjectionMatrix(screen.getDynamicCamera().combined);
        shapeBatch.setProjectionMatrix(screen.getDynamicCamera().combined);
    }

    public SpriteBatchDrawer getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRendererDrawer getShapeBatch() {
        return shapeBatch;
    }

    // Structure
    public void setScreen(SharedScreen screen) {
        this.screen = screen;
    }

    public void setSharedStuff(SharedStuff sharedStuff) {
        this.sharedStuff = sharedStuff;
    }

    public void setStuff(S stuff) {
        this.stuff = stuff;
    }
}