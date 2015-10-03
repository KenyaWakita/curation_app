package kenyawakita.sssss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketListAdapter extends ArrayAdapter<FetchTicket> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<FetchTicket> Ticket = new ArrayList<FetchTicket>();


    // 初期化
    public TicketListAdapter(Context context,
                            int id,
                            ArrayList<FetchTicket> Ticket
    )
    {
        super(context,id, Ticket);

        this.context = context;
        this.Ticket = Ticket;

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
            v = mInflater.inflate(R.layout.ticketlistview, null);
        }

        // この行のためのデータを読み出し
        FetchTicket data_for_this_line = Ticket.get(position);

        // この行のためのテキストをセット
        String Title
                = data_for_this_line.getTitle();
        String Loc
                = data_for_this_line.getLoc();
        String Date
                = data_for_this_line.getDate();
        String Price
                = data_for_this_line.getPrice();

        //textviewにset
        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView loc = (TextView)v.findViewById(R.id.loc);
        loc.setText(Loc);
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(Date);
        TextView price = (TextView)v.findViewById(R.id.price);
        price.setText(Price);


        /* ---------- 行内の画像をロードして描画 ------------ */

        // 行内の画像ビュー
        ImageView imageView = (ImageView)v.findViewById(R.id.icon);

        // 画像のURL
        String img_url = data_for_this_line.getImageurl();


        // 非同期で画像読込を実行
        try{
            Log.d("ListViewTest", position + "の画像読み込みを開始");

            DownloadImagetask task
                    = new DownloadImagetask(imageView, context);
            task.execute(img_url);
        }
        catch(Exception e){
            //
            Log.d("ListViewTest", position + "の画像読み込みに失敗");
        }

        /* ---------- 行の描画が完了 ------------ */

        return v;
    }

}
