package kenyawakita.musi_king;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class WebviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);

        Intent intent = getIntent();
        WebView web = (WebView) findViewById(R.id.webView);

        String url = intent.getStringExtra("url");
        setTitle(intent.getStringExtra("title"));

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        web.loadUrl(url);
    }
}
