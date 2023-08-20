package com.epicness.fundamentals.constants;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

public class Constants3D {

    public static final long LIGHTED_TEXTURED_ATTRIBUTES = Position | Normal | TextureCoordinates;
    public static final long LIGHTLESS_TEXTURED_ATTRIBUTES = Position | TextureCoordinates;
    public static final long LIGHTED_TEXTURELESS_ATTRIBUTES = Position | Normal;
    public static final long LIGHTLESS_TEXTURELESS_ATTRIBUTES = Position;
}