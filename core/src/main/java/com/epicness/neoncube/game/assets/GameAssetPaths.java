package com.epicness.neoncube.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

public class GameAssetPaths {

    public static final List<AssetDescriptor<?>> ASSETS;
    public static final AssetDescriptor<Sprite[]> STICKMAN_CLIMBING_ANIMATION;
    public static final AssetDescriptor<Sprite[]> STICKMAN_RUNNING_ANIMATION;
    public static final AssetDescriptor<Sprite> LADDER_SPRITE;
    public static final AssetDescriptor<Sprite> STICKMAN_FALLING_SPRITE;
    public static final AssetDescriptor<Sprite> STICKMAN_IDLE_SPRITE;
    public static final AssetDescriptor<Texture> FIGURES_WITH_GLOW_ATLAS;

    static {
        ASSETS = new ArrayList<>();
        ASSETS.add(STICKMAN_CLIMBING_ANIMATION = new AssetDescriptor<>(
                "neoncube/animations/stickmanClimbing.anim", Sprite[].class));
        ASSETS.add(STICKMAN_RUNNING_ANIMATION = new AssetDescriptor<>(
                "neoncube/animations/stickmanRunning.anim", Sprite[].class));
        ASSETS.add(LADDER_SPRITE = new AssetDescriptor<>("neoncube/images/ladder.png", Sprite.class));
        ASSETS.add(STICKMAN_FALLING_SPRITE = new AssetDescriptor<>("neoncube/images/stickmanFalling.png", Sprite.class));
        ASSETS.add(STICKMAN_IDLE_SPRITE = new AssetDescriptor<>("neoncube/images/stickmanIdle.png", Sprite.class));
        ASSETS.add(FIGURES_WITH_GLOW_ATLAS = new AssetDescriptor<>("neoncube/images/figuresWithGlow.png", Texture.class));
    }
}