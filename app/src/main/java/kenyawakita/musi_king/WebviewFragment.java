package kenyawakita.musi_king;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableLayout;

import kenyawakita.musi_king.R;

public class WebviewFragment extends Fragment{

    String Tab_Name = null;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.activity_content, container, false);

        Bundle bundle = getArguments();
        Tab_Name = bundle.getString("Tab_name");
        String url = bundle.getString("url");
        
        WebView web = (WebView) view.findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        // GAスクリーン計測　第一引数:Context, 第二引数:スクリーン名
        MeasurementGAManager.sendGAScreen(getActivity(), Tab_Name);
    }

}
