package kenyawakita.sssss;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class NewsActivity extends Fragment {

    PullToRefreshLayout mPulltoRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        final View view = inflater.inflate(R.layout.news_tab, container, false);
        final Activity owner=getActivity();


        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);
        final ListView newsListView = (ListView) view.findViewById(R.id.list3);


        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(newsListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                        // ViewGroup
                .options(Options.create()
                        // these are the new methods :)
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        Log.d("tag", "start refresh");


                        AsyncHttpRequest_Tab3 task = new AsyncHttpRequest_Tab3(owner);
                        task.execute(Constants.ORICON_URL, Constants.NAVER_URL, Constants.NATARY_URL);


                        mPulltoRefresh.setRefreshComplete();
                    }
                })
                .setup(mPulltoRefresh);



        AsyncHttpRequest_Tab3 task = new AsyncHttpRequest_Tab3(owner);
        task.execute(Constants.ORICON_URL, Constants.NAVER_URL, Constants.NATARY_URL);


        return view;

    }
}


