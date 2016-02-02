package kenyawakita.musi_king.dummy;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import kenyawakita.musi_king.R;

public class WebviewActivity extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.activity_content, container, false);
        WebView web = (WebView) getActivity().findViewById(R.id.webView);

        //タイトルとurlが欲しい


        return view;
    }
}
