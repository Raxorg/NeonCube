package com.epicness.neoncube.game;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.Constants.WINDOW_HEIGHT;
import static com.epicness.neoncube.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.neoncube.game.stuff.DecalScreen;
import com.epicness.neoncube.game.stuff.GameStuff;

public class GameRenderer extends Renderer<GameStuff> {

    private final PerspectiveCamera perspectiveCamera;
    private final ModelBatch modelBatch;
    private final DecalBatch decalBatch;
    private final FrameBuffer frameBuffer;
    private final Sprite bufferSprite;
    private Matrix4 normalProjectionMatrix, wideProjectionMatrix;

    public GameRenderer() {
        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 9000f;

        modelBatch = new ModelBatch();
        decalBatch = new DecalBatch(new CameraGroupStrategy(perspectiveCamera));
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_WIDTH, WINDOW_HEIGHT, false);

        bufferSprite = new Sprite();
        bufferSprite.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    public void setScreen(SharedScreen screen) {
        super.setScreen(screen);
        screen.getStaticCamera().setToOrtho(false, CAMERA_WIDTH * 4f, CAMERA_HEIGHT);
        wideProjectionMatrix = screen.getStaticCamera().combined.cpy();
        screen.getStaticCamera().setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        normalProjectionMatrix = screen.getStaticCamera().combined;
    }

    @Override
    public void render() {
        ScreenUtils.clear(BLACK, true);

        spriteBatch.begin();
        BitmapFont font = new BitmapFont();
        font.getData().scale(7f);
        font.draw(spriteBatch, Gdx.graphics.getFramesPerSecond() + "", 100f, 100f);
        spriteBatch.end();

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(stuff.getCube(), stuff.getEnvironment());
        modelBatch.end();

        renderCubeWorld();

        stuff.getDecalCube().draw(decalBatch);
        decalBatch.flush();
    }

    private void renderCubeWorld() {
        // Use wide projection
        spriteBatch.setProjectionMatrix(wideProjectionMatrix);
        // Render to frame buffer
        frameBuffer.bind();
        ScreenUtils.clear(Color.RED);
        spriteBatch.begin();
        stuff.getCubeWorld().draw(spriteBatch);
        spriteBatch.end();
        frameBuffer.end();
        // Use normal projection
        spriteBatch.setProjectionMatrix(normalProjectionMatrix);
        // Render the texture from frame buffer
        DecalScreen[] screens = stuff.getDecalCube().getFaces();
        Texture bufferTexture = frameBuffer.getColorBufferTexture();
        for (int i = 0; i < screens.length; i++) {
            bufferSprite.setRegion(bufferTexture);
            bufferSprite.setRegionX(i * WINDOW_WIDTH / 4);
            bufferSprite.setRegionWidth(WINDOW_WIDTH / 4);
            bufferSprite.flip(false, true);
            screens[i].setSprite(bufferSprite);
        }
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}