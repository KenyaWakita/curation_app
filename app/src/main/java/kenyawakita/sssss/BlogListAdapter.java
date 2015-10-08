package kenyawakita.sssss;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BlogListAdapter extends ArrayAdapter<FetchBlog> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();


    // 初期化
    public BlogListAdapter(Context context,
                             int id,
                             ArrayList<FetchBlog> Blog
    )
    {
        super(context,id, Blog);

        this.context = context;
        this.Blog = Blog;

        // リストの動的な描画のためにインフレータを生成
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
            v = mInflater.inflate(R.layout.bloglistview, null);
        }

        // この行のためのデータを読み出し
        FetchBlog data_for_this_line = Blog.get(position);

        // この行のためのテキストをセット
        String Title
                = data_for_this_line.getTitle();
        String Date
                = data_for_this_line.getDate();

        //textviewにset
        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(Date);


        // ImageViewにset
        ImageView imageView = (ImageView)v.findViewById(R.id.icon);

        Bitmap result = BitmapFactory.decodeResource(v.getResources(),R.drawable.images11);
                imageView.setImageBitmap(result);

        return v;
    }


}
