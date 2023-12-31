package com.epicness.neoncube.game.stuff;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.neoncube.game.constants.GameConstants.DECAL_SCREEN_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.fundamentals.stuff.Stuff;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Screen3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.WireframeCube;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.Cylinder;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.Plane;
import com.epicness.neoncube.game.assets.GameAssets;
import com.epicness.neoncube.game.stuff.bidimensional.StickmanWorld;

public class GameStuff extends Stuff<GameAssets> {

    // 3D
    private Environment environment;
    private ModelInstance cube;
    private DelayedRemovalArray<Screen3D<Plane>> planeScreens;
    private DelayedRemovalArray<Screen3D<Cylinder>> cylinderScreens;
    private Line3D line;
    private WireframeCube wireframeCube;
    // 2D
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
            IntAttribute.createCullFace(GL20.GL_NONE));

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createBox(1f, 1f, 1f, material, Position | Normal | TextureCoordinates);
        cube = new ModelInstance(model);

        planeScreens = new DelayedRemovalArray<>();
        cylinderScreens = new DelayedRemovalArray<>();
        line = new Line3D(0f, 0f, 0f, 5f, 5f, 5f);

        wireframeCube = new WireframeCube();
        wireframeCube.setSize(DECAL_SCREEN_WIDTH, 5f, DECAL_SCREEN_WIDTH);

        stickmanWorld = new StickmanWorld(sharedAssets, assets);

        sharedAssets.getPixelFont().getData().scale(3f);
        debugText = new Text(sharedAssets.getPixelFont());
        debugText.setText("FPS\nSPEED");
        debugText.setTextTargetWidth(CAMERA_WIDTH);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ModelInstance getCube() {
        return cube;
    }

    public DelayedRemovalArray<Screen3D<Plane>> getPlaneScreens() {
        return planeScreens;
    }

    public DelayedRemovalArray<Screen3D<Cylinder>> getCylinderScreens() {
        return cylinderScreens;
    }

    public Line3D getLine() {
        return line;
    }

    public WireframeCube getWireframeCube() {
        return wireframeCube;
    }

    public StickmanWorld getStickmanWorld() {
        return stickmanWorld;
    }

    public Text getDebugText() {
        return debugText;
    }
}