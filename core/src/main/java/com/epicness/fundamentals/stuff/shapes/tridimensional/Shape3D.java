package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public abstract class Shape3D {

    protected static final Quaternion QUATERNION_HELPER = new Quaternion();
    protected final Material material;
    protected short[] indices;
    protected final Vector3 position;

    public Shape3D() {
        material = new Material(
            "material",
            new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
            FloatAttribute.createAlphaTest(0.5f),
            IntAttribute.createCullFace(GL20.GL_NONE)
        );
        position = new Vector3();
    }

    protected final void storeIndices(Model model) {
        Mesh mesh = model.meshes.first();
        indices = new short[mesh.getNumIndices()];
        mesh.getIndices(indices);
    }

    public final short[] getIndices() {
        return indices;
    }
}