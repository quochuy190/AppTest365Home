package neo.vn.test365home.View.Longin;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.R;

public class ActivityWebviewDieukhoan extends BaseActivity {
    @BindView(R.id.webview_naptien)
    WebView webView;

    @Override
    public int setContentViewId() {
        return R.layout.activity_webview_naptien;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialogLoading();
        initAppbar();
        initData();
    }

    private void initData() {
        String sUrl = "http://home365.online/index.php/dieu-khoan/";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(sUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                hideDialogLoading();
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                hideDialogLoading();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                hideDialogLoading();
            }
        });
    }

    TextView txt_title;
    String sUserMe, SUserCon;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Điều khoản");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
