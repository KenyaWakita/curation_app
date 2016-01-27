package kenyawakita.musi_king;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class TicketFragment extends Fragment {

    //refreshのlayoutを格納する場所
    PullToRefreshLayout mPulltoRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view, container, false);

        final ListView TicketListView = (ListView) view.findViewById(R.id.listview);
        mPulltoRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to);

        //refreshした時
        ActionBarPullToRefresh.from(getActivity())
                .theseChildrenArePullable(TicketListView) // We need to mark the ListView and it's Empty View as pullable This is because they are not direct children of the
                        // ViewGroup
                .options(Options.create()
                        // these are the new methods :)
                        .build())
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(final View view) {
                        Constants.Ticket.clear();
                        RequestVolley.fetchFromTicket(getActivity(),view,Constants.TICKET_CAMP_URL);
                        mPulltoRefresh.setRefreshComplete();
                    }
                })
                .setup(mPulltoRefresh);


        //もしアプリ起動して初めて開くならば，Jsonをとってくる
        if (Constants.ticketflag) {
            RequestVolley.fetchFromTicket(getActivity(),view,Constants.TICKET_CAMP_URL);
        }

        //タブ表示が2回目以降なら，保存しておいたConstants.Ticketをセット
        else{
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
                    Intent intent = new Intent(getActivity(), WebviewActivity.class);
                    intent.putExtra("url", Constants.Ticket.get(position).getUrl());
                    intent.putExtra("title", "チケット");
                    startActivity(intent);
                }
            });

        }

        return view;
    }
}
