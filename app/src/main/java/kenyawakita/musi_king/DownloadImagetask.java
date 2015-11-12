package kenyawakita.musi_king;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;


public class DownloadImagetask extends AsyncTask<String,Void,Bitmap> {
    private ImageView imageView;
    private Context context;

    // ����
    public DownloadImagetask(ImageView imageView, Context context) {
        this.imageView = imageView;
        this.context = context;
    }

    // execute���̃^�X�N�{�́B�摜���r�b�g�}�b�v�Ƃ��ēǂݍ���ŕԂ�
    @Override
    protected Bitmap doInBackground(String... params) {
        synchronized (context){
            try {
                String str_url = params[0];
                URL imageUrl = new URL(str_url);
                InputStream imageIs;
                imageIs = imageUrl.openStream();
                Bitmap bm = BitmapFactory.decodeStream(imageIs);

                return bm;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if(result != null){
            imageView.setImageBitmap(result);
        }
    }
}
