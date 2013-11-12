/**
 * Created by insidepower on 11/11/13.
 */
package com.example.mysurfaceview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/*/// extending View
public MyView extends View {
    private Bitmap bmp;

     public MySurfaceView(Context context) {
        super(context);
         bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmp, 10, 10, null);
    }
}*/

public class MySurfaceView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private MySurfaceViewThread myViewThread;
    private int x = 0;

    public MySurfaceView(Context context) {
        super(context);
        myViewThread = new MySurfaceViewThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                myViewThread.setRunning(true);
                myViewThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                myViewThread.setRunning(false);
                while (retry) {
                    try {
                        myViewThread.join();
                        retry = false;
                    } catch (InterruptedException exception) {

                    }
                }
            }
        });

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        if (x < getWidth() - bmp.getWidth()) {
            x++;
        }
        canvas.drawBitmap(bmp, x, 30, null);
    }
}


