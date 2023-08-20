package com.epicness.neoncube.game.logic.player;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.Ladder;
import com.epicness.neoncube.game.stuff.bidimensional.Player;

public class LadderDetector extends GameLogicHandler {

    private Player player;
    private DelayedRemovalArray<Ladder> ladders;
    private int ladderIndex;
    private Ladder detectedLadder;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        ladders = stuff.getStickmanWorld().ladders;
        ladderIndex = 0;
        detectedLadder = null;
    }

    @Override
    protected void update(float delta) {
        detectLadder();
    }

    public void detectLadder() {
        detectedLadder = null;
        for (ladderIndex = 0; ladderIndex < ladders.size; ladderIndex++) {
            if (ladders.get(ladderIndex).bounds.contains(player.getCenterX(), player.getY())) {
                detectedLadder = ladders.get(ladderIndex);
                return;
            }
        }
    }

    public boolean isLadderDetected() {
        return detectedLadder != null;
    }

    public Ladder getDetectedLadder() {
        return detectedLadder;
    }
}