package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.GL20.GL_LINES;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.ColorUnpacked;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Line3D {

    private ModelInstance lineInstance;
    private final ModelBuilder modelBuilder;
    private final Color color;
    private final Vector3 start, end;

    public Line3D(float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
        modelBuilder = new ModelBuilder();
        this.color = color;
        start = new Vector3();
        end = new Vector3();
        set(x1, y1, z1, x2, y2, z2);
    }

    public Line3D(float x1, float y1, float z1, float x2, float y2, float z2) {
        this(x1, y1, z1, x2, y2, z2, RED);
    }

    public Line3D(Vector3 start, Vector3 end, Color color) {
        this(start.x, start.y, start.z, end.x, end.y, end.z, color);
    }

    public Line3D(Vector3 start, Vector3 end) {
        this(start, end, RED);
    }

    public void set(float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
        start.set(x1, y1, z1);
        end.set(x2, y2, z2);
        modelBuilder.begin();
        MeshPartBuilder meshPartBuilder = modelBuilder.part("line", GL_LINES, Position | ColorUnpacked, new Material());
        meshPartBuilder.setColor(color);
        meshPartBuilder.line(x1, y1, z1, x2, y2, z2);
        Model lineModel = modelBuilder.end();
        lineInstance = new ModelInstance(lineModel);
    }

    public void set(float x1, float y1, float z1, float x2, float y2, float z2) {
        set(x1, y1, z1, x2, y2, z2, color);
    }

    public void set(Vector3 v1, Vector3 v2, Color color) {
        set(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, color);
    }

    public void set(Vector3 v1, Vector3 v2) {
        set(v1, v2, color);
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(lineInstance);
    }

    public Ray getRay() {
        return new Ray(start, end.cpy().sub(start));
    }
}