package barqsoft.footballscores.widget;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;

/**
 * Created by ZSergei on 08.12.2015.
 */
public class TodayGamesWidgetService extends RemoteViewsService {

    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.SCORES_TABLE + "." +
            DatabaseContract.scores_table._ID,
            DatabaseContract.scores_table.DATE_COL,
            DatabaseContract.scores_table.TIME_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.LEAGUE_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.MATCH_ID,
            DatabaseContract.scores_table.MATCH_DAY
    };

    public static final int COL_HOME = 3;
    public static final int COL_AWAY = 4;
    public static final int COL_HOME_GOALS = 6;
    public static final int COL_AWAY_GOALS = 7;
    public static final int COL_DATE = 1;
    public static final int COL_LEAGUE = 5;
    public static final int COL_MATCHDAY = 9;
    public static final int COL_ID = 8;
    public static final int COL_MATCHTIME = 2;
    public double detail_match_id = 0;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            private Cursor data = null;

            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }

                final long identityToken = Binder.clearCallingIdentity();


                Uri scoresUri = DatabaseContract.scores_table.buildScoreWithDate();
                data = getContentResolver().query(scoresUri,
                        SCORES_COLUMNS,
                        null,
                        null,
                        DatabaseContract.scores_table.DATE_COL + " ASC");

                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }

                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.scores_list_item);





//                date.setText(cursor.getString(COL_MATCHTIME));
//                date.setContentDescription("Match time " + cursor.getString(COL_MATCHTIME));
//
//                score.setText(Utilies.getScores(cursor.getInt(COL_HOME_GOALS), cursor.getInt(COL_AWAY_GOALS)));
//                score.setContentDescription("Score " + Utilies.getScores(cursor.getInt(COL_HOME_GOALS), cursor.getInt(COL_AWAY_GOALS)));
//
//                match_id = cursor.getDouble(COL_ID);
//                home_crest.setImageResource(Utilies.getTeamCrestByTeamName(
//                        cursor.getString(COL_HOME)));
//                away_crest.setImageResource(Utilies.getTeamCrestByTeamName(
//                        cursor.getString(COL_AWAY)
//                ));
                Log.e("SLSBA SUKA SLABA  " + data.getString(COL_HOME), "KAKEL");
                views.setTextViewText(R.id.home_name, data.getString(COL_HOME));
                views.setTextViewText(R.id.away_name, data.getString(COL_AWAY));
                final Intent fillInIntent = new Intent();
                //iews.setOnClickFillInIntent(R.id.wif, fillInIntent);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.scores_list_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int i) {
                if (data.moveToPosition(i))
                    return data.getLong(0);
                return i;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
