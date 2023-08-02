package com.epicness.neoncube.game.logic.player;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.neoncube.game.logic.GameLogicHandler;
import com.epicness.neoncube.game.stuff.Player;

public class LadderDetector extends GameLogicHandler {

    private Player player;
    private DelayedRemovalArray<Sprited> ladders;
    private boolean onLadder;

    @Override
    protected void init() {
        player = stuff.getStickmanWorld().player;
        ladders = stuff.getStickmanWorld().ladders;
    }

    @Override
    protected void update(float delta) {
        onLadder = false;
        for (int i = 0; i < ladders.size; i++) {
            if (player.overlaps(ladders.get(i))) {
                onLadder = true;
                return;
            }
        }
    }

    public boolean isOnLadder() {
        return onLadder;
    }
}