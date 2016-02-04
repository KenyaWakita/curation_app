package kenyawakita.musi_king;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import java.util.Random;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class BlogFragment extends Fragment {

    private RequestQueue mQueue;
    PullToRefreshLayout mPulltoRefresh;
    private ListView blogListView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
         final View view = inflater.inflate(R.layout.fragment_view, container, false);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);
        blogListView = (ListView) view.findViewById(R.id.listview);
        //ランダム関数1～5
        //ブログタブを表示したとき，5分の1の確率で広告が表示される
        Random r = new Random();
        int n = r.nextInt(5);

        if(n==1){
            //広告実装
            com.ad_stir.interstitial.AdstirInterstitial interstitial = new com.ad_stir.interstitial.AdstirInterstitial(Constants.MEDIA_ID, Constants.SPOT);
            interstitial.load();
            interstitial.showTypeA(getActivity());
        }
        //refreshした時
        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(blogListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                .options(Options.create()
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        RequestVolley.fetchFromBlog(getActivity(), view);
                        mPulltoRefresh.setRefreshComplete();
                    }
                }).setup(mPulltoRefresh);
            if (Constants.blogtflag) {
                RequestVolley.fetchFromBlog(getActivity(), view);
            } else {
                setAdapter();
            }
            return view;
        }

    public void setAdapter() {
        BlogListAdapter adapter = new BlogListAdapter(
                getActivity(),
                0,
                Constants.Blog
        );

        blogListView.setAdapter(adapter);
        blogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("url", Constants.Blog.get(position).getUrl());
                intent.putExtra("title", "ブログ");
                startActivity(intent);
            }
        });
    }
}