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


    // ������
    public BlogListAdapter(Context context,
                             int id,
                             ArrayList<FetchBlog> Blog
    )
    {
        super(context,id, Blog);

        this.context = context;
        this.Blog = Blog;

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


        /* ---------- �s�������� ------------ */

        // �s��\���r���[
        View v = convertView;
        if(v == null){
            Log.d("ListViewTest", position + "��v��V�K����");
            v = mInflater.inflate(R.layout.bloglistview, null);
        }

        // ���̍s�̂��߂̃f�[�^��ǂݏo��
        FetchBlog data_for_this_line = Blog.get(position);

        // ���̍s�̂��߂̃e�L�X�g���Z�b�g
        String Title
                = data_for_this_line.getTitle();
        String Date
                = data_for_this_line.getDate();

        //textview��set
        TextView title = (TextView)v.findViewById(R.id.title);
        title.setText(Title);
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(Date);


        // ImageView��set
        ImageView imageView = (ImageView)v.findViewById(R.id.icon);

        Bitmap result = BitmapFactory.decodeResource(v.getResources(),R.drawable.images11);
                imageView.setImageBitmap(result);

        return v;
    }


}
