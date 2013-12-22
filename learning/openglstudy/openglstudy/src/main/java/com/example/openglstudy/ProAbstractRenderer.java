package com.example.openglstudy;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by knxy on 12/22/13.
 */
public abstract class ProAbstractRenderer implements GLSurfaceView.Renderer {


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glDisable(GL10.GL_DITHER); // Disable dithering for better performance
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl10.glClearColor(.5f, .5f, .5f, 1);
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        gl10.glViewport(0, 0, w, h);
        float ratio = (float) w/h;
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        gl10.glFrustumf(-ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glDisable(GL10.GL_DITHER);
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        /// point of camera,   looking at point,    Up Vector
        GLU.gluLookAt(gl10, 0, 0, 5, 0f, 0f, 0f, 0f, 1.0f, 0f);
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        this.draw(gl10);
    }

    protected abstract void draw(GL10 gl10);
}
