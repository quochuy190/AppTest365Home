package neo.vn.test365home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Untils.FragmentUtil;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.View.Baitap.FragmentBaitapNew;
import neo.vn.test365home.View.Setup.FragmentSetup;
import neo.vn.test365home.View.Thongke.FragmentThongkeHome;

public class ActivityHome extends BaseActivity {
    private static final String TAG = "ActivityHome";
    public static BottomBar bottomBar;
    @BindView(R.id.frame_content)
    FrameLayout frame_content;

    @Override
    public int setContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        initBottomBar();
        String sToken = SharedPrefs.getInstance().get(Constants.KEY_TOKEN, String.class);
        Log.i(TAG, "onCreate: " + sToken);
    }

    private void initData() {

    }

    private void initBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_baitap:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentBaitapNew.getInstance(),
                                R.id.frame_content);
                        break;
                    case R.id.tab_thongke:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentThongkeHome.getInstance(),
                                R.id.frame_content);

                        break;
              /*      case R.id.tab_tintuc:

                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentTintuc.getInstance(),
                                R.id.frame_content);

                        break;*/
                    case R.id.tab_setup:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentSetup.getInstance(),
                                R.id.frame_content);
                        break;

                }
            }
        });

    }
    boolean isDoubleClick;
    @Override
    public void onBackPressed() {
        if (isDoubleClick) {
            finish();
            return;
        }
        this.isDoubleClick = true;
        Toast.makeText(this, "Chạm lần nữa để thoát", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isDoubleClick = false;
            }
        }, 2000);
        /*Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_content);
        if (fragment instanceof FragmentBaitap) {
            FragmentBaitap.getInstance().fragmentBackTack();
        } else {
            super.onBackPressed();
        }*/
    }
}