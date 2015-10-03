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


public class BlogActivity extends Fragment {

    private RequestQueue mQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
         final View view = inflater.inflate(R.layout.blog_tab, container, false);

        final ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();
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

                                Blog.add(new FetchBlog(title.get(i), date.get(i)));

                            }

                            ListView blogListView = (ListView) view.findViewById(R.id.list2);


                            BlogListAdapter adapter = new BlogListAdapter(
                                    getActivity(),
                                    0,
                                    Blog
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
                                    intent.putExtra("url", href.get(position));
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
            return view;
        }
}
