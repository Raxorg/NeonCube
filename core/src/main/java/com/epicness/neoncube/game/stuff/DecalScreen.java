package com.epicness.neoncube.game.stuff;

import static com.epicness.neoncube.game.GameConstants.DECAL_SCREEN_HEIGHT;
import static com.epicness.neoncube.game.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalScreen {

    private final Decal decal;

    public DecalScreen() {
        decal = Decal.newDecal(DECAL_SCREEN_WIDTH, DECAL_SCREEN_HEIGHT, new Sprite(), true);
    }

    public void draw(DecalBatch decalBatch) {
        decalBatch.add(decal);
    }

    public void translateX(float amount) {
        decal.translateX(amount);
    }

    public void translateY(float amount) {
        decal.translateY(amount);
    }

    public void translateZ(float amount) {
        decal.translateZ(amount);
    }

    public void rotateY(float degrees) {
        decal.rotateY(degrees);
    }

    public void setSprite(Sprite sprite) {
        decal.setTextureRegion(sprite);
    }
}