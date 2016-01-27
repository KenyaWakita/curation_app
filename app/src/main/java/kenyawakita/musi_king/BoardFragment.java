package kenyawakita.musi_king;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Random;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class BoardFragment extends Fragment {
    //refreshのlayoutを格納する場所
    PullToRefreshLayout mPulltoRefresh;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view, container, false);

        //ArrayList<FetchYoutube> list = (ArrayList<FetchYoutube>)bundle.getSerializable("list");

        //ランダム関数1～5
        //youtubeタブを表示したとき，5分の1の確率で広告が表示される
        Random r = new Random();
        int n = r.nextInt(5);

        if(n==1){
            //広告実装
            com.ad_stir.interstitial.AdstirInterstitial interstitial = new com.ad_stir.interstitial.AdstirInterstitial(Constants.MEDIA_ID, Constants.SPOT);
           interstitial.load();
           interstitial.showTypeA(getActivity());
        }


        final ListView BordListView = (ListView) view.findViewById(R.id.listview);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);

        //refreshした時
        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(BordListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                        // ViewGroup
                .options(Options.create()
                        // these are the new methods :)
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        Constants.Bord.clear();
                        RequestVolley.fetchFromBoard(getActivity(),view,Constants.BORD_URL);
                        mPulltoRefresh.setRefreshComplete();
                    }
                })
                .setup(mPulltoRefresh);

        //もしアプリ起動して初めて開くならば，Jsonをとってくる
        if (Constants.bordflag) {
            RequestVolley.fetchFromBoard(getActivity(), view, Constants.BORD_URL);
            Constants.bordflag=false;
        }

        //タブ表示が2回目以降なら，保存しておいたConstants.BORDをセット
        else{
            NewsListAdapter adapter = new NewsListAdapter(
                    getActivity(),
                    0,
                    Constants.Bord
            );

            BordListView.setAdapter(adapter);
            BordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> parent,
                        View view,
                        int position,
                        long id
                ) {
                    Intent intent = new Intent(getActivity(), WebviewActivity.class);
                    intent.putExtra("url", Constants.Bord.get(position).getUrl());
                    intent.putExtra("title", "掲示板");
                    startActivity(intent);
                }
            });
        }

        return view;
    }

}
