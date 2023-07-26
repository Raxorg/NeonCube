package com.epicness.neoncube.game.logic;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraHandler extends GameLogicHandler {

    private PerspectiveCamera camera;
    private Vector2 pivot;

    @Override
    protected void init() {
        camera = renderer.getPerspectiveCamera();
        camera.translate(0f, 0f, 10f);
        camera.update();
        pivot = new Vector2();
    }

    @Override
    public void touchDown(float x, float y) {
        pivot.set(x, y);
    }

    @Override
    public void touchDragged(float x, float y) {
        float deltaX = pivot.x - x;
        float deltaY = y - pivot.y;
        camera.rotateAround(Vector3.Zero, Vector3.Y, deltaX);
        camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), deltaY);
        camera.update();
        pivot.set(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {

        }
    }

    @Override
    public void keyUp(int keycode) {
        switch (keycode) {

        }
    }
}