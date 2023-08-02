package com.epicness.neoncube.game.assets;


import static com.epicness.neoncube.game.assets.GameAssetPaths.ASSETS;
import static com.epicness.neoncube.game.assets.GameAssetPaths.LADDER_SPRITE;
import static com.epicness.neoncube.game.assets.GameAssetPaths.STICKMAN_CLIMBING_ANIMATION;
import static com.epicness.neoncube.game.assets.GameAssetPaths.STICKMAN_IDLE_SPRITE;
import static com.epicness.neoncube.game.assets.GameAssetPaths.STICKMAN_RUNNING_ANIMATION;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;

public class GameAssets extends Assets {

    private Sprite[] stickmanClimbing;
    private Sprite[] stickmanRunning;
    private Sprite ladder;
    private Sprite stickmanIdle;

    public GameAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        stickmanClimbing = get(STICKMAN_CLIMBING_ANIMATION);
        stickmanRunning = get(STICKMAN_RUNNING_ANIMATION);
        ladder = get(LADDER_SPRITE);
        stickmanIdle = get(STICKMAN_IDLE_SPRITE);
    }

    public Sprite[] getStickmanClimbing() {
        return stickmanClimbing;
    }

    public Sprite[] getStickmanRunning() {
        return stickmanRunning;
    }

    public Sprite getLadder() {
        return ladder;
    }

    public Sprite getStickmanIdle() {
        return stickmanIdle;
    }
}