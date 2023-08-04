package com.epicness.neoncube.game.stuff.tridimensional;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalScreen extends Decal {

    public DecalScreen() {
        setTextureRegion(new Sprite());
        setBlending(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); // Enable transparency
        dimensions.x = DECAL_SCREEN_WIDTH;
        dimensions.y = DECAL_SCREEN_HEIGHT;
        setColor(1, 1, 1, 1);
    }

    public void draw(DecalBatch decalBatch) {
        decalBatch.add(this);
    }

    public void setSprite(Sprite sprite) {
        setTextureRegion(sprite);
    }
}