package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;

public class Cylinder extends Shape3D {

    private final ModelInstance cylinder;
    private final float width, height, depth;
    private final Vector3[] rotationVertices;
    private final float[] plainVertices;
    private final Line3D[] debugLines;

    private Cylinder(float width, float height, float depth, int divisions, long attributes,
                     Color color, float angleFrom, float angleTo) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        if ((attributes & TextureCoordinates) == 0) {
            material.set(ColorAttribute.createDiffuse(color));
        }

        ModelBuilderPlus modelBuilder = new ModelBuilderPlus();
        Model model = modelBuilder.createCylinder(width, height, depth, divisions,
            material, attributes, angleFrom, angleTo);
        cylinder = new ModelInstance(model);

        Mesh mesh = model.meshes.first();
        float[] verticesWithUV = new float[mesh.getNumVertices() * mesh.getVertexSize() / 4];
        mesh.getVertices(verticesWithUV);
        rotationVertices = new Vector3[verticesWithUV.length / 5];
        plainVertices = new float[rotationVertices.length * 3];
        for (int i = 0, v = 0; i < verticesWithUV.length; i += 5, v++) {
            rotationVertices[v] = new Vector3(verticesWithUV[i], verticesWithUV[i + 1], verticesWithUV[i + 2]);
            plainVertices[v * 3] = rotationVertices[v].x;
            plainVertices[v * 3 + 1] = rotationVertices[v].y;
            plainVertices[v * 3 + 2] = rotationVertices[v].z;
        }
        debugLines = new Line3D[rotationVertices.length];
        for (int i = 0; i < debugLines.length; i++) {
            debugLines[i] = new Line3D();
        }
        updateDebugLines();
        storeIndices(model);
    }

    protected Cylinder(CylinderBuilder builder) {
        this(builder.width, builder.height, builder.depth, builder.divisions, builder.attributes,
            builder.color, builder.angleFrom, builder.angleTo);
    }

    private void updateDebugLines() {
        for (int i = 0; i < rotationVertices.length / 2; i++) {
            debugLines[i].set(
                rotationVertices[i].x + position.x,
                rotationVertices[i].y + position.y,
                rotationVertices[i].z + position.z,
                rotationVertices[(i + 2) % rotationVertices.length].x + position.x,
                rotationVertices[(i + 2) % rotationVertices.length].y + position.y,
                rotationVertices[(i + 2) % rotationVertices.length].z + position.z
            );
        }
        for (int i = rotationVertices.length / 2; i < rotationVertices.length - 2; i++) {
            debugLines[i].set(
                rotationVertices[i].x + position.x,
                rotationVertices[i].y + position.y,
                rotationVertices[i].z + position.z,
                rotationVertices[(i + 2) % rotationVertices.length].x + position.x,
                rotationVertices[(i + 2) % rotationVertices.length].y + position.y,
                rotationVertices[(i + 2) % rotationVertices.length].z + position.z
            );
        }
        int i = rotationVertices.length - 2;
        debugLines[i].set(
            rotationVertices[i].x + position.x,
            rotationVertices[i].y + position.y,
            rotationVertices[i].z + position.z,
            rotationVertices[(i + 1) % rotationVertices.length].x + position.x,
            rotationVertices[(i + 1) % rotationVertices.length].y + position.y,
            rotationVertices[(i + 1) % rotationVertices.length].z + position.z
        );
        i = rotationVertices.length - 1;
        debugLines[i].set(
            rotationVertices[0].x + position.x,
            rotationVertices[0].y + position.y,
            rotationVertices[0].z + position.z,
            rotationVertices[1].x + position.x,
            rotationVertices[1].y + position.y,
            rotationVertices[1].z + position.z
        );
    }

    public float[] getVertices() {
        return plainVertices;
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(cylinder);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(cylinder, environment);
    }

    public void drawDebug(ModelBatch modelBatch) {
        for (int i = 0; i < debugLines.length; i++) {
            debugLines[i].draw(modelBatch);
        }
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
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getZ() {
        return position.z;
    }

    public void translate(float xAmount, float yAmount, float zAmount) {
        cylinder.transform.translate(xAmount, yAmount, zAmount);
        position.add(xAmount, yAmount, zAmount);
        for (int i = 0; i < plainVertices.length; i += 3) {
            plainVertices[i] += xAmount;
            plainVertices[i + 1] += yAmount;
            plainVertices[i + 2] += zAmount;
        }
        updateDebugLines();
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

    public float getYRotation() {
        return cylinder.transform.getRotation(QUATERNION_HELPER).getAngleAround(Vector3.Y);
    }

    public void rotate(float xDegrees, float yDegrees, float zDegrees) {
        cylinder.transform.rotate(Vector3.X, xDegrees);
        cylinder.transform.rotate(Vector3.Y, yDegrees);
        cylinder.transform.rotate(Vector3.Z, zDegrees);
        for (int i = 0; i < rotationVertices.length; i++) {
            rotationVertices[i].rotate(Vector3.X, xDegrees);
            rotationVertices[i].rotate(Vector3.Y, yDegrees);
            rotationVertices[i].rotate(Vector3.Z, zDegrees);
            plainVertices[i * 3] = rotationVertices[i].x + position.x;
            plainVertices[i * 3 + 1] = rotationVertices[i].y + position.y;
            plainVertices[i * 3 + 2] = rotationVertices[i].z + position.z;
        }
        updateDebugLines();
    }

    public void rotateX(float degrees) {
        rotate(degrees, 0f, 0f);
    }

    public void rotateY(float degrees) {
        rotate(0f, degrees, 0f);
    }

    public void rotateZ(float degrees) {
        rotate(0f, 0f, degrees);
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