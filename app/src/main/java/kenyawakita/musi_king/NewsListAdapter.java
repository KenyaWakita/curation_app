package kenyawakita.musi_king;

import android.content.Context;
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
    private ArrayList<FetchNews> News;

    public NewsListAdapter(Context context, int id, ArrayList<FetchNews> News) {
        super(context,id, News);

        this.context = context;
        this.News = News;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 1行を描画するたびに呼ばれるメソッド
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = mInflater.inflate(R.layout.newslistview, null);
        }
        FetchNews data_for_this_line = News.get(position);
        String Title = data_for_this_line.getTitle();
        String Date = null;
        try {
            Date = new SimpleDateFormat("yyyy-MM-dd").format(data_for_this_line.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            Date = data_for_this_line.getDateString();
        }
        String Site = data_for_this_line.getSite();

        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView date = (TextView)v.findViewById(R.id.loc);
        date.setText(Date);
        TextView site = (TextView)v.findViewById(R.id.site);
        site.setText(Site);

        return v;
    }
}
