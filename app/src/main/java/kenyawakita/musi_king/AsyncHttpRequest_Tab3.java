package kenyawakita.musi_king;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class AsyncHttpRequest_Tab3 extends AsyncTask<String, Void, String> {
    public Activity owner;
    ArrayList<FetchNews> newsList = new ArrayList<FetchNews>();

    public AsyncHttpRequest_Tab3(Activity activity) {
        owner = activity;
    }

    @Override
    protected String doInBackground(String... url) {

            try {
                Constants.newsSites = new JSONObject[url.length];
                for (int i = 0; i < url.length; i++) {
                    HttpGet httpGet = new HttpGet(url[i]);
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    httpGet.setHeader("Connection", "Keep-Alive");
                    HttpResponse response = httpClient.execute(httpGet);

                    int status = response.getStatusLine().getStatusCode();
                    if (status != HttpStatus.SC_OK) {
                        throw new Exception("");
                    } else {
                        Constants.newsSites[i] = new JSONObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //一度通れば良いので，flagをfalseへ
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        final String property = Constants.PROPERTY;
        try {
            for (int i = 0; i < Constants.newsSites.length; i++) {
                JSONArray contents = Constants.newsSites[i].getJSONObject("results").getJSONArray("collection1");
                for (int j = 0; j < contents.length(); j++) {
                    JSONObject roo = contents.getJSONObject(j);

                    try {
                        newsList.add(new FetchNews(roo.getJSONObject(property).getString("text"), roo.getJSONObject(property).getString("href"), roo.getJSONObject("pubDate").getString("text"), Constants.newsSites[i].getString("name")));
                    } catch (Exception e) {
                        newsList.add(new FetchNews(roo.getJSONObject(property).getString("text"), roo.getJSONObject(property).getString("href"), roo.getString("pubDate"), Constants.newsSites[i].getString("name")));
                    }
                    Log.d("tags", newsList.get(i).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //三つの記事を日付でソート
//        Collections.sort(newsList, new DateComparator());
        ListView listView = (ListView) owner.findViewById(R.id.listview); //list3の部分を汎用化したい
        Constants.news_sites = newsList;
        NewsListAdapter adapter = new NewsListAdapter(
                owner,
                0,
                Constants.news_sites
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view, // タップされたView
                    int position,//何番目？
                    long id //View id
            ) {
                Intent intent = new Intent(owner, WebviewActivity.class);
                intent.putExtra("url", Constants.news_sites.get(position).getUrl());
                intent.putExtra("title", "ニュース");
                owner.startActivity(intent);
            }
        });
    }

}
