package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

// TODO: scaling
// TODO: collision
// TODO: builder
public class Plane {

    private final ModelInstance plane;

    private Plane(float width, float height, long attributes) {
        ModelBuilder modelBuilder = new ModelBuilder();

        Material material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
            FloatAttribute.createAlphaTest(0.5f),
            IntAttribute.createCullFace(GL20.GL_NONE)
        );

        float hw = width / 2f, hh = height / 2f;
        plane = new ModelInstance(modelBuilder.createRect(
            -hw, -hh, 0f, // v1
            hw, -hh, 0f, // v2
            hw, hh, 0f, // v3
            -hw, hh, 0f, // v4
            1f, 0.8f, 0.2f, // normals
            material, attributes
        ));
    }

    protected Plane(PlaneBuilder builder) {
        this(builder.width, builder.height, builder.attributes);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(plane, environment);
    }

    public void setSprite(Sprite sprite) {
        plane.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    public void translate(float xAmount, float yAmount, float zAmount) {
        plane.transform.translate(xAmount, yAmount, zAmount);
    }

    public void translateX(float amount) {
        translate(amount, 0f, 0f);
    }

    public void translateY(float amount) {
        translate(0f, amount, 0f);
    }

    public void translateZ(float amount) {
        translate(0f, 0f, amount);
    }

    public void rotateX(float degrees) {
        plane.transform.rotate(Vector3.X, degrees);
    }

    public void rotateY(float degrees) {
        plane.transform.rotate(Vector3.Y, degrees);
    }

    public void rotateZ(float degrees) {
        plane.transform.rotate(Vector3.Z, degrees);
    }

    public void rotate(float xDegrees, float yDegrees, float zDegrees) {
        rotateX(xDegrees);
        rotateY(yDegrees);
        rotateX(zDegrees);
    }

    public static final class PlaneBuilder {

        private float width, height;
        private long attributes;

        public PlaneBuilder() {
            width = height = 5f;
            attributes = Position | Normal | TextureCoordinates;
        }

        public PlaneBuilder width(float width) {
            this.width = width;
            return this;
        }

        public PlaneBuilder height(float height) {
            this.height = height;
            return this;
        }

        public PlaneBuilder disableLight() {
            attributes &= ~Normal;
            return this;
        }

        public Plane build() {
            return new Plane(width, height, attributes);
        }
    }
}