package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Plane extends Shape3D {

    private final ModelInstance plane;
    private final float width, height;
    private final Vector3[] rotationVertices;
    private final float[] plainVertices;
    private final Line3D[] debugLines;

    private Plane(float width, float height, long attributes) {
        this.width = width;
        this.height = height;

        float hw = width / 2f, hh = height / 2f;
        Model model = new ModelBuilder().createRect(
            -hw, -hh, 0f, // v1
            hw, -hh, 0f, // v2
            hw, hh, 0f, // v3
            -hw, hh, 0f, // v4
            1f, 0.8f, 0.2f, // normals
            material, attributes
        );
        plane = new ModelInstance(model);

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

    protected Plane(PlaneBuilder builder) {
        this(builder.width, builder.height, builder.attributes);
    }

    public float[] getVertices() {
        return plainVertices;
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(plane);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(plane, environment);
    }

    public void drawDebug(ModelBatch modelBatch) {
        for (int i = 0; i < debugLines.length; i++) {
            debugLines[i].draw(modelBatch);
        }
    }

    public void setSprite(Sprite sprite) {
        plane.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    private void updateDebugLines() {
        for (int i = 0; i < rotationVertices.length; i++) {
            int endIndex = (i + 1) % rotationVertices.length;
            debugLines[i].set(
                rotationVertices[i].x + position.x,
                rotationVertices[i].y + position.y,
                rotationVertices[i].z + position.z,
                rotationVertices[endIndex].x + position.x,
                rotationVertices[endIndex].y + position.y,
                rotationVertices[endIndex].z + position.z
            );
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
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
        plane.transform.translate(xAmount, yAmount, zAmount);
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
        return plane.transform.getRotation(QUATERNION_HELPER).getAngleAround(Vector3.Y);
    }

    public void rotate(float xDegrees, float yDegrees, float zDegrees) {
        plane.transform.rotate(Vector3.X, xDegrees);
        plane.transform.rotate(Vector3.Y, yDegrees);
        plane.transform.rotate(Vector3.Z, zDegrees);
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