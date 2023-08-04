package com.epicness.neoncube.game.logic.player;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.bidimensional.Ladder;
import com.epicness.neoncube.game.stuff.bidimensional.Player;

public class LadderDetector extends GameLogicHandler {

    private Player player;
    private DelayedRemovalArray<Ladder> ladders;
    private Ladder detectedLadder;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        ladders = stuff.getStickmanWorld().ladders;
        detectedLadder = null;
    }

    @Override
    protected void update(float delta) {
        detectedLadder = null;
        detectLadder();
    }

    public void detectLadder() {
        for (int i = 0; i < ladders.size; i++) {
            if (ladders.get(i).bounds.contains(player.getCenterX(), player.getY())) {
                detectedLadder = ladders.get(i);
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