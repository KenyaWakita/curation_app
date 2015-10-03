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
    public JSONObject[] newsSites;
    ArrayList<FetchNews> newsList = new ArrayList<FetchNews>();

    public AsyncHttpRequest_Tab3(Activity activity) {
        owner = activity;
    }
    @Override
    protected String doInBackground(String... url) {
        try {
            newsSites = new JSONObject[url.length];
            for(int i = 0; i < url.length; i++){
                HttpGet httpGet = new HttpGet(url[i]);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                httpGet.setHeader("Connection", "Keep-Alive");
                HttpResponse response = httpClient.execute(httpGet);

                int status = response.getStatusLine().getStatusCode();
                if (status != HttpStatus.SC_OK) {
                    throw new Exception("");
                } else {
                    newsSites[i] = new JSONObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            for(int i = 0; i < newsSites.length; i++){
                JSONArray contents = newsSites[i].getJSONObject("results").getJSONArray("collection1");
                for(int j = 0; j < contents.length(); j++){
                    JSONObject roo = contents.getJSONObject(j);


                    Log.d("log:", "jfsljf");

                    href.add(roo.getString("url"));

                    try{
                        newsList.add(new FetchNews(roo.getJSONObject(property).getString("text"), roo.getString("url"), roo.getString("pubDate"),newsSites[i].getString("name")));
                    } catch (Exception e){
                        newsList.add(new FetchNews(roo.getString(property), roo.getString("url"), roo.getString("pubDate"),newsSites[i].getString("name")));
                    }

                }
            }

            //三つの記事を日付でソート
            Collections.sort(newsList, new DateComparator());
            ListView listView = (ListView) owner.findViewById(R.id.list3); //list2の部分を汎用化したい
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
                    intent.putExtra("url", href.get(position));
                    intent.putExtra("title", "ニュース");
                    owner.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
//         catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
