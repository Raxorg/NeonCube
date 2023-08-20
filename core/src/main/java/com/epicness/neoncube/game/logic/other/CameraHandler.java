package com.epicness.neoncube.game.logic.other;

import static com.badlogic.gdx.Input.Keys.F;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.PLAYER_STARTING_X;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.Player;

public class CameraHandler extends GameLogicHandler {

    private PerspectiveCamera camera;
    private Vector2 pivot;
    private boolean free;
    private Player player;
    private float lastPlayerX, lastPlayerY, deltaX, deltaY, dragDeltaX, dragDeltaY;

    @Override
    protected void init() {
        camera = renderer.getPerspectiveCamera();
        camera.direction.set(0, 0, -1);
        camera.up.set(0, 1, 0);
        camera.position.set(0f, 0f, 15f);
        camera.update();

        pivot = new Vector2();
        free = false;
        player = stuff.getStickmanWorld().player;
        lastPlayerX = PLAYER_STARTING_X;
        deltaX = deltaY = dragDeltaX = dragDeltaY = 0f;
    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < stuff.getCylinderScreens().size; i++) {
            stuff.getCylinderScreens().get(i).rotateY(delta);
        }
        for (int i = 0; i < stuff.getPlaneScreens().size; i++) {
            stuff.getPlaneScreens().get(i).rotateY(delta);
        }
        deltaX = player.getX() - lastPlayerX;
        rotateX(deltaX);

        deltaY = lastPlayerY - player.getY();
        deltaY = MathUtils.map(0f, CAMERA_HEIGHT, 0f, 45f, deltaY);
        camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), deltaY);
        camera.update();

        lastPlayerX = player.getX();
        lastPlayerY = player.getY();
    }

    private void rotateX(float delta) {
        delta = MathUtils.map(0f, CAMERA_WIDTH, 0f, 45f, delta);
        camera.rotateAround(Vector3.Zero, Vector3.Y, delta);
    }

    @Override
    public void touchDown(float x, float y) {
        if (!free) return;
        pivot.set(x, y);
    }

    @Override
    public void touchDragged(float x, float y) {
        if (!free) return;

        dragDeltaX = MathUtils.clamp(pivot.x - x, -10f, 10f) / 3f;
        dragDeltaY = MathUtils.clamp(y - pivot.y, -10f, 10f) / 3f;

        camera.rotateAround(Vector3.Zero, Vector3.Y, dragDeltaX);
        camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), dragDeltaY);
        if (camera.direction.y < -0.9f || camera.direction.y > 0.9f) {
            camera.rotateAround(Vector3.Zero, Vector3.Y, -dragDeltaX);
            camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), -dragDeltaY);
        }
        camera.update();
        pivot.set(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == F) {
            free = !free;
        }
    }
}