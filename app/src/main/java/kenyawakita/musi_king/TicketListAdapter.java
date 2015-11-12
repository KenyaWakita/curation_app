package kenyawakita.musi_king;

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


    // ����
    public TicketListAdapter(Context context,
                            int id,
                            ArrayList<FetchTicket> Ticket
    )
    {
        super(context,id, Ticket);

        this.context = context;
        this.Ticket = Ticket;

        // ���X�g�̓��I�ȕ`��̂��߂ɃC���t���[�^�𐶐�
        this.mInflater =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );

        Log.d("ListViewTest", "�A�_�v�^��������");
    }

    // 1�s��`�悷�邽�тɌĂ΂�郁�\�b�h
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        Log.d("ListViewTest", position + "�� getView() ���J�n");


        /* ---------- �s������ ------------ */

        // �s��\���r���[
        View v = convertView;
        if(v == null){
            Log.d("ListViewTest", position + "��v��V�K����");
            v = mInflater.inflate(R.layout.ticketlistview, null);
        }

        // ���̍s�̂��߂̃f�[�^��ǂݏo��
        FetchTicket data_for_this_line = Ticket.get(position);

        // ���̍s�̂��߂̃e�L�X�g���Z�b�g
        String Title
                = data_for_this_line.getTitle();
        String Loc
                = data_for_this_line.getLoc();
        String Date
                = data_for_this_line.getDate();
        String Price
                = data_for_this_line.getPrice();

        //textview��set
        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView loc = (TextView)v.findViewById(R.id.loc);
        loc.setText(Loc);
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(Date);
        TextView price = (TextView)v.findViewById(R.id.price);
        price.setText(Price);


        /* ---------- �s���̉摜�����[�h���ĕ`�� ------------ */

        // �s���̉摜�r���[
        ImageView imageView = (ImageView)v.findViewById(R.id.icon);

        // �摜��URL
        String img_url = data_for_this_line.getImageurl();


        // �񓯊�ŉ摜�Ǎ������s
        try{
            Log.d("ListViewTest", position + "�̉摜�ǂݍ��݂��J�n");

            DownloadImagetask task
                    = new DownloadImagetask(imageView, context);
            task.execute(img_url);
        }
        catch(Exception e){
            //
            Log.d("ListViewTest", position + "�̉摜�ǂݍ��݂Ɏ��s");
        }

        /* ---------- �s�̕`�悪���� ------------ */

        return v;
    }

}
