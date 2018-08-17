package neo.vn.test365home;

import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Untils.FragmentUtil;
import neo.vn.test365home.View.Baitap.FragmentBaitap;
import neo.vn.test365home.View.Setup.FragmentSetup;
import neo.vn.test365home.View.Thongke.FragmentThongke;
import neo.vn.test365home.View.Tintuc.FragmentTintuc;

public class ActivityHome extends BaseActivity {
    private static final String TAG = "ActivityHome";
    public static BottomBar bottomBar;

    @Override
    public int setContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data="[{\"ERROR\":\"0000\",\"MESSAGE\":\"SUCCESS\",\"RESULT\":\"Lấy danh sách con thành công\"," +
                "\"ID\":\"38\",\"FULLNAME\":\"C.Ronaldo\",\"AVATAR\":\"\",\"USERNAME\":\"ronaldo123\",\"PASS\":" +
                "\"123456\",\"ID_LEVEL\":\"1\",\"LEVEL_NAME\":\"Lớp 1\",\"CLASS\":\"1A5\"}]";
        //String data = "[{\"ERROR\":\"0000\",\"MESSAGE\":\"SUCCESS\",\"RESULT\":\"Đăng ký thành công\"}]";
        String data2 = "[{\"userName\": \"sandeep\",\"age\":30},{\"userName\": \"vivan\",\"age\":5}]";
        JSONArray jsonArr = null;
        try {
            jsonArr = new JSONArray(data);
            for (int i = 0; i < jsonArr.length(); i++)
            {
                JSONObject jsonObj = jsonArr.getJSONObject(i);

                System.out.println(jsonObj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        initBottomBar();
    }

    private void initBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_baitap:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentBaitap.getInstance(),
                                R.id.frame_content);
                        break;
                    case R.id.tab_thongke:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentThongke.getInstance(),
                                R.id.frame_content);

                        break;
                    case R.id.tab_tintuc:

                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentTintuc.getInstance(),
                                R.id.frame_content);

                        break;
                    case R.id.tab_setup:
                        FragmentUtil.replaceFragmentMain(ActivityHome.this,
                                FragmentSetup.getInstance(),
                                R.id.frame_content);
                        break;

                }
            }
        });

    }
}