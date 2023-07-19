package com.epicness.neoncube.game.logic;

import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class CameraHandler extends GameLogicHandler {

    private FirstPersonCameraController cameraController;

    @Override
    protected void init() {
        cameraController = new FirstPersonCameraController(renderer.getPerspectiveCamera());
        cameraController.setVelocity(100f);
        renderer.getPerspectiveCamera().translate(0f, 0f, 100f);
        renderer.getPerspectiveCamera().update();
    }

    @Override
    public void update() {
        cameraController.update();
    }

    @Override
    public boolean touchDragged(float x, float y) {
        cameraController.touchDragged((int) x, (int) y, 0);
        return false;
    }

    public void keyPressed(int keycode) {
        cameraController.keyDown(keycode);
    }

    public void keyReleased(int keycode) {
        cameraController.keyUp(keycode);
    }
}