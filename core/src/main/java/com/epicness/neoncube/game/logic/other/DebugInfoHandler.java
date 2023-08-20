package com.epicness.neoncube.game.logic.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.StringBuilder;
import com.epicness.neoncube.game.logic.GameLogicHandler;

public class DebugInfoHandler extends GameLogicHandler {

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
}