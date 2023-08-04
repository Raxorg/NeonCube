package com.epicness.neoncube.game.stuff.bidimensional;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_SIZE;
import static com.epicness.neoncube.game.constants.GameConstants.GRID_COLUMNS;
import static com.epicness.neoncube.game.constants.GameConstants.GRID_ROWS;
import static com.epicness.neoncube.game.constants.GameConstants.STICKMAN_WORLD_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.STICKMAN_WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.CellGrid;
import com.epicness.neoncube.game.assets.GameAssets;

public class StickmanWorld {

    private final Sprited background;
    public final CellGrid grid;
    public final DelayedRemovalArray<Ladder> ladders;
    public final Player player, playerMirror;

    public StickmanWorld(SharedAssets sharedAssets, GameAssets assets) {
        background = new Sprited(sharedAssets.getPixel());
        background.setSize(STICKMAN_WORLD_WIDTH, STICKMAN_WORLD_HEIGHT);
        background.setColor(BLUE.cpy().lerp(CLEAR, 0.2f));

        grid = new CellGrid(GRID_COLUMNS, GRID_ROWS, assets.getRoundedSquare());
        grid.setCellSize(CELL_SIZE);
        grid.setColor(BLACK);

        ladders = new DelayedRemovalArray<>();
        for (int i = 0; i < 7; i++) {
            Ladder ladder = new Ladder(Math.max(7 - i, 1), assets.getLadder());
            ladder.setX(i * CAMERA_WIDTH / 4f);
            ladders.add(ladder);
        }

        player = new Player(assets);
        playerMirror = new Player(assets);
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        grid.draw(spriteBatch);
        for (int i = 0; i < ladders.size; i++) {
            ladders.get(i).draw(spriteBatch);
        }
        player.draw(spriteBatch);
        playerMirror.draw(spriteBatch);
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        for (int i = 0; i < ladders.size; i++) {
            ladders.get(i).drawDebug(shapeRenderer);
        }
        shapeRenderer.setColor(GREEN);
        player.drawDebug(shapeRenderer);
        playerMirror.drawDebug(shapeRenderer);
    }
}