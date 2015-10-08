package kenyawakita.sssss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsListAdapter extends ArrayAdapter<FetchNews> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<FetchNews> News = new ArrayList<FetchNews>();


    // 初期化
    public NewsListAdapter(Context context,
                           int id,
                           ArrayList<FetchNews> News
    )
    {
        super(context,id, News);

        this.context = context;
        this.News = News;

        // リストの動的な描写のためにインフレ―多を生成
        this.mInflater =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );

        Log.d("ListViewTest", "アダプタ生成完了");
    }

    // 1行を描画するたびに呼ばれるメソッド
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        Log.d("ListViewTest", position + "の getView() が開始");


        /* ---------- 行を初期化 ------------ */

        // 行を表すビュー
        View v = convertView;
        if(v == null){
            Log.d("ListViewTest", position + "のvを新規生成");
            v = mInflater.inflate(R.layout.newslistview, null);
        }

        // この行のためのデータを読み出し
        FetchNews data_for_this_line = News.get(position);

        // この行のためのテキストをセット
        String Title
                = data_for_this_line.getTitle();
        String Date
                = null;
        try {
            Date = new SimpleDateFormat("yyyy-MM-dd").format(data_for_this_line.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String Site
                = data_for_this_line.getSite();


        //textviewにset
        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(Date);
        TextView site = (TextView)v.findViewById(R.id.site);
        site.setText(Site);

        return v;
    }


}

