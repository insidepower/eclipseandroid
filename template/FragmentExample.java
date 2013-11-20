package com.example.openglstudy;

import android.support.v4.app.Fragment;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by knxy on 11/20/13.
 *
 *
 * This program doesn't do much: it clears the screen to black on every frame.
 * But it is a complete OpenGL application, hat correctly implements the Android
 * activity life-cycle. It pauses rendering when the activity is paused, and
 * resumes it when the activity is resumed. Notice that you don't even need to
 * subclass the GLSurfaceView view.
 *
 Note that the GLSurfaceView.Renderer interface has three methods:

 The onSurfaceCreated() method is called at the start of rendering, and whenever
 the OpenGL ES drawing context has to be recreated. (The drawing context is
 typically lost and recreated when the activity is paused and resumed.)
 OnSurfaceCreated() is a good place to create long-lived OpenGL resources like
 textures.

 The onSurfaceChanged() method is called when the surface changes size. It's a
 good place to set your OpenGL viewport. You may also want to set your camera
 here, if it's a fixed camera that doesn't move around the scene.

 The onDrawFrame() method is called every frame, and is responsible for drawing
 the scene. You would typically start by calling glClear to clear the
 framebuffer, followed by other OpenGL ES calls to draw the current scene.
 *
 *
 *
 */
public class BlankOpenGl extends Fragment {
    private GLSurfaceView mGLView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mGLView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLSurfaceView(getActivity().getApplicationContext());
        mGLView.setRenderer(new ClearRenderer());
    }

    @Override
    public void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGLView.onResume();
    }
}

class ClearRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }
}
