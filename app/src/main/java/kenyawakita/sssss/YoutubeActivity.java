package kenyawakita.sssss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

public class YoutubeActivity extends Fragment {

    private RequestQueue mQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.youtube_tab, container, false);

        if(Constants.youtubeflag) {

            mQueue = Volley.newRequestQueue(getActivity());
            String url = "https://www.googleapis.com/youtube/v3/search?key=" +
                    Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD +
                    "&part=snippet&maxResults=20&order=viewCount";//YoutubeAPIとJsonのURL

            mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray items = response.getJSONArray("items");
                                ArrayList<String> title = new ArrayList<String>();
                                final ArrayList<String> href = new ArrayList<String>();
                                ArrayList<String> date = new ArrayList<String>();
                                ArrayList<String> image = new ArrayList<String>();

                                for (int i = 0; i < items.length(); i++) {//youtube#videoがある個数を計算(countに代入).配列membersにいくつの要素を確保するか知るために作った
                                    JSONObject roo = items.getJSONObject(i);
                                    if (roo.getJSONObject("id").getString("kind").equals("youtube#video")) {//もし．idのkindが＝youtube#videoならばcountにプラス1をする
                                        title.add(roo.getJSONObject("snippet").getString("title"));
                                        href.add("https://www.youtube.com/watch?v=" + roo.getJSONObject("id").getString("videoId"));
                                        date.add(roo.getJSONObject("snippet").getString("publishedAt"));
                                        image.add(roo.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));

                                        Constants.Youtube.add(new FetchYoutube(title.get(i), href.get(i), image.get(i), date.get(i)));
                                    }
                                }

                                ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);

                                ImageListAdapter adapter = new ImageListAdapter(
                                        getActivity(),
                                        0,
                                        Constants.Youtube
                                );
                                YoutubeListView.setAdapter(adapter);

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
        }

        else{
            ListView YoutubeListView = (ListView) view.findViewById(R.id.list4);

            ImageListAdapter adapter = new ImageListAdapter(
                    getActivity(),
                    0,
                    Constants.Youtube
            );
            YoutubeListView.setAdapter(adapter);

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
