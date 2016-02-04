package kenyawakita.musi_king;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ad_stir.interstitial.AdstirInterstitial;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.Random;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class BoardTypeFragment extends Fragment {
    private RequestQueue mQueue;
    PullToRefreshLayout mPulltoRefresh;
    private ListView BoardTypeListView;
    String json_url;
    ArrayList<String> BoardType_title;
    ArrayList<String> BoardType_href;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view, container, false);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);
        BoardTypeListView = (ListView) view.findViewById(R.id.listview);

        Bundle bundle = getArguments();
        json_url = bundle.getString("json_url");
        String theme = bundle.getString("theme");


        //ランダム関数1～5
        //ブログタブを表示したとき，5分の1の確率で広告が表示される
        Random r = new Random();
        int n = r.nextInt(5);

        if(n==1){
            //広告実装
            AdstirInterstitial interstitial = new AdstirInterstitial(Constants.MEDIA_ID, Constants.SPOT);
            interstitial.load();
            interstitial.showTypeA(getActivity());
        }
        //refreshした時
        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(BoardTypeListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                .options(Options.create()
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        RequestVolley.fetchFromBoardType(getActivity(), view, json_url);
                        mPulltoRefresh.setRefreshComplete();
                    }
                }).setup(mPulltoRefresh);
//
//        switch (theme){
//            case "official":
//                RequestVolley.fetchFromYoutube(getActivity(), view, json_url, Constants.youtube_official);
//                break;
//            case "live":
//                RequestVolley.fetchFromYoutube(getActivity(), view, json_url, Constants.youtube_live);
//                break;
//            case "sing":
//                RequestVolley.fetchFromYoutube(getActivity(), view, json_url, Constants.youtube_sing);
//                break;
//            case "game":
//                RequestVolley.fetchFromYoutube(getActivity(), view, json_url, Constants.youtube_game);
//                break;
//            default:
//                RequestVolley.fetchFromYoutube(getActivity(), view, json_url, Constants.youtube_game);
//                break;
//        }

        RequestVolley.fetchFromBoardType(getActivity(), view, json_url);

        return view;
    }



}
