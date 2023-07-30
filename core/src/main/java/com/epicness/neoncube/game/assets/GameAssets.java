package com.epicness.neoncube.game.assets;


import static com.epicness.neoncube.game.assets.GameAssetPaths.ASSETS;
import static com.epicness.neoncube.game.assets.GameAssetPaths.STICKMANIDLE_SPRITE;
import static com.epicness.neoncube.game.assets.GameAssetPaths.STICKMANRUNNING_ANIMATION;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;

public class GameAssets extends Assets {

    private Sprite stickmanIdle;
    private Sprite[] stickmanRunning;

    public GameAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        stickmanIdle = get(STICKMANIDLE_SPRITE);
        stickmanRunning = get(STICKMANRUNNING_ANIMATION);
    }

    public Sprite getStickmanIdle() {
        return stickmanIdle;
    }

    public Sprite[] getStickmanRunning() {
        return stickmanRunning;
    }
}