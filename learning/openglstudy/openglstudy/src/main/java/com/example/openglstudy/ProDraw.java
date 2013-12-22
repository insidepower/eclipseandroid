package com.example.openglstudy;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by knxy on 12/22/13.
 */
public class ProDraw extends Fragment {
    private GLSurfaceView mGlView;
    private int choice;

    public ProDraw(int choice) {
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context myContext = getActivity().getApplicationContext();
        mGlView = new GLSurfaceView(myContext);
        mGlView.setEGLConfigChooser(false);
        switch(choice){
            case MainActivity.PRO_DRAW_TRIANGLE:
                mGlView.setRenderer(new ProSimpleTriangle(myContext));
                mGlView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                break;
            default:break;
        }

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
