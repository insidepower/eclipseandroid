package kn.app.goodsentence;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class GoodSentenceWidget extends AppWidgetProvider {

    public void onEnabled(Context context){
    }

    public void onUpdate(
                    Context context,
                    AppWidgetManager appWidgetMgr,
                    int[] appWidgetIds) {
        final int N = appWidgetIds.length;
		// Perform this loop procedure for each App Widget that belongs to this provider
		// as we can create a few widget, this will update all?
		for (int appWidgetId : appWidgetIds) {
			//setAlarm(context, appWidgetId, UPDATE_RATE);
            Intent intent = new Intent(context, GoodSentenceService.class);
            intent.setAction(GoodSentenceService.UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            context.startService(intent);
		}
        //context.startService(new Intent(context, GoodSentenceService.class));
		super.onUpdate(context, appWidgetMgr, appWidgetIds);
    }

}
