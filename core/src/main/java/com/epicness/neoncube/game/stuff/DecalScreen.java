package com.epicness.neoncube.game.stuff;

import static com.epicness.fundamentals.SharedConstants.RATIO;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalScreen {

    private final Decal decal;

    public DecalScreen() {
        decal = Decal.newDecal(5f * RATIO, 5f, new Sprite(), true);
    }

    public void draw(DecalBatch decalBatch) {
        decalBatch.add(decal);
    }

    public void translateX(float amount) {
        decal.translateX(amount);
    }

    public void setSprite(Sprite sprite) {
        decal.setTextureRegion(sprite);
    }
}