package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import neo.vn.test365home.ActivityHome;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.R;

public class ActivityDownloadAppKid extends BaseActivity {
    @BindView(R.id.btn_back)
    RelativeLayout btn_back;
    @BindView(R.id.btn_download)
    RelativeLayout btn_download;

    @Override
    public int setContentViewId() {
        return R.layout.activity_download_app_kid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initEvent();
    }

    private void initEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDownloadAppKid.this, ActivityHome.class);
                startActivity(intent);
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_market();
            }
        });
    }

    private void start_market(){
        final String my_package_name = "neo.vn.test365children";  // <- HERE YOUR PACKAGE NAME!!
        String url = "";

        try {
            //Check whether Google Play store is installed or not:
            this.getPackageManager().getPackageInfo("com.android.vending", 0);

            url = "market://details?id=" + my_package_name;
        } catch ( final Exception e ) {
            url = "https://play.google.com/store/apps/details?id=" + my_package_name;
        }


//Open the app page in Google Play store:
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        startActivity(intent);
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Cài đặt App con");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
