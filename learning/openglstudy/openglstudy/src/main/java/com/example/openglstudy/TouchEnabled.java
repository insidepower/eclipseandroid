package com.example.openglstudy;

//import android.app.Fragment;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by knxy on 11/20/13.
 *
 This application clears the screen every frame. When you tap on the screen, it
 sets the clear color based on the (x,y) coordinates of your touch event. Note
 the use of queueEvent() in ClearGLSurfaceView.onTouchEvent(). The queueEvent()
 method is used to safely communicate between the UI thread and the rendering
 thread. If you prefer you can use some other Java cross-thread communication
 technique, such as synchronized methods on the Renderer class itself. But
 queueing events is often the simplest way of dealing with cross-thread
 communication.
 *
 */
public class TouchEnabled extends Fragment {
    GLSurfaceView mGLView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGLView = new ClearGLSurfaceView(getActivity().getApplicationContext());
        return mGLView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

class ClearGLSurfaceView extends GLSurfaceView {
    RenderColor mRenderer;

    public ClearGLSurfaceView(Context applicationContext) {
        super(applicationContext);
        mRenderer = new RenderColor();
        setRenderer(mRenderer);
    }

    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable(){
            public void run() {
                mRenderer.setColor(event.getX()/getWidth(),
                        event.getY()/getHeight(), 1.0f);
            }
        });
        return true;
    }
}

class RenderColor implements GLSurfaceView.Renderer {
    private float mRed;
    private float mGreen;
    private float mBlue;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //Do nothing
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0,0,i,i2);
    }

	/// clear the screen to following color
    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClearColor(mRed, mGreen, mBlue, 1.0f);
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }

	/// not opengl API
    public void setColor(float r, float g, float b){
        mRed = r;
        mGreen = g;
        mBlue = b;
    }
}
