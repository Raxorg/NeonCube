package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class Cylinder {

    private final ModelInstance cylinderInstance;
    private final float width, depth;

    private Cylinder(float width, float height, float depth, int divisions, long attributes,
                     Color color, float angleFrom, float angleTo) {
        this.width = width;
        this.depth = depth;

        Material material = new Material(
                "material",
                new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
                FloatAttribute.createAlphaTest(0.5f),
                IntAttribute.createCullFace(GL20.GL_NONE));

        if ((attributes & TextureCoordinates) == 0) {
            material.set(ColorAttribute.createDiffuse(color));
        }

        ModelBuilderPlus modelBuilder = new ModelBuilderPlus();
        Model curveModel = modelBuilder.createCylinder(width, height, depth, divisions,
                material, attributes, angleFrom, angleTo);
        cylinderInstance = new ModelInstance(curveModel);
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

    public void setSprite(Sprite sprite) {
        cylinderInstance.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    public void setColor(Color color) {
        cylinderInstance.getMaterial("material").set(ColorAttribute.createDiffuse(color));
    }

    public float getWidth() {
        return width;
    }

    public float getDepth() {
        return depth;
    }

    public static final class CylinderBuilder {

        private float width, height, depth;
        private int divisions;
        private long attributes;
        Color color;
        float angleFrom, angleTo;

        public CylinderBuilder() {
            width = height = depth = 5f;
            divisions = 10;
            attributes = Position | Normal | TextureCoordinates;
            color = WHITE.cpy();
            angleFrom = 0f;
            angleTo = 360f;
        }

        public CylinderBuilder size(float width, float height, float depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            return this;
        }

        public CylinderBuilder size(float size) {
            return size(size, size, size);
        }

        public CylinderBuilder width(float width) {
            this.width = width;
            return this;
        }

        public CylinderBuilder height(float height) {
            this.height = height;
            return this;
        }

        public CylinderBuilder depth(float depth) {
            this.depth = depth;
            return this;
        }

        public CylinderBuilder divisions(int divisions) {
            this.divisions = divisions;
            return this;
        }

        public CylinderBuilder disableLight() {
            attributes &= ~Normal;
            return this;
        }

        public CylinderBuilder disableSprite() {
            attributes &= ~TextureCoordinates;
            return this;
        }

        public CylinderBuilder colorOnly() {
            return disableLight().disableSprite();
        }

        public CylinderBuilder color(Color color) {
            this.color.set(color);
            return this;
        }

        public CylinderBuilder angleFrom(float angleFrom) {
            this.angleFrom = angleFrom;
            return this;
        }

        public CylinderBuilder angleTo(float angleTo) {
            this.angleTo = angleTo;
            return this;
        }

        public Cylinder build() {
            return new Cylinder(width, height, depth, divisions, attributes, color, angleFrom, angleTo);
        }
    }
}