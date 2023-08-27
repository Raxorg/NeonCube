package com.epicness.neoncube.game.stuff.bidimensional;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.epicness.neoncube.game.constants.GameConstants.CELL_SIZE;
import static com.epicness.neoncube.game.constants.GameConstants.GRID_COLUMNS;
import static com.epicness.neoncube.game.constants.GameConstants.GRID_ROWS;
import static com.epicness.neoncube.game.constants.GameConstants.STICKMAN_WORLD_HEIGHT;
import static com.epicness.neoncube.game.constants.GameConstants.STICKMAN_WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.CellGrid;
import com.epicness.fundamentals.stuff.grid.CellGridBuilder;
import com.epicness.neoncube.game.assets.GameAssets;

public class StickmanWorld {

    public final Sprited background;
    public final CellGrid<ColorCell> grid;
    public final DelayedRemovalArray<Ladder> ladders;
    public final Player player, playerMirror;

    public StickmanWorld(SharedAssets sharedAssets, GameAssets assets) {
        background = new Sprited(sharedAssets.getPixel());
        background.setSize(STICKMAN_WORLD_WIDTH, STICKMAN_WORLD_HEIGHT);
        background.setColor(BLUE.cpy().lerp(CLEAR, 0.25f));

        ColorCellBuilder cellBuilder = new ColorCellBuilder();
        cellBuilder.sprite(assets.getRoundedSquare());
        CellGridBuilder<ColorCell> gridBuilder = new CellGridBuilder<>(cellBuilder);
        gridBuilder.columns(GRID_COLUMNS).rows(GRID_ROWS);
        grid = new CellGrid<>(gridBuilder);
        grid.setCellSize(CELL_SIZE);

        ladders = new DelayedRemovalArray<>();

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

    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        for (int i = 0; i < ladders.size; i++) {
            ladders.get(i).drawDebug(shapeDrawer);
        }
        shapeDrawer.setColor(GREEN);
        player.drawDebug(shapeDrawer);
        playerMirror.drawDebug(shapeDrawer);
    }
}