package com.epicness.neoncube.game.stuff;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.epicness.fundamentals.stuff.Stuff;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.neoncube.game.assets.GameAssets;

public class GameStuff extends Stuff<GameAssets> {

    private Environment environment;
    private ModelInstance cube;
    private DecalCube decalCube;
    private StickmanWorld stickmanWorld;
    private Text debugText;

    @Override
    public void initializeStuff() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        Material material = new Material(
                TextureAttribute.createDiffuse(sharedAssets.getSquare32Inverted()),
                new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
                FloatAttribute.createAlphaTest(0.5f),
                IntAttribute.createCullFace(GL20.GL_NONE));

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createBox(5f, 5f, 5f, material, Position | Normal | TextureCoordinates);

        cube = new ModelInstance(model);
        cube.transform.translate(0f, 0f, 0f);

        decalCube = new DecalCube();
        stickmanWorld = new StickmanWorld(sharedAssets, assets);

        sharedAssets.getPixelFont().getData().scale(5f);
        debugText = new Text(sharedAssets.getPixelFont());
        debugText.setText("FPS\nSPEED");
        debugText.setTextTargetWidth(CAMERA_WIDTH);
        debugText.setY(debugText.getHeight());
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ModelInstance getCube() {
        return cube;
    }

    public DecalCube getDecalCube() {
        return decalCube;
    }

    public StickmanWorld getStickmanWorld() {
        return stickmanWorld;
    }

    public Text getDebugText() {
        return debugText;
    }
}