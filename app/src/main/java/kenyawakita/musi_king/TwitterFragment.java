package kenyawakita.musi_king;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.concurrent.Callable;

import io.fabric.sdk.android.Fabric;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class TwitterFragment extends ListFragment {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Re3eDQnGwBl2Y5RGv5YDdxgZS";
    private static final String TWITTER_SECRET = "W9F68RrSDBA59v6yK0QYFeMpG2vjl4He8Fi756TXykxjOF79cF";
    String twitter_account;
    PullToRefreshLayout mPulltoRefresh;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.twitter_tab, container, false);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pullto_twitter);

        Bundle bundle = getArguments();
        twitter_account =bundle.getString("twitter_account");

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(getActivity(), new Twitter(authConfig));

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(twitter_account)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);


        //リロードをしようとしたが，できなかった
//        ActionBarPullToRefresh.from(getActivity())
//                .theseChildrenArePullable(view)
//                .options(Options.create()
//                        .build())
//                .listener(new OnRefreshListener() {
//                    @Override
//                    public void onRefreshStarted(final View view) {
//
//                        final UserTimeline userTimeline = new UserTimeline.Builder()
//                                .screenName(twitter_account)
//                                .build();
//                        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
//                                .setTimeline(userTimeline)
//                                .build();
//                        setListAdapter(adapter);
//
//
//                        mPulltoRefresh.setRefreshComplete();
//                    }
//                }).setup(mPulltoRefresh);


        return view;
    }

}
