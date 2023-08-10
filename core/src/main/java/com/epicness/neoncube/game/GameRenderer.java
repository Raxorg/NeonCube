package com.epicness.neoncube.game;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.Constants.WINDOW_HEIGHT;
import static com.epicness.neoncube.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.neoncube.game.stuff.GameStuff;
import com.epicness.neoncube.game.stuff.tridimensional.DecalScreen;

public class GameRenderer extends Renderer<GameStuff> {

    private final PerspectiveCamera perspectiveCamera;
    private final ModelBatch modelBatch;
    private final DecalBatch decalBatch;
    private final FrameBuffer[] frameBuffers;
    private final Sprite[] bufferSprites;
    private final OrthographicCamera decalCamera;

    public GameRenderer() {
        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 500f;

        modelBatch = new ModelBatch();
        decalBatch = new DecalBatch(new CameraGroupStrategy(perspectiveCamera));

        frameBuffers = new FrameBuffer[4];
        for (int i = 0; i < frameBuffers.length; i++) {
            frameBuffers[i] = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_WIDTH, WINDOW_HEIGHT, false);
        }

        bufferSprites = new Sprite[4];
        for (int i = 0; i < bufferSprites.length; i++) {
            bufferSprites[i] = new Sprite();
            bufferSprites[i].setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        }

        decalCamera = new OrthographicCamera();
        decalCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);

        Gdx.gl.glLineWidth(2f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(BLACK, true);

        spriteBatch.begin();
        stuff.getDebugText().setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
                "Speed: " + stuff.getStickmanWorld().player.speed);
        stuff.getDebugText().draw(spriteBatch);
        spriteBatch.end();

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(stuff.getCube(), stuff.getEnvironment());
        stuff.getLine().draw(modelBatch);
        stuff.getWireframeCube().draw(modelBatch);
        modelBatch.end();

        renderDecalFaces();
        stuff.getDecalCube().draw(decalBatch);
        decalBatch.flush();

        modelBatch.begin(perspectiveCamera);
        stuff.getCylinder().draw(modelBatch, stuff.getEnvironment());
        modelBatch.end();

        renderDebug();
    }

    public void renderDebug() {
        shapeRenderer.setProjectionMatrix(perspectiveCamera.combined);
        shapeRenderer.begin();
        // debug shapes (circles, rectangles etc)
        shapeRenderer.end();

        modelBatch.begin(perspectiveCamera);
        // debug models (lines etc)
        modelBatch.end();
    }

    @SuppressWarnings("GDXJavaFlushInsideLoop")
    private void renderDecalFaces() {
        DelayedRemovalArray<DecalScreen> faces = stuff.getDecalCube().faces;
        for (int i = 0; i < faces.size; i++) {
            FrameBuffer frameBuffer = frameBuffers[i];
            Sprite bufferSprite = bufferSprites[i];
            // Render to frame buffer
            frameBuffer.bind();
            ScreenUtils.clear(CLEAR);
            renderDecalNormally(i);
            renderDecalDebug();
            frameBuffer.end();
            // Set the frame buffer's texture as the decal's texture
            bufferSprite.setRegion(frameBuffer.getColorBufferTexture());
            bufferSprite.flip(false, true);
            faces.get(i).setSprite(bufferSprite);
        }
        stuff.getCylinder().setSprite(bufferSprites[1]);
        // Back to normal projection matrix
        useStaticCamera();
    }

    private void renderDecalNormally(int screenIndex) {
        // Move the camera and use its new projection matrix
        spriteBatch.begin();
        decalCamera.position.x = CAMERA_HALF_WIDTH + screenIndex * CAMERA_WIDTH;
        decalCamera.update();
        spriteBatch.setProjectionMatrix(decalCamera.combined);
        stuff.getStickmanWorld().draw(spriteBatch);
        useStaticCamera(); // For the HUD
        // Draw HUD
        spriteBatch.end();
    }

    private void renderDecalDebug() {
        // Debug
        shapeRenderer.begin();
        shapeRenderer.setProjectionMatrix(decalCamera.combined);
        stuff.getStickmanWorld().drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}