package com.example.openglstudy;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by knxy on 12/22/13.
 */
public class ProDraw extends Fragment {
    private ProGlView mGlView;
    private int choice;

    public ProDraw(int choice) {
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context myContext = getActivity().getApplicationContext();
        mGlView = new ProGlView(myContext, choice);

        return mGlView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mGlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGlView.onResume();
    }
}

class ProGlView extends GLSurfaceView{
    public GLSurfaceView.Renderer mRender;

    ProGlView(Context context, int choice) {
        super(context);
        switch(choice){
            case MainActivity.PRO_DRAW_TRIANGLE:
                mRender = new ProSimpleTriangle(context);
                setEGLConfigChooser(false);
                setRenderer(mRender);
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

                break;
            default:break;
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        /*queueEvent(new Runnable(){
            public void run() {
                mRenderer.setColor(event.getX()/getWidth(),
                        event.getY()/getHeight(), 1.0f);
            }
        });*/
        return true;
    }
}
