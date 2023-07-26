package com.epicness.neoncube.game.stuff;

import static com.epicness.fundamentals.SharedConstants.RATIO;

import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalCube {

    private final DecalScreen[] faces;

    public DecalCube() {
        faces = new DecalScreen[4];
        for (int i = 0; i < faces.length; i++) {
            faces[i] = new DecalScreen();
            faces[i].translateX(i * 5.1f * RATIO - 5f);
        }
    }

    public void draw(DecalBatch decalBatch) {
        for (int i = 0; i < faces.length; i++) {
            faces[i].draw(decalBatch);
        }
    }

    public DecalScreen[] getFaces() {
        return faces;
    }
}