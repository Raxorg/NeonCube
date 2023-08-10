package com.epicness.neoncube.game;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.neoncube.game.stuff.GameStuff;

public class GameRenderer extends Renderer<GameStuff> {

    private final PerspectiveCamera perspectiveCamera;
    private final ModelBatch modelBatch;

    public GameRenderer() {
        shapeDrawer.setDefaultLineWidth(4f);

        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 1000f;

        modelBatch = new ModelBatch();
    }

    @SuppressWarnings("GDXJavaFlushInsideLoop")
    @Override
    public void render() {
        ScreenUtils.clear(BLACK, true);

        spriteBatch.begin();
        for (int i = 0; i < stuff.getPlaneScreens().size; i++) {
            stuff.getPlaneScreens().get(i).drawToBuffer(spriteBatch, shapeDrawer, screen.getDynamicCamera());
        }
        for (int i = 0; i < stuff.getCylinderScreens().size; i++) {
            stuff.getCylinderScreens().get(i).drawToBuffer(spriteBatch, shapeDrawer, screen.getDynamicCamera());
        }
        spriteBatch.end();

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(stuff.getCube(), stuff.getEnvironment());
        stuff.getLine().draw(modelBatch);
        stuff.getWireframeCube().draw(modelBatch);
        for (int i = 0; i < stuff.getPlaneScreens().size; i++) {
            stuff.getPlaneScreens().get(i).draw(modelBatch, stuff.getEnvironment());
        }
        for (int i = 0; i < stuff.getCylinderScreens().size; i++) {
            stuff.getCylinderScreens().get(i).draw(modelBatch, stuff.getEnvironment());
        }
        modelBatch.end();

        useStaticCamera();
        spriteBatch.begin();
        stuff.getDebugText().setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
            "Speed: " + stuff.getStickmanWorld().player.speed);
        stuff.getDebugText().draw(spriteBatch);
        spriteBatch.end();

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

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}