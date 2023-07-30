package com.epicness.neoncube.game.logic;

import static com.badlogic.gdx.Input.Keys.F;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.GameConstants.PLAYER_STARTING_X;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.epicness.neoncube.game.stuff.Player;

public class CameraHandler extends GameLogicHandler {

    private PerspectiveCamera camera;
    private Vector2 pivot;
    private boolean free;
    private Player player;
    private float lastPlayerX;

    @Override
    protected void init() {
        camera = renderer.getPerspectiveCamera();
        camera.position.set(0f, 0f, 10f);
        camera.update();

        pivot = new Vector2();
        free = false;
        player = stuff.getStickmanWorld().getPlayer();
        lastPlayerX = PLAYER_STARTING_X;
    }

    @Override
    public void update() {
        float deltaX = player.getX() - lastPlayerX;
        deltaX = MathUtils.map(0f, CAMERA_WIDTH, 0f, 90f, deltaX);
        camera.rotateAround(Vector3.Zero, Vector3.Y, deltaX);
        camera.update();
        lastPlayerX = player.getX();
    }

    @Override
    public void touchDown(float x, float y) {
        if (!free) return;
        pivot.set(x, y);
    }

    @Override
    public void touchDragged(float x, float y) {
        if (!free) return;

        float deltaX = MathUtils.clamp(pivot.x - x, -10f, 10f);
        float deltaY = MathUtils.clamp(y - pivot.y, -10f, 10f);

        camera.rotateAround(Vector3.Zero, Vector3.Y, deltaX);
        camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), deltaY);
        if (camera.direction.y < -0.9f || camera.direction.y > 0.9f) {
            camera.rotateAround(Vector3.Zero, Vector3.Y, -deltaX);
            camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), -deltaY);
        }
        camera.update();
        pivot.set(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case F:
                free = !free;
                break;
        }
    }

    @Override
    public void keyUp(int keycode) {
        switch (keycode) {

        }
    }
}