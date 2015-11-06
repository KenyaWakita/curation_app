package kenyawakita.sssss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class BlogActivity extends Fragment {

    private RequestQueue mQueue;
    PullToRefreshLayout mPulltoRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
         final View view = inflater.inflate(R.layout.blog_tab, container, false);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);
        final ListView blogListView = (ListView) view.findViewById(R.id.list2);

        //ランダム関数1～5
        //ブログタブを表示したとき，5分の1の確率で広告が表示される
        Random r = new Random();
        int n = r.nextInt(5);

        if(n==1){
            //広告実装
            com.ad_stir.interstitial.AdstirInterstitial interstitial = new com.ad_stir.interstitial.AdstirInterstitial("MEDIA-cef73fb7",1);
            interstitial.load();
            interstitial.showTypeA(getActivity());
        }


        //refreshした時
        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(blogListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                        // ViewGroup
                .options(Options.create()
                        // these are the new methods :)
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        Log.d("tag", "start refresh");


                        mQueue = Volley.newRequestQueue(getActivity());

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

                                                    //今いるアクティビティからContentActivityへのintentを作る
                                                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                                                    intent.putExtra("url", Constants.Blog.get(position).getUrl());
                                                    intent.putExtra("title", "ブログ");
                                                    startActivity(intent);


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


                        mPulltoRefresh.setRefreshComplete();
                    }
                })
                .setup(mPulltoRefresh);




        if (Constants.blogtflag) {
            mQueue = Volley.newRequestQueue(getActivity());

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

                                        //今いるアクティビティからContentActivityへのintentを作る
                                        Intent intent = new Intent(getActivity(), ContentActivity.class);
                                        intent.putExtra("url", Constants.Blog.get(position).getUrl());
                                        intent.putExtra("title", "ブログ");
                                        startActivity(intent);


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
        }

        else{
//            ListView blogListView = (ListView) view.findViewById(R.id.list2);

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

                    //今いるアクティビティからContentActivityへのintentを作る
                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                    intent.putExtra("url", Constants.Blog.get(position).getUrl());
                    intent.putExtra("title", "ブログ");
                    startActivity(intent);


                }
            });

        }

            return view;
        }
}
