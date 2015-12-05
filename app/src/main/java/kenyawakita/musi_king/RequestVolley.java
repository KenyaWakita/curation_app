package kenyawakita.musi_king;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AbsListView;
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


public class RequestVolley {
    public static void fetchFromBoard(final Activity activity, final View view, final String url) {
        RequestQueue mQueue = Volley.newRequestQueue(activity);
        mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray collection = response.getJSONObject("results").getJSONArray("collection1");
                            ArrayList<String> title = new ArrayList<String>();
                            final ArrayList<String> href = new ArrayList<String>();
                            ArrayList<String> date = new ArrayList<String>();
                            ArrayList<String> post = new ArrayList<String>();

                            for (int i = 0; i < collection.length(); i++) {
                                JSONObject roo = collection.getJSONObject(i);
                                title.add(roo.getJSONObject("title").getString("text"));
                                date.add(roo.getJSONObject("last").getString("text"));
                                href.add(roo.getJSONObject("title").getString("href"));
                                post.add(roo.getString("post"));

                                Constants.news.add(new FetchNews(title.get(i), href.get(i), date.get(i), "投稿数 : " + post.get(i)));
                            }

                            ListView blogListView = (ListView) view.findViewById(R.id.list3);
                            NewsListAdapter adapter = new NewsListAdapter(
                                    activity,
                                    0,
                                    Constants.news
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
                                    Intent intent = new Intent(activity, ContentActivity.class);
                                    intent.putExtra("url", Constants.news.get(position).getUrl());
                                    intent.putExtra("title", "掲示板");
                                    activity.startActivity(intent);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }));
    }

    public static void fetchFromBlog(final Activity activity, final View view) {
            RequestQueue mQueue = Volley.newRequestQueue(activity);
            mQueue.add(new JsonObjectRequest(Request.Method.GET, Constants.AMEBA_URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray collection = response.getJSONObject("results").getJSONArray("collection1");
                                ArrayList<String> title = new ArrayList<String>();
                                final ArrayList<String> href = new ArrayList<String>();
                                ArrayList<String> date = new ArrayList<String>();

                                for (int i = 0; i < collection.length(); i++) {
                                    JSONObject roo = collection.getJSONObject(i);
                                    title.add(roo.getJSONObject("property1").getString("text"));
                                    date.add(roo.getString("property2"));
                                    href.add(roo.getJSONObject("property1").getString("href"));

                                    Constants.Blog.add(new FetchBlog(title.get(i), href.get(i), date.get(i)));
                                }

                                ListView blogListView = (ListView) view.findViewById(R.id.list2);
                                BlogListAdapter adapter = new BlogListAdapter(
                                        activity,
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
                                        Intent intent = new Intent(activity, ContentActivity.class);
                                        intent.putExtra("url", Constants.Blog.get(position).getUrl());
                                        intent.putExtra("title", "ブログ");
                                        activity.startActivity(intent);
                                    }
                                });



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }));
    }

    public static void fetchFromYoutube(final Activity activity, final View view, final String URL, final ArrayList<FetchYoutube> youtube) {
        final RequestQueue mQueue = Volley.newRequestQueue(activity);
        mQueue.add(new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            ArrayList<String> title = new ArrayList<String>();
                            final ArrayList<String> href = new ArrayList<String>();
                            ArrayList<String> date = new ArrayList<String>();
                            ArrayList<String> image = new ArrayList<String>();
                            int count = 0;
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject roo = items.getJSONObject(i);
                                if (roo.getJSONObject("id").getString("kind").equals("youtube#video")) {//もし．idのkindが＝youtube#videoならば
                                    title.add(roo.getJSONObject("snippet").getString("title"));
                                    href.add("https://www.youtube.com/watch?v=" + roo.getJSONObject("id").getString("videoId"));
                                    date.add(roo.getJSONObject("snippet").getString("publishedAt"));
                                    image.add(roo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));

                                    youtube.add(new FetchYoutube(title.get(count), href.get(count), image.get(count), date.get(count)));
                                    count++;
                                }
                            }
                            Constants.NEXT_TOKEN_URL = response.getString("nextPageToken");
                            readMoreVideo(activity, view, mQueue, URL, youtube);

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
    }
    public static void readMoreVideo(final Activity activity, View view, final RequestQueue mQueue, final String URL, final ArrayList<FetchYoutube> youtube) {
        ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);

        ImageListAdapter adapter = new ImageListAdapter(
                activity,
                0,
                youtube
        );
        YoutubeListView.setAdapter(adapter);

        //最も下まで行った時を検知するコード
        YoutubeListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(final AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, final int totalItemCount) {
                //totalItemCount：登録しているリストの総数
                //firstVisibleItem：今見えてる一番上のリストのid
                //visibleItemCount：見えているリストの数
                if (totalItemCount == firstVisibleItem + visibleItemCount) {
                    if (Constants.RENZOKUCANCEL) {
                            mQueue.add(new JsonObjectRequest(Request.Method.GET, URL + "&pageToken=" + Constants.NEXT_TOKEN_URL, null,
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

                                                        youtube.add(new FetchYoutube(title.get(count), href.get(count), image.get(count), date.get(count)));
                                                        count++;
                                                    }
                                                }

                                                //nextPageTokenの値をConstants.NEXT_TOKEN_URLに格納
                                                Constants.NEXT_TOKEN_URL = response.getString("nextPageToken");
                                                ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);
                                                ImageListAdapter adapter = new ImageListAdapter(
                                                        activity,
                                                        0,
                                                        youtube
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
                Uri uri = Uri.parse(Constants.youtube.get(position).getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(i);
            }
        });
    }
}
