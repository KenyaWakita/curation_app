package kenyawakita.sssss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class YoutubeActivity extends Fragment {

    private RequestQueue mQueue;
    boolean next=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.youtube_tab, container, false);

        //ランダム関数1～5
        //youtubeタブを表示したとき，5分の1の確率で広告が表示される
        Random r = new Random();
        int n = r.nextInt(5);

        if(n==1){
            //広告実装
            com.ad_stir.interstitial.AdstirInterstitial interstitial = new com.ad_stir.interstitial.AdstirInterstitial("MEDIA-cef73fb7",1);
            interstitial.load();
            interstitial.showTypeA(getActivity());
        }


        if(Constants.youtubeflag) {

            mQueue = Volley.newRequestQueue(getActivity());


            mQueue.add(new JsonObjectRequest(Request.Method.GET, Constants.YOUTUBE_URL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONArray items = response.getJSONArray("items");
                                ArrayList<String> title = new ArrayList<String>();
                                final ArrayList<String> href = new ArrayList<String>();
                                ArrayList<String> date = new ArrayList<String>();
                                ArrayList<String> image = new ArrayList<String>();

                                //youtube#videoがあるところだけを識別するため
                                int count = 0;

                                //itemsの数だけまわす
                                for (int i = 0; i < items.length(); i++) {
                                    JSONObject roo = items.getJSONObject(i);
                                    if (roo.getJSONObject("id").getString("kind").equals("youtube#video")) {//もし．idのkindが＝youtube#videoならば

                                        title.add(roo.getJSONObject("snippet").getString("title"));
                                        href.add("https://www.youtube.com/watch?v=" + roo.getJSONObject("id").getString("videoId"));
                                        date.add(roo.getJSONObject("snippet").getString("publishedAt"));
                                        image.add(roo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));

                                        Constants.Youtube.add(new FetchYoutube(title.get(count), href.get(count), image.get(count), date.get(count)));
                                        count++;
                                    }
                                }



                                //nextPageTokenの値をConstants.NEXT_TOKEN_URLに格納
                                Constants.NEXT_TOKEN_URL = response.getString("nextPageToken");


                                ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);

                                ImageListAdapter adapter = new ImageListAdapter(
                                        getActivity(),
                                        0,
                                        Constants.Youtube
                                );
                                YoutubeListView.setAdapter(adapter);


                                //最も下まで行った時を検知するコード
                                YoutubeListView.setOnScrollListener(new OnScrollListener() {
                                    @Override
                                    public void onScroll(final AbsListView view, int firstVisibleItem,
                                                         int visibleItemCount, final int totalItemCount) {
                                        //totalItemCount：登録しているリストの総数
                                        //firstVisibleItem：今見えてる一番上のリストのid
                                        //visibleItemCount：見えているリストの数
                                        if (totalItemCount == firstVisibleItem + visibleItemCount) {

                                            if (Constants.RENZOKUCANCEL) {

                                                    mQueue.add(new JsonObjectRequest(Request.Method.GET, Constants.YOUTUBE_URL + "&pageToken=" + Constants.NEXT_TOKEN_URL, null,
                                                            new Response.Listener<JSONObject>() {

                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        JSONArray items = response.getJSONArray("items");
                                                                        ArrayList<String> title = new ArrayList<String>();
                                                                        final ArrayList<String> href = new ArrayList<String>();
                                                                        ArrayList<String> date = new ArrayList<String>();
                                                                        ArrayList<String> image = new ArrayList<String>();

                                                                        //youtube#videoがあるところだけを識別するため
                                                                        int count = 0;

                                                                        for (int i = 0; i < items.length(); i++) {//youtube#videoがある個数を計算(countに代入).配列membersにいくつの要素を確保するか知るために作った
                                                                            JSONObject roo = items.getJSONObject(i);
                                                                            if (roo.getJSONObject("id").getString("kind").equals("youtube#video")) {//もし．idのkindが＝youtube#videoならばcountにプラス1をする
                                                                                title.add(roo.getJSONObject("snippet").getString("title"));
                                                                                href.add("https://www.youtube.com/watch?v=" + roo.getJSONObject("id").getString("videoId"));
                                                                                date.add(roo.getJSONObject("snippet").getString("publishedAt"));
                                                                                image.add(roo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));

                                                                                Constants.Youtube.add(new FetchYoutube(title.get(count), href.get(count), image.get(count), date.get(count)));
                                                                                count++;
                                                                            }
                                                                        }

                                                                        //nextPageTokenの値をConstants.NEXT_TOKEN_URLに格納
                                                                        Constants.NEXT_TOKEN_URL = response.getString("nextPageToken");
                                                                        ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);
                                                                        ImageListAdapter adapter = new ImageListAdapter(
                                                                                getActivity(),
                                                                                0,
                                                                                Constants.Youtube
                                                                        );

                                                                        YoutubeListView.setAdapter(adapter);

                                                                        //最も下のリストに到達したら，最も下だったリスト(totalItemCount-1)を画面の上に表示して，新しいリストを追加
                                                                        YoutubeListView.setSelection(totalItemCount-1);


                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {

                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                }
                                                            }));

                                                Constants.RENZOKUCANCEL = false;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onScrollStateChanged(AbsListView arg0, int arg1) {
                                        //リストを追加するif文に入れるようにする
                                        Constants.RENZOKUCANCEL=true;
                                    }

                                });

                                YoutubeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(
                                            AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id //View id
                                    ) {
                                        Uri uri = Uri.parse(Constants.Youtube.get(position).getUrl());
                                        Intent i = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(i);
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }));

            //youtubeタブ一回目の表示だけ，if文に入ればよいので，if文に入ってこれないようにする
            Constants.youtubeflag=false;

        }

        //Youtubeタブ表示二回目以降
        else{
            ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);

            ImageListAdapter adapter = new ImageListAdapter(
                    getActivity(),
                    0,
                    Constants.Youtube
            );
            YoutubeListView.setAdapter(adapter);



            //最も下まで行った事を検知するコード
            YoutubeListView.setOnScrollListener(new OnScrollListener() {
                @Override
                public void onScroll(final AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, final int totalItemCount) {
                    //totalItemCount：登録しているリストの総数
                    //firstVisibleItem：今見えてる一番上のリストのid
                    //visibleItemCount：見えているリストの数
                    //下の条件が成り立ったとき，一番下のリストを表示したと検知する
                    if (totalItemCount == firstVisibleItem + visibleItemCount) {

                        if (Constants.RENZOKUCANCEL) {

                            mQueue.add(new JsonObjectRequest(Request.Method.GET, Constants.YOUTUBE_URL + "&pageToken=" + Constants.NEXT_TOKEN_URL, null,
                                    new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                JSONArray items = response.getJSONArray("items");
                                                ArrayList<String> title = new ArrayList<String>();
                                                final ArrayList<String> href = new ArrayList<String>();
                                                ArrayList<String> date = new ArrayList<String>();
                                                ArrayList<String> image = new ArrayList<String>();

                                                //youtube#videoがあるところだけを識別するため
                                                int count = 0;

                                                for (int i = 0; i < items.length(); i++) {//youtube#videoがある個数を計算(countに代入).配列membersにいくつの要素を確保するか知るために作った
                                                    JSONObject roo = items.getJSONObject(i);
                                                    if (roo.getJSONObject("id").getString("kind").equals("youtube#video")) {//もし．idのkindが＝youtube#videoならばcountにプラス1をする
                                                        title.add(roo.getJSONObject("snippet").getString("title"));
                                                        href.add("https://www.youtube.com/watch?v=" + roo.getJSONObject("id").getString("videoId"));
                                                        date.add(roo.getJSONObject("snippet").getString("publishedAt"));
                                                        image.add(roo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));

                                                        Constants.Youtube.add(new FetchYoutube(title.get(count), href.get(count), image.get(count), date.get(count)));
                                                        count++;
                                                    }
                                                }

                                                //nextPageTokenの値をConstants.NEXT_TOKEN_URLに格納
                                                Constants.NEXT_TOKEN_URL = response.getString("nextPageToken");
                                                ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);
                                                ImageListAdapter adapter = new ImageListAdapter(
                                                        getActivity(),
                                                        0,
                                                        Constants.Youtube
                                                );

                                                YoutubeListView.setAdapter(adapter);

                                                //最も下のリストに到達したら，最も下だったリスト(totalItemCount-1)を画面の上に表示して，新しいリストを追加
                                                YoutubeListView.setSelection(totalItemCount-1);


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    }));

                            Constants.RENZOKUCANCEL = false;
                        }
                    }
                }

                @Override
                public void onScrollStateChanged(AbsListView arg0, int arg1) {
                    //リストを追加するif文に入れるようにする
                    Constants.RENZOKUCANCEL=true;
                }

            });



            YoutubeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> parent,
                        View view,
                        int position,
                        long id //View id
                ) {
                    Uri uri = Uri.parse(Constants.Youtube.get(position).getUrl());
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);
                }
            });

        }

        return view;
    }
}
