package com.example.openglstudy;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by knxy on 12/22/13.
 */
public class ProSimpleTriangle extends ProAbstractRenderer {
    private final static int VERTS = 3;
    private FloatBuffer mFVertexBuffer;
    private ShortBuffer mIndexBuffer;



    public ProSimpleTriangle(Context myContext) {
        float[] coords = {
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.0f, 0.5f, 0,
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(coords.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mFVertexBuffer = vbb.asFloatBuffer();
        mFVertexBuffer.put(coords);
        mFVertexBuffer.position(0);

        short[] mIndexArray = {0,1,2};
        ByteBuffer ibb = ByteBuffer.allocateDirect( mIndexArray.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        mIndexBuffer = ibb.asShortBuffer();
        mIndexBuffer.put(mIndexArray);
        mIndexBuffer.position(0);
    }


    @Override
    protected void draw(GL10 gl10) {
        gl10.glColor4f(0.0f, 1.0f, 1.0f, 0.5f);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, mFVertexBuffer);
        gl10.glDrawElements(GL10.GL_TRIANGLES, VERTS, GL10.GL_UNSIGNED_SHORT, mIndexBuffer);
    }
}
