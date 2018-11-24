package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivityChangePassWord extends BaseActivity implements ImpSetup.View {
    @BindView(R.id.btn_change_pass)
    Button btn_change_pass;
    @BindView(R.id.edt_pass_old)
    TextView edt_pass_old;
    @BindView(R.id.edt_password_new)
    TextView edt_password_new;
    @BindView(R.id.edt_pass_comfirm)
    TextView edt_pass_comfirm;
    PresenterSetup mPresenter;

    @Override
    public int setContentViewId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterSetup(this);
        initAppbar();
        initEvent();
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Đổi mật khẩu");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String sPassNew, sPassOld, sPassComfirm, sPassword, sUserMe;

    private void initEvent() {
        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPassOld = edt_pass_old.getText().toString();
                sPassNew = edt_password_new.getText().toString();
                sPassComfirm = edt_pass_comfirm.getText().toString();
                sPassword = SharedPrefs.getInstance().get(Constants.KEY_PASSWORD, String.class);
                sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                if (sPassOld.length() > 0 && sPassNew.length() > 0 && sPassComfirm.length() > 0) {
                    if (sPassword.equals(sPassOld)) {
                        if (sPassNew.equals(sPassComfirm)) {
                            showDialogLoading();
                            mPresenter.api_get_change_pass(sUserMe, sPassOld, sPassNew);
                        } else showDialogNotify("Thông báo", "Mời nhập lại mật khẩu mới");
                    } else showDialogNotify("Thông báo", "Sai mật khẩu, mời nhập lại");
                } else showDialogNotify("Thông báo", "Mời bạn nhập vào đầy đủ thông tin");
            }
        });
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_get_config_children(List<ConfigChildren> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_payment(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_change_pass(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            SharedPrefs.getInstance().put(Constants.KEY_PASSWORD, sPassNew);
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else showDialogNotify("Lỗi", "Đổi mật khẩu không thành công");
    }

    @Override
    public void show_get_info_chil(List<Childrens> mLis) {

    }

    @Override
    public void show_update_info_chil(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_history_balance(List<HistoryBalance> mLis) {

    }
}
