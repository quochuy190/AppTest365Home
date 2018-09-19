package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

public class ActivityManageAccount extends BaseActivity implements ImpSetup.View {
    @BindView(R.id.txt_username)
    TextView txt_username;
    @BindView(R.id.txt_fullname)
    TextView txt_fullname;
    @BindView(R.id.txt_email)
    TextView txt_email;
    @BindView(R.id.txt_phone)
    TextView txt_phone;
    @BindView(R.id.txt_account_main)
    TextView txt_main_account;
    @BindView(R.id.txt_bonus_account)
    TextView txt_bonus_account;
    @BindView(R.id.btn_naptien)
    Button btn_naptien;
    PresenterSetup mPresenter;

    @Override
    public int setContentViewId() {
        return R.layout.activity_account_manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterSetup(this);
        initData();
        initEvent();
        initAppbar();
    }
    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Quản lý tài khoản");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initEvent() {

    }

    String sUserMe;

    private void initData() {
        showDialogLoading();
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.api_get_user_info(sUserMe);
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

    }

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR() != null && mLis.get(0).getsERROR().equals("0000")) {
            UserInfo obj = mLis.get(0);
            if (obj.getsCORE_BALANCE() != null)
                txt_main_account.setText(StringUtil.formatNumber(obj.getsCORE_BALANCE()));
            if (obj.getsPROMOTION_BALANCE() != null)
                txt_bonus_account.setText(StringUtil.formatNumber(obj.getsPROMOTION_BALANCE()));
            txt_fullname.setText(obj.getsFULLNAME());
            txt_username.setText(obj.getsUSERNAME());
            txt_email.setText(obj.getsEMAIL());
            txt_phone.setText(obj.getsPHONENUMBER());
        }
    }
}
