package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Plane extends Shape3D<PlaneWrapper, PlaneProperties> {

    private final Vector3[] rotationVertices;
    private final float[] plainVertices;
    private final Line3D[] debugLines;

    protected Plane(PlaneWrapper wrapper) {
        super(wrapper);

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

    public float[] getVertices() {
        return plainVertices;
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(modelInstance);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(modelInstance, environment);
    }

    public void drawDebug(ModelBatch modelBatch) {
        for (int i = 0; i < debugLines.length; i++) {
            debugLines[i].draw(modelBatch);
        }
    }

    public void setSprite(Sprite sprite) {
        modelInstance.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    @Override
    protected void updateDebugLines() {
        for (vertexIndex = 0; vertexIndex < rotationVertices.length; vertexIndex++) {
            nextVertexIndex = (vertexIndex + 1) % rotationVertices.length;
            debugLines[vertexIndex].set(
                rotationVertices[vertexIndex].x + position.x,
                rotationVertices[vertexIndex].y + position.y,
                rotationVertices[vertexIndex].z + position.z,
                rotationVertices[nextVertexIndex].x + position.x,
                rotationVertices[nextVertexIndex].y + position.y,
                rotationVertices[nextVertexIndex].z + position.z
            );
        }
    }

    public float getWidth() {
        return properties.width;
    }

    public float getHeight() {
        return properties.height;
    }

    @Override
    public void translate(float xAmount, float yAmount, float zAmount) {
        modelInstance.transform.translate(xAmount, yAmount, zAmount);
        position.add(xAmount, yAmount, zAmount);
        for (vertexIndex = 0; vertexIndex < plainVertices.length; vertexIndex += 3) {
            plainVertices[vertexIndex] += xAmount;
            plainVertices[vertexIndex + 1] += yAmount;
            plainVertices[vertexIndex + 2] += zAmount;
        }
        updateDebugLines();
    }

    @Override
    public void rotate(float xDegrees, float yDegrees, float zDegrees) {
        modelInstance.transform.rotate(Vector3.X, xDegrees);
        modelInstance.transform.rotate(Vector3.Y, yDegrees);
        modelInstance.transform.rotate(Vector3.Z, zDegrees);
        for (vertexIndex = 0; vertexIndex < rotationVertices.length; vertexIndex++) {
            rotationVertices[vertexIndex].rotate(Vector3.X, xDegrees);
            rotationVertices[vertexIndex].rotate(Vector3.Y, yDegrees);
            rotationVertices[vertexIndex].rotate(Vector3.Z, zDegrees);
            plainVertices[vertexIndex * 3] = rotationVertices[vertexIndex].x + position.x;
            plainVertices[vertexIndex * 3 + 1] = rotationVertices[vertexIndex].y + position.y;
            plainVertices[vertexIndex * 3 + 2] = rotationVertices[vertexIndex].z + position.z;
        }
        updateDebugLines();
    }
}