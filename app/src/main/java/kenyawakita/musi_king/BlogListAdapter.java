package kenyawakita.musi_king;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kenyawakita.musi_king.R;

public class BlogListAdapter extends ArrayAdapter<FetchBlog> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();

    public BlogListAdapter(Context context, int id, ArrayList<FetchBlog> Blog) {
        super(context,id, Blog);
        this.context = context;
        this.Blog = Blog;

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = mInflater.inflate(R.layout.bloglistview, null);
        }
        FetchBlog data_for_this_line = Blog.get(position);
        String title = data_for_this_line.getTitle();
        String date = data_for_this_line.getDate();

        TextView titleView = (TextView)v.findViewById(R.id.title);
        TextView dateView = (TextView)v.findViewById(R.id.loc);
        titleView.setText(title);
        dateView.setText(date);

        ImageView imageView = (ImageView)v.findViewById(R.id.icon);
        Bitmap result = BitmapFactory.decodeResource(v.getResources(),R.drawable.images11);
        imageView.setImageBitmap(result);

        return v;
    }
}
