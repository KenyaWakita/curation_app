package kenyawakita.musi_king;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kenyawakita.musi_king.R;

public class WebviewFragment extends Fragment{

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.activity_content, container, false);

        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        
        WebView web = (WebView) view.findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);

        return view;
    }

}
