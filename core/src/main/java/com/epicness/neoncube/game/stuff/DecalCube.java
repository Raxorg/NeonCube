package com.epicness.neoncube.game.stuff;

import static com.epicness.neoncube.game.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalCube {

    private final DecalScreen[] faces;

    public DecalCube() {
        faces = new DecalScreen[4];
        for (int i = 0; i < faces.length; i++) {
            faces[i] = new DecalScreen();
            faces[i].rotateY(i * 90f);
        }
        faces[0].translateZ(DECAL_SCREEN_WIDTH / 2f);
        faces[1].translateX(DECAL_SCREEN_WIDTH / 2f);
        faces[2].translateZ(-DECAL_SCREEN_WIDTH / 2f);
        faces[3].translateX(-DECAL_SCREEN_WIDTH / 2f);
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