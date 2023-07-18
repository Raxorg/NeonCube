package com.epicness.neoncube.game.logic;

import com.epicness.fundamentals.input.InputHandler;
import com.epicness.neoncube.game.stuff.GameStuff;

public class GameInputHandler extends InputHandler<GameLogic, GameStuff> {

    @Override
    public void touchDragged(float x, float y) {
        logic.get(CameraHandler.class).touchDragged(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        logic.get(CameraHandler.class).keyPressed(keycode);
        logic.get(MovementHandler.class).keyPressed(keycode);
    }

    @Override
    public void keyUp(int keycode) {
        logic.get(CameraHandler.class).keyReleased(keycode);
        logic.get(MovementHandler.class).keyReleased(keycode);
    }
}