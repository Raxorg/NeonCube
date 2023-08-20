package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public abstract class Shape3D<M extends ModelWrapper<P>, P extends ModelProperties> {

    protected final M modelWrapper;
    protected final P properties;
    protected Model model;
    protected ModelInstance modelInstance;
    protected static int vertexIndex, nextVertexIndex;
    protected static final Quaternion QUATERNION_HELPER = new Quaternion();
    protected short[] indices;
    protected final Vector3 position;

    public Shape3D(M modelWrapper) {
        this.modelWrapper = modelWrapper;
        properties = modelWrapper.properties;
        model = modelWrapper.build(properties);
        modelInstance = new ModelInstance(model);
        position = new Vector3();
    }

    protected final void storeIndices(Model model) {
        Mesh mesh = model.meshes.first();
        indices = new short[mesh.getNumIndices()];
        mesh.getIndices(indices);
    }

    protected abstract void updateDebugLines();

    public final short[] getIndices() {
        return indices;
    }

    public final float getX() {
        return position.x;
    }

    public final float getY() {
        return position.y;
    }

    public final float getZ() {
        return position.z;
    }

    public abstract void translate(float xAmount, float yAmount, float zAmount);

    public final void translateX(float amount) {
        translate(amount, 0f, 0f);
    }

    public final void translateY(float amount) {
        translate(0f, amount, 0f);
    }

    public final void translateZ(float amount) {
        translate(0f, 0f, amount);
    }

    public float getYRotation() {
        return modelInstance.transform.getRotation(QUATERNION_HELPER).getAngleAround(Vector3.Y);
    }

    public abstract void rotate(float xDegrees, float yDegrees, float zDegrees);

    public final void rotateX(float degrees) {
        rotate(degrees, 0f, 0f);
    }

    public final void rotateY(float degrees) {
        rotate(0f, degrees, 0f);
    }

    public final void rotateZ(float degrees) {
        rotate(0f, 0f, degrees);
    }

    public final void setColor(Color color) {
        modelInstance.getMaterial("material").set(ColorAttribute.createDiffuse(color));
    }
}