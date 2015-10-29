package kenyawakita.sssss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
        if (Constants.Newsflag) {

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
            Constants.Newsflag=false;
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayList<String> title = new ArrayList<String>();
        final ArrayList<String> href = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> site = new ArrayList<String>();
        final String property = Constants.PROPERTY;

            try {
                for (int i = 0; i < Constants.newsSites.length; i++) {
                    JSONArray contents = Constants.newsSites[i].getJSONObject("results").getJSONArray("collection1");
                    for (int j = 0; j < contents.length(); j++) {
                        JSONObject roo = contents.getJSONObject(j);

                       href.add(roo.getString("url"));

                        try {
                            newsList.add(new FetchNews(roo.getJSONObject(property).getString("text"), roo.getJSONObject(property).getString("href"), roo.getJSONObject("pubDate").getString("text"), Constants.newsSites[i].getString("name")));
                        } catch (Exception e) {
                            newsList.add(new FetchNews(roo.getJSONObject(property).getString("text"), roo.getJSONObject(property).getString("href"), roo.getString("pubDate"), Constants.newsSites[i].getString("name")));
                        }
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        //三つの記事を日付でソート
        Collections.sort(newsList, new DateComparator());



            ListView listView = (ListView) owner.findViewById(R.id.list3); //list3の部分を汎用化したい
            NewsListAdapter adapter = new NewsListAdapter(
                    owner,
                    0,
                    newsList
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


                    Intent intent = new Intent(owner, ContentActivity.class);
                    intent.putExtra("url", newsList.get(position).getUrl());
                    intent.putExtra("title", "ニュース");
                    owner.startActivity(intent);
                }
            });
    }






}
