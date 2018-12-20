package neo.vn.test365home.View.Setup;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.R;

public class ActivityWebviewNaptien extends BaseActivity {
    @BindView(R.id.webview_naptien)
    WebView webView;

    @Override
    public int setContentViewId() {
        return R.layout.activity_webview_naptien;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
    }

    private void initData() {
        String sUrl = getIntent().getStringExtra(Constants.KEY_SEND_URL_WEBVIEW_NAPTIEN);
        if (sUrl != null && sUrl.length() > 0) {
            goUrl(sUrl);
        }
    }

    private void goUrl(String url) {
        if (url.isEmpty()) {
            Toast.makeText(this, "Please enter url", Toast.LENGTH_SHORT).show();
            return;
        }
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setDefaultFontSize(18);
        webSettings.setTextZoom((int) (webSettings.getTextZoom() * 1.2));
        webView.loadUrl(url);
    /*    webView.loadDataWithBaseURL("", url,
                "text/html", "UTF-8", "");*/
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }

    TextView txt_title;
    String sUserMe, SUserCon;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Nạp tiền trực tuyến");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
