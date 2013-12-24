package com.example.openglstudy;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private float mPreviousX;
    private float mPreviousY;
    private int factor;
    public GLSurfaceView.Renderer mRender;

    ProGlView(Context context, int choice) {
        super(context);

        factor = 1;
        switch(choice){
            case MainActivity.PRO_DRAW_TRIANGLE:
            {
                mRender = new ProSimpleTriangle(context);
                setEGLConfigChooser(false);
                setRenderer(mRender);
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                break;
            }
            case MainActivity.PRO_ANIMATE_TRIANGLE:
            {
                mRender = new ProSimpleTriangle2(context);
                setEGLConfigChooser(false);
                setRenderer(mRender);
                // default is RENDERMODE_CONTINUOUSLY
                setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
                break;
            }
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
        Log.d("kn:", "onTouchEvent=" + event.getAction() + " ; getPointerCount=" + event.getPointerCount());

        float x = event.getX();
        float y = event.getY();

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            {
                // count the total of touch detected
                // if more than 2, reverse camera
                //Log.d("kn:", "getPointerCount = " + event.getPointerCount());
                if (event.getPointerCount() >= 3) {
                    ((ProAbstractRenderer) mRender).setCameraUpVector();
                }
                requestRender();
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if (factor<5){
                    ++factor;
                }else{
                    factor = 1;
                }
                ((ProAbstractRenderer) mRender).setFrustrum(factor);
                requestRender();

                /*// reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }


                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();*/

            }

        }

        return true;
    }
}
