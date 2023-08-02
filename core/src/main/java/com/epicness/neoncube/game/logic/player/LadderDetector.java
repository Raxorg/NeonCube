package com.epicness.neoncube.game.logic.player;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class LadderDetector extends GameLogicHandler {

    private Player player;
    private DelayedRemovalArray<Sprited> ladders;
    private Sprited detectedLadder;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        ladders = stuff.getStickmanWorld().ladders;
    }

    @Override
    protected void update(float delta) {
        detectedLadder = null;
        for (int i = 0; i < ladders.size; i++) {
            if (player.overlaps(ladders.get(i))) {
                detectedLadder = ladders.get(i);
                return;
            }
        }
    }

    public boolean isLadderDetected() {
        return detectedLadder != null;
    }
}