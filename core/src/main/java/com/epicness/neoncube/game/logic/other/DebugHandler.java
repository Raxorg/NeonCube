package com.epicness.neoncube.game.logic.other;

import static com.badlogic.gdx.Input.Keys.R;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.StringBuilder;
import com.epicness.neoncube.game.logic.GameLogicHandler;

public class DebugHandler extends GameLogicHandler {

    private StringBuilder stringBuilder;

    @Override
    protected void init() {
        stringBuilder = new StringBuilder();
    }

    @Override
    protected void update(float delta) {
        stringBuilder
            .append("FPS: ")
            .append(String.valueOf(Gdx.graphics.getFramesPerSecond()))
            .append("\n")
            .append("Speed: ")
            .append(String.valueOf(stuff.getStickmanWorld().player.speed));
        stuff.getDebugText().setText(stringBuilder.toStringAndClear());
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == R) {
            logic.restart();
        }
    }
}