package com.epicness.neoncube.game;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.Constants.WINDOW_HEIGHT;
import static com.epicness.neoncube.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.neoncube.game.stuff.DecalScreen;
import com.epicness.neoncube.game.stuff.GameStuff;

public class GameRenderer extends Renderer<GameStuff> {

    private final PerspectiveCamera perspectiveCamera;
    private final ModelBatch modelBatch;
    private final DecalBatch decalBatch;
    private final FrameBuffer[] frameBuffers;
    private final Sprite[] bufferSprites;
    private final OrthographicCamera decalCamera;

    public GameRenderer() {
        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 100f;

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

        shapeRenderer.setColor(GREEN);
        Gdx.gl.glLineWidth(2f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(BLACK, true);

        spriteBatch.begin();
        stuff.getFpsText().setText(Gdx.graphics.getFramesPerSecond() + "");
        stuff.getFpsText().draw(spriteBatch);
        spriteBatch.end();

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(stuff.getCube(), stuff.getEnvironment());
        modelBatch.end();

        renderDecalCube();

        stuff.getDecalCube().draw(decalBatch);
        decalBatch.flush();
    }

    @SuppressWarnings("GDXJavaFlushInsideLoop")
    private void renderDecalCube() {
        DecalScreen[] screens = stuff.getDecalCube().getFaces();
        for (int i = 0; i < screens.length; i++) {
            FrameBuffer frameBuffer = frameBuffers[i];
            Sprite bufferSprite = bufferSprites[i];
            // Render to frame buffer
            frameBuffer.bind();
            ScreenUtils.clear(Color.RED);
            renderNormally(i);
            renderDebug();
            frameBuffer.end();
            // Set the frame buffer's texture as the decal's texture
            bufferSprite.setRegion(frameBuffer.getColorBufferTexture());
            bufferSprite.flip(false, true);
            screens[i].setSprite(bufferSprite);
        }
        // Back to normal projection matrix
        useStaticCamera();
    }

    private void renderNormally(int screenIndex) {
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

    private void renderDebug() {
        // Debug
        shapeRenderer.begin();
        stuff.getStickmanWorld().drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}