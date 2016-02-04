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



public class YoutubeListAdapter extends ArrayAdapter<FetchYoutube> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<FetchYoutube> Youtube = new ArrayList<FetchYoutube>();


    // ����
    public YoutubeListAdapter(Context context,
                              int id,
                              ArrayList<FetchYoutube> Youtube
    )
    {
        super(context,id, Youtube);

        this.context = context;
        this.Youtube = Youtube;

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
            v = mInflater.inflate(R.layout.youtubelistview, null);
        }

        // ���̍s�̂��߂̃f�[�^��ǂݏo��
        FetchYoutube data_for_this_line = Youtube.get(position);

        // ���̍s�̂��߂̃e�L�X�g���Z�b�g
        String Title
                = data_for_this_line.getTitle();
        String Date
                = data_for_this_line.getDate();

        Log.d("ListViewTest", position + "��text��" + Title);
        TextView tv = (TextView)v.findViewById(R.id.title);
        tv.setText(Title);
        TextView loc = (TextView)v.findViewById(R.id.loc);
        loc.setText(Date);


        /* ---------- �s���̉摜�����[�h���ĕ`�� ------------ */

        // �s���̉摜�r���[
        ImageView imageView = (ImageView)v.findViewById(R.id.icon);

        // �摜��URL
        String img_url = data_for_this_line.getImageUrl();


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
