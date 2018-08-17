package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/30/2018
 * @updated 7/30/2018
 * @modified by
 * @updated on 7/30/2018
 * @since 1.0
 */
public class ActivityRegister extends BaseActivity implements ImpLogin.View {
    private static final String TAG = "ActivityRegister";
    String sUserName, sPassWord, sPassComfirm;
    @BindView(R.id.btn_dangnhap_Login)
    Button btn_Register;
    @BindView(R.id.edt_taikhoan_Login)
    TextView txtUsername;
    @BindView(R.id.edt_password_register)
    TextView txtPass;
    @BindView(R.id.edt_password_confirm)
    TextView txtPassConfirm;
    PresenterLogin mPresenter;

    @Override
    public int setContentViewId() {
        return R.layout.activity_register;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterLogin(this);
        initEvent();
    }

    public void initEvent() {
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetwork()) {
                    sUserName = txtUsername.getText().toString();
                    sPassWord = txtPass.getText().toString();
                    sPassComfirm = txtPassConfirm.getText().toString();
                    if (sUserName.length() > 0 && sPassWord.length() > 0 && sPassComfirm.length() > 0) {
                        if (sPassWord.equals(sPassComfirm)) {
                            showDialogLoading();
                            mPresenter.api_register(sUserName, sPassWord);
                        } else showDialogNotify(getString(R.string.error),
                                "Mật khẩu xác nhận không đúng");
                    } else showDialogNotify(getString(R.string.error),
                            getString(R.string.masseger_edt_null));
                } else showDialogNotify(getString(R.string.error_network),
                        getString(R.string.error_network_message));
            }
        });


    }

    @Override
    public void show_api_register(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                SharedPrefs.getInstance().put(Constants.KEY_USERNAME, sUserName);
                SharedPrefs.getInstance().put(Constants.KEY_PASSWORD, sPassWord);
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ActivityRegister.this, ActivityLogin.class);
                intent.putExtra(Constants.KEY_REGISTER_SUCCESS, true);
                startActivity(intent);
                finish();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }

    @Override
    public void create_sub_user(List<ErrorApi> mLis) {

    }

    @Override
    public void show_api_login(List<Login> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_api_update_info(List<Login> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {

    }
}
