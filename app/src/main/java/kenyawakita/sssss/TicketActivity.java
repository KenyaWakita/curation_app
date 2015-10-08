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


public class TicketActivity extends Fragment {

    private RequestQueue mQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.ticket_tab, container, false);

        if (Constants.ticketflag) {
            mQueue = Volley.newRequestQueue(getActivity());
            mQueue.add(new JsonObjectRequest(Request.Method.GET, Constants.TICKET_CAMP_URL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray collection = response.getJSONObject("results").getJSONArray("collection1");

                                ArrayList<String> title = new ArrayList<String>();
                                final ArrayList<String> href = new ArrayList<String>();
                                ArrayList<String> loc = new ArrayList<String>();
                                ArrayList<String> date = new ArrayList<String>();
                                ArrayList<String> image = new ArrayList<String>();
                                ArrayList<String> price = new ArrayList<String>();


                                for (int i = 0; i < collection.length(); i++) {

                                    JSONObject roo = collection.getJSONObject(i);

                                    //画像
                                    image.add(roo.getJSONObject("property1").getString("src"));
                                    //タイトル名(ツアー名)
                                    title.add(roo.getJSONObject("property2").getString("text"));
                                    //URL先
                                    href.add(roo.getJSONObject("property2").getString("href"));
                                    //開催地
                                    loc.add(roo.getJSONObject("property3").getString("text"));
                                    //日時
                                    date.add(roo.getJSONObject("property4").getString("text"));
                                    //一枚あたりの値段
                                    price.add(roo.getJSONObject("property5").getString("text"));

                                    Constants.Ticket.add(new FetchTicket(title.get(i), href.get(i) ,loc.get(i), image.get(i), date.get(i), price.get(i)));

                                }


                                ListView TicketListView = (ListView) view.findViewById(R.id.list1);

                                TicketListAdapter adapter = new TicketListAdapter(
                                        getActivity(),
                                        0,
                                        Constants.Ticket
                                );
                                TicketListView.setAdapter(adapter);

                                TicketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(
                                            AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id
                                    ) {


                                        Intent intent = new Intent(getActivity(), ContentActivity.class);
                                        intent.putExtra("url", Constants.Ticket.get(position).getUrl());
                                        intent.putExtra("title", "チケット");
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
                            //?G???[????
                        }
                        //  AsyncHttpRequest_Tab3 task = new AsyncHttpRequest_Tab3(getActivity());
                        //task.execute(url);

                    }));
        }
        else{
            ListView TicketListView = (ListView) view.findViewById(R.id.list1);

            TicketListAdapter adapter = new TicketListAdapter(
                    getActivity(),
                    0,
                    Constants.Ticket
            );
            TicketListView.setAdapter(adapter);

            TicketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(
                        AdapterView<?> parent,
                        View view,
                        int position,
                        long id
                ) {


                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                    intent.putExtra("url", Constants.Ticket.get(position).getUrl());
                    intent.putExtra("title", "チケット");
                    startActivity(intent);

                }
            });

        }

        return view;
    }
}
