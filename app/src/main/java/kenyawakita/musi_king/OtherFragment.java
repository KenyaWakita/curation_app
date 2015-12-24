package kenyawakita.musi_king;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class OtherFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.other_tab, container, false);

        final ArrayList<String> Other = new ArrayList<>();

        Random r = new Random();
        int n = r.nextInt(5);
        if(n==1){
            //広告実装
           com.ad_stir.interstitial.AdstirInterstitial interstitial = new com.ad_stir.interstitial.AdstirInterstitial( Constants.MEDIA_ID, Constants.SPOT);
           interstitial.load();
           interstitial.showTypeA(getActivity());
        }


        Other.add("2015セカンドムシカード");
        Other.add("2015セカンおたすけカード");
        Other.add("2015ファーストムシカード");
        Other.add("2015ファースおたすけカード");

        //URLを保存
        final String href[]={
                "http://mushiking.boy.jp/cardlist-2/#M-2",
                "http://mushiking.boy.jp/cardlist-2/2",
                "http://mushiking.boy.jp/cardlist/",
                "http://mushiking.boy.jp/cardlist/2",
        };

        ListView OtherListView = (ListView) view.findViewById(R.id.list5);

                ArrayAdapter<String> adapter =  new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        Other
                );

        OtherListView.setAdapter(adapter);
        OtherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("url", href[position]);
                intent.putExtra("title", Other.get(position));
                startActivity(intent);
            }
        });

        return view;

    }
}
