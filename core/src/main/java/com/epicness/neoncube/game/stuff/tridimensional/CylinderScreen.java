package com.epicness.neoncube.game.stuff.tridimensional;

import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.Constants.WINDOW_HEIGHT;
import static com.epicness.neoncube.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Cylinder;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;

public class CylinderScreen extends Cylinder {

    private final FrameBuffer frameBuffer;
    private final Sprite bufferSprite;
    private final StickmanWorld stickmanWorld;
    public final int screenPortionIndex;

    public CylinderScreen(CylinderBuilder cylinderBuilder, StickmanWorld stickmanWorld, int screenPortionIndex) {
        super(cylinderBuilder);

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_WIDTH, WINDOW_HEIGHT, false);
        bufferSprite = new Sprite();
        bufferSprite.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);

        this.stickmanWorld = stickmanWorld;
        this.screenPortionIndex = screenPortionIndex;
    }

    public void drawToBuffer(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, OrthographicCamera camera) {
        // Move the camera and use its new projection matrix
        camera.position.x = CAMERA_HALF_WIDTH + screenPortionIndex * CAMERA_WIDTH;
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeDrawer.update();
        // Render to frame buffer
        frameBuffer.bind();
        ScreenUtils.clear(CLEAR);
        renderOrthoNormally(spriteBatch);
        renderOrthoDebug(shapeDrawer);
        spriteBatch.flush();
        frameBuffer.end();
        // Set the frame buffer's texture as the decal's texture
        bufferSprite.setRegion(frameBuffer.getColorBufferTexture());
        bufferSprite.flip(false, true);
        setSprite(bufferSprite);
    }

    private void renderOrthoNormally(SpriteBatch spriteBatch) {
        stickmanWorld.draw(spriteBatch);
        // useStaticCamera(); // For the HUD
        // Draw HUD
    }

    private void renderOrthoDebug(ShapeDrawerPlus shapeDrawer) {
        stickmanWorld.drawDebug(shapeDrawer);
    }
}