package com.epicness.neoncube.game;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

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
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.neoncube.game.stuff.DecalScreen;
import com.epicness.neoncube.game.stuff.GameStuff;
import com.epicness.neoncube.game.stuff.GameView;

public class GameRenderer extends Renderer<GameStuff> {

    private final PerspectiveCamera perspectiveCamera;
    private final ModelBatch modelBatch;
    private final DecalBatch decalBatch;
    private final FrameBuffer frameBuffer;
    private Sprite gameSprite;

    public GameRenderer() {
        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 99999999f;
        modelBatch = new ModelBatch();
        decalBatch = new DecalBatch(new CameraGroupStrategy(perspectiveCamera));

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        gameSprite = new Sprite();
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

        renderToFrameBuffer();

        stuff.getDecalCube().draw(decalBatch);
        decalBatch.flush();
    }

    private void renderToFrameBuffer() {
        GameView gameView = stuff.getGameView();
        frameBuffer.bind();
        ScreenUtils.clear(Color.RED);
        spriteBatch.begin();
        gameView.draw(spriteBatch);
        spriteBatch.end();
        frameBuffer.end();
        // Update decals
        DecalScreen[] screens = stuff.getDecalCube().getFaces();
        Texture bufferTexture = frameBuffer.getColorBufferTexture();
        for (int i = 0; i < screens.length; i++) {
            gameSprite = new Sprite(
                    bufferTexture,
                    i * Gdx.graphics.getWidth(), 0,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            gameSprite.flip(false, true);
            screens[i].setSprite(gameSprite);
        }
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}