package barqsoft.footballscores.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Created by ZSergei on 08.12.2015.
 */
public class TodayGamesWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Perform this loop procedure for each Today widget
        for (int appWidgetId : appWidgetIds) {
            int layoutId = R.layout.today_games_widget;
            RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);
            views.setImageViewResource(R.id.imageView, R.drawable.west_ham);


            // Create an Intent to launch MainActivity
            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.imageView, pendingIntent);



            // Set data
            //setRemoteAdapter(context, views);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private void setRemoteAdapter(Context context, final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, TodayGamesWidgetService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
//                new ComponentName(context, getClass()));
//        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
    }


}
