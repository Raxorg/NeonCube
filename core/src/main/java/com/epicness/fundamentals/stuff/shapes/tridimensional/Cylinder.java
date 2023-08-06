package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class Cylinder {

    private final ModelInstance cylinderInstance;

    public Cylinder(float width, float height, float depth, int divisions, float angleFrom, float angleTo, Sprite sprite) {
        Material material = new Material(
                TextureAttribute.createDiffuse(sprite),
                new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
                FloatAttribute.createAlphaTest(0.5f),
                IntAttribute.createCullFace(GL20.GL_NONE));

        ModelBuilderPlus modelBuilder = new ModelBuilderPlus();
        long attributes = Position | Normal | TextureCoordinates;
        Model curveModel = modelBuilder.createCylinder(width, height, depth, divisions,
                material, attributes, angleFrom, angleTo);
        cylinderInstance = new ModelInstance(curveModel);
    }

    public static Cylinder buildHalfCylinder(float width, float height, float depth, int divisions, Sprite sprite) {
        return new Cylinder(width, height, depth, divisions, 0f, 180f, sprite);
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(cylinderInstance);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(cylinderInstance, environment);
    }

    public void translate(float x, float y, float z) {
        cylinderInstance.transform.translate(x, y, z);
    }
}