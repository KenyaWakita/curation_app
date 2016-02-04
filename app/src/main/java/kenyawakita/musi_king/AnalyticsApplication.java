package kenyawakita.musi_king;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by itosouplus on 11/17/15.
 */
public class AnalyticsApplication extends Application {
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        //トラッキングIDは，アプリごとで違ってくる．毎回新しく作成する
        tracker = analytics.newTracker("UA-70168853-14");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
    }

}
