package com.epicness.neoncube.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

public class GameAssetPaths {

    public static final List<AssetDescriptor<?>> ASSETS;
    public static final AssetDescriptor<Sprite> STICKMANIDLE_SPRITE;
    public static final AssetDescriptor<Sprite[]> STICKMANRUNNING_ANIMATION;

    static {
        ASSETS = new ArrayList<>();
        ASSETS.add(STICKMANIDLE_SPRITE = new AssetDescriptor<>("neoncube/stickmanIdle.png", Sprite.class));
        ASSETS.add(STICKMANRUNNING_ANIMATION = new AssetDescriptor<>("neoncube/stickmanRunning.anim", Sprite[].class));
    }
}