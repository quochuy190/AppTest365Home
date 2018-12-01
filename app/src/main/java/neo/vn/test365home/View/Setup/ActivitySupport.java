package neo.vn.test365home.View.Setup;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.R;
import neo.vn.test365home.View.Longin.ActivityLogin;

public class ActivitySupport extends BaseActivity {
    @BindView(R.id.txt_hotline)
    TextView txt_hotline;
    @BindView(R.id.txt_email)
    TextView txt_email;
    @BindView(R.id.txt_web)
    TextView txt_web;
    @BindView(R.id.txt_facebook)
    TextView txt_facebook;

    @Override
    public int setContentViewId() {
        return R.layout.activity_support;
    }

    public static String FACEBOOK_URL = "https://www.facebook.com/Home365-909939962530854/";
    public static String FACEBOOK_PAGE_ID = "Home365-909939962530854/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        initEvent();
    }

    private void initData() {
        String sHotline = "Hotline " + "<b><u><font color='#3F51B5'>1900561548</font></u></b> ";
        txt_hotline.setText(Html.fromHtml(sHotline));
        String sEmail = "Email " + "<b><u><font color='#3F51B5'>cskh@home365.online</font></u></b> ";
        txt_email.setText(Html.fromHtml(sEmail));
        String sFacebook = "Facebook " + "<b><u><font color='#3F51B5'>https://www.facebook.com/Home365-909939962530854/</font></u></b> ";
        txt_facebook.setText(Html.fromHtml(sFacebook));
        String sWeb = "Website " + "<b><u><font color='#3F51B5'>https://home365.online</font></u></b> ";
        txt_web.setText(Html.fromHtml(sWeb));
    }

    private void initEvent() {
        txt_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(ActivitySupport.this);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        txt_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://home365.online";
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });
        txt_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLogin.call_phone(ActivitySupport.this, "1900561548");
            }
        });
        txt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"cskh@home365.online"});
                i.putExtra(Intent.EXTRA_SUBJECT, "HOME365");
                i.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ActivitySupport.this, "There are no email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText(getString(R.string.txt_support_app));
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
