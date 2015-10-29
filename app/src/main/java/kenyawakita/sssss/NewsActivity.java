package kenyawakita.sssss;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        final View view = inflater.inflate(R.layout.news_tab, container, false);
        final Activity owner=getActivity();

        AsyncHttpRequest_Tab3 task = new AsyncHttpRequest_Tab3(owner);
        task.execute(Constants.ORICON_URL, Constants.NAVER_URL, Constants.NATARY_URL);


        return view;

    }
}


