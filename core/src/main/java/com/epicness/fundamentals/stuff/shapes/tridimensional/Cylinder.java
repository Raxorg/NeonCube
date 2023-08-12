package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
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
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;

public class Cylinder {

    private final ModelInstance cylinder;
    private final float width, height, depth;
    private final Vector3 translation;

    private final short[] indices;

    private Cylinder(float width, float height, float depth, int divisions, long attributes,
                     Color color, float angleFrom, float angleTo) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        translation = new Vector3();

        Material material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
            FloatAttribute.createAlphaTest(0.5f),
            IntAttribute.createCullFace(GL20.GL_NONE)
        );

        if ((attributes & TextureCoordinates) == 0) {
            material.set(ColorAttribute.createDiffuse(color));
        }

        ModelBuilderPlus modelBuilder = new ModelBuilderPlus();
        Model model = modelBuilder.createCylinder(width, height, depth, divisions,
            material, attributes, angleFrom, angleTo);
        cylinder = new ModelInstance(model);

        Mesh mesh = model.meshes.first();
        indices = new short[mesh.getNumIndices()];
        mesh.getIndices(indices);
    }

    protected Cylinder(CylinderBuilder builder) {
        this(builder.width, builder.height, builder.depth, builder.divisions, builder.attributes,
            builder.color, builder.angleFrom, builder.angleTo);
    }

    public float[] getVertices() {
        Mesh mesh = cylinder.model.meshes.first();

        float[] verticesWithUV = new float[mesh.getNumVertices() * mesh.getVertexSize() / 4];
        mesh.getVertices(verticesWithUV);

        float[] vertices = new float[mesh.getNumVertices() * 3];
        for (int i = 0, v = 0; v < vertices.length; i++) {
            int i2 = i + 1;
            if (i2 % 5 == 0 || i2 % 5 == 4) continue;
            if (i2 % 5 == 1) verticesWithUV[i] += translation.x;
            if (i2 % 5 == 2) verticesWithUV[i] += translation.y;
            if (i2 % 5 == 3) verticesWithUV[i] += translation.z;
            vertices[v] = verticesWithUV[i];
            v++;
        }

        return vertices;
    }

    public short[] getIndices() {
        return Arrays.copyOf(indices, indices.length);
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(cylinder);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(cylinder, environment);
    }

    public void setSprite(Sprite sprite) {
        cylinder.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getDepth() {
        return depth;
    }

    public float getX() {
        return translation.x;
    }

    public float getY() {
        return translation.y;
    }

    public float getZ() {
        return translation.z;
    }

    public void translate(float xAmount, float yAmount, float zAmount) {
        cylinder.transform.translate(xAmount, yAmount, zAmount);
        translation.add(xAmount, yAmount, zAmount);
    }

    public void rotateX(float degrees) {
        cylinder.transform.rotate(Vector3.X, degrees);
    }

    public void rotateY(float degrees) {
        cylinder.transform.rotate(Vector3.Y, degrees);
    }

    public void rotateZ(float degrees) {
        cylinder.transform.rotate(Vector3.Z, degrees);
    }

    public void rotate(float xDegrees, float yDegrees, float zDegrees) {
        rotateX(xDegrees);
        rotateY(yDegrees);
        rotateX(zDegrees);
    }

    public void setColor(Color color) {
        cylinder.getMaterial("material").set(ColorAttribute.createDiffuse(color));
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

        public CylinderBuilder angleRange(float angleFrom, float angleTo) {
            return angleFrom(angleFrom).angleTo(angleTo);
        }

        public Cylinder build() {
            return new Cylinder(width, height, depth, divisions, attributes, color, angleFrom, angleTo);
        }
    }
}