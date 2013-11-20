package com.example.openglstudy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by knxy on 11/20/13.
 */
public class DrawShapeSquare {
    // buffer holding the vertices
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;

    // initial vertex definition
    // 0, 1, 2 (first triangle)
    // 1, 2, 3 (second triangle)
    // vertex 1 and 2 will be common for both triangle
    private float vertices[] = {
            -1.0f , 1.0f , 0.0f,       // upper left
             1.0f , 1.0f , 0.0f,       // upper right
            -1.0f , -1.0f, 0.0f,       // bottom left (CW, draw one triangle)
             1.0f , -1.0f, 0.0f        // bottom right

            //-1.0f , 1.0f , 0.0f,       // upper left
            //1.0f , 1.0f , 0.0f,        // upper right
            //1.0f , -1.0f, 0.0f,        // bottom right
            //-1.0f , -1.0f, 0.0f        // bottom left   (draw a pac man shape, instead square)
    };

    private float[] colors = {
            1.0f, 0.0f, 0.0f, 1.0f,     // red
            0.0f, 1.0f, 0.0f, 1.0f,     // green
            0.0f, 0.0f, 1.0f, 1.0f,     // blue
            0.0f, 1.0f, 0.0f, 1.0f,     // green
    };

    public DrawShapeSquare() {
        // A float is represented by 32 bits, which equals four bytes.
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length*4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // setup color array
        byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    /*
    In a scene composed entirely of opaque closed surfaces, back-facing
    polygons are never visible. Eliminating these invisible polygons has the
    obvious benefit of speeding up the rendering of the image. To enable and
    disable elimination of back-facing polygons, call glEnable and glDisable
    with argument GL_CULL_FACE.

    void glVertexPointer(size,type,stride,pointer);
    size - Specifies the number of coordinates per vertex. Must be 2, 3, or 4. The initial value is 4.
    type - Specifies the data type of each coordinate in the array. Symbolic constants GL_SHORT, GL_INT, GL_FLOAT, or GL_DOUBLE are accepted. The initial value is GL_FLOAT.
    stride - Specifies the byte offset between consecutive vertices. If stride is 0, the vertices are understood to be tightly packed in the array. The initial value is 0.
    pointer - Specifies a pointer to the first coordinate of the first vertex in the array. The initial value is 0.

    glDrawArrays — render primitives from array data
    void glDrawArrays(mode,first,count);
    mode - Specifies what kind of primitives to render. Symbolic constants GL_POINTS, GL_LINE_STRIP, GL_LINE_LOOP, GL_LINES, GL_TRIANGLE_STRIP, GL_TRIANGLE_FAN, GL_TRIANGLES, GL_QUAD_STRIP, GL_QUADS, and GL_POLYGON are accepted.
    first - Specifies the starting index in the enabled arrays.
    count - Specifies the number of indices to be rendered.
3
     */
    public void draw(GL10 gl) {
        // set the face rotation
        gl.glFrontFace(GL10.GL_CW);

        // point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        // enable vertex buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // color
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // we define the colors of the vertices and copy them into a color-array buffer.
        // We enable color-array client-state. The colors will be rendered together with
        // the vertices in glDrawElements().
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        // Draw the vertices as triangle strip
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length/3);

        // disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}
