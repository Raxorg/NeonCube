package com.epicness.neoncube.game.stuff.tridimensional;

import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class DecalCube {

    public final DelayedRemovalArray<DecalScreen> faces;

    public DecalCube() {
        faces = new DelayedRemovalArray<>();
        for (int i = 0; i < 4; i++) {
            DecalScreen decalScreen = new DecalScreen();
            decalScreen.rotateY(i * 90f);
            faces.add(decalScreen);
        }
        faces.get(0).translateZ(DECAL_SCREEN_WIDTH / 2f);
        faces.get(1).translateX(DECAL_SCREEN_WIDTH / 2f);
        faces.get(2).translateZ(-DECAL_SCREEN_WIDTH / 2f);
        faces.get(3).translateX(-DECAL_SCREEN_WIDTH / 2f);
    }

    public void draw(DecalBatch decalBatch) {
        for (int i = 0; i < faces.size; i++) {
            faces.get(i).draw(decalBatch);
        }
    }
}