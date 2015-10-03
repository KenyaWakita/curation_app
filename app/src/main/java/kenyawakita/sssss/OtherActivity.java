package kenyawakita.sssss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OtherActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.other_tab, container, false);

        ArrayList<String> Other = new ArrayList<>();

        Other.add( "公式Webサイト");
        Other.add("Facebook");
        Other.add("グッズ");
        Other.add("歌詞");
        Other.add("公式Twitter");
        Other.add("NAOTO(Twitter)");
        Other.add("ELLY(Twitter)");
        Other.add("登坂広臣(Twitter)");

        //URLを保存
        final String href[]={
                Constants.OFFICIAL_WEBSITE,
                Constants.FB,
                Constants.GOODS,
                Constants.KASHI,
                Constants.OFFICIAL_TWITTER,
                Constants.NAOTO,
                Constants.ELLY,
                Constants.TOSAKA,
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
                intent.putExtra("title", "その他");
                startActivity(intent);


//                Uri uri = Uri.parse(href[position]);
//                Intent i = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(i);
            }
        });

        return view;

    }
}
