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

public class BoardTypeListAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<String> BoardType = new ArrayList<String>();

    public BoardTypeListAdapter(Context context, int id, ArrayList<String> BoardType) {
        super(context,id, BoardType);
        this.context = context;
        this.BoardType = BoardType;

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = mInflater.inflate(R.layout.boardtypelistview, null);
        }
        String data_for_this_line = BoardType.get(position);
//        String title = data_for_this_line.getTitle();

        TextView titleView = (TextView)v.findViewById(R.id.title);
        titleView.setText(data_for_this_line);

        return v;
    }
}
