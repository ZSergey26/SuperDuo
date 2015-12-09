package barqsoft.footballscores.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Created by ZSergei on 08.12.2015.
 */
public class WidgetService extends IntentService{

    public WidgetService() {
        super("WidgetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                WidgetProvider.class));

        // Perform this loop procedure for each Today widget
        for (int appWidgetId : appWidgetIds) {
            int layoutId = R.layout.widget;
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);

            // Add the data to the RemoteViews
            views.setImageViewResource(R.id.widget_home_crest, R.drawable.manchester_united);
            views.setTextViewText(R.id.widget_home_name, "Manchester United");

            views.setImageViewResource(R.id.widget_away_crest, R.drawable.manchester_city);
            views.setTextViewText(R.id.widget_away_name, "Manchester City");

            views.setTextViewText(R.id.widget_score_textview, "2:4");

            views.setTextViewText(R.id.widget_data_textview, "09.12.2015");

            // Create an Intent to launch MainActivity
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
