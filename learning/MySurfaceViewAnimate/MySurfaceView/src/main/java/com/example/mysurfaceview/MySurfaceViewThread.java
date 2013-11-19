package com.example.mysurfaceview;

import android.graphics.Canvas;

/**
 * Created by insidepower on 11/11/13.
 */
public class MySurfaceViewThread extends Thread {
    private MySurfaceView view;
    private boolean running = false;

    public MySurfaceViewThread(MySurfaceView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    if ( c!=null ) {
                        view.onDraw(c);
                    }
                }
            } finally {
                if ( c!=null ) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
