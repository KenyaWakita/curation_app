package kenyawakita.musi_king;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.Random;

import kenyawakita.musi_king.R;

public class YoutubeActivity extends Fragment {

    private RequestQueue mQueue;
    boolean next=true;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.youtube_tab, container, false);

        Bundle bundle = getArguments();
        String url = bundle.getString("url");

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

        Constants.youtube = new ArrayList<FetchYoutube>();
        RequestVolley.fetchFromYoutube(getActivity(), view, url, Constants.youtube );
        return view;
    }
}
