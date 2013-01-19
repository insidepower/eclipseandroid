package com.kendroid.goodsaying;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {
    InputStream instream = null;
    InputStreamReader inputreader = null;
    BufferedReader buffreader = null;
    String str_text = null;
	final static String TAG = "GoodSaying_widget";
    public final static String UPDATE_WIDGET = "update_widget";

    public void onEnabled (Context context) {
        /// when the widget is first added to home screen
		Log.d(TAG, "GoodSaying_widget onEnabled is called");
        instream = context.getResources().openRawResource(R.raw.content);
        inputreader = new InputStreamReader(instream);
        buffreader = new BufferedReader(inputreader);

    }

    public void onReceive(Context context, Intent intent){
        if (intent.getAction().equals(UPDATE_WIDGET))
        {
		    Log.d(TAG, "GoodSaying_widget onReceive is called");
        }
        super.onReceive(context, intent);
    }

    public void onUpdate(
            Context context,
            AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        final int N = appWidgetIds.length;

		Log.d(TAG, "GoodSaying_widget onUpdate is called");
        /// perform for each App Widget
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            /// Create an Intent to launch activity
            Intent intent = new Intent(context, Widget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            //intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context,0,intent,0);

            /// get the layout for the App Widget and attach an on-click
            /// listener to the widget itself
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            try {
                if (buffreader != null)
                {
                    str_text = buffreader.readLine();
                }
                else
                {
                    str_text = "1-"+str_text;
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
            views.setTextViewText(R.id.txt_widget, str_text);
            views.setOnClickPendingIntent(R.id.widgetlayout, pendingIntent);

            /// tell the AppWidgetManager to perform an update on current widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
