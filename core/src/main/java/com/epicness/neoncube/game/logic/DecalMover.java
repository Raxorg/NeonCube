package com.epicness.neoncube.game.logic;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.MathUtils;

import java.util.List;

public class DecalMover extends GameLogicHandler {

    private List<Decal> decals;
    private float time;

    @Override
    protected void init() {
        decals = stuff.getDecals();
        for (int i = 0; i < decals.size(); i++) {
            float angle = 360f / decals.size() * i;
            Decal decal = decals.get(i);
            decal.setPosition(MathUtils.sinDeg(angle) * 50f, MathUtils.cosDeg(angle) * 50f, 0f);
        }
    }

    @Override
    protected void update(float delta) {
        time += delta;
        for (int i = 0; i < decals.size(); i++) {
            float angle = 360f / decals.size() * i + time;
            Decal decal = decals.get(i);
            decal.setPosition(MathUtils.sinDeg(angle) * 1000f, MathUtils.cosDeg(angle) * 1000f, 0f);
            decal.rotateY(delta*45f);
        }
    }
}