package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.BuildConfig;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;


/**
 * Created by QQ on 8/16/2017.
 */

public class ActivityLogin extends BaseActivity implements ImpLogin.View {
    @BindView(R.id.btn_dangnhap_Login)
    Button btn_dangnhap_Login;
    @BindView(R.id.edt_taikhoan_Login)
    EditText edt_taikhoan_Login;
    @BindView(R.id.edt_password_Login)
    EditText edt_matkhau_Login;
    boolean isShowpass = true;
    @BindView(R.id.img_showpass)
    ImageView img_showpass;
    PresenterLogin mPresenter;
    String sUserName, sPassWord, sTokenKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterLogin(this);
        initData();
        initEvent();
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_login;
    }


    public void initData() {
        boolean isRegister = getIntent().getBooleanExtra(Constants.KEY_REGISTER_SUCCESS, false);
        if (isRegister) {
            sUserName = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
            sPassWord = SharedPrefs.getInstance().get(Constants.KEY_PASSWORD, String.class);
            edt_taikhoan_Login.setText(sUserName);
            edt_matkhau_Login.setText(sPassWord);
            login_api();
        } else {

        }

    }

    private void initEvent() {
        btn_dangnhap_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetwork()) {
                    showDialogNotify("Thông báo",
                            "Mất kết nối, vui long kiểm tra lại mạng để tiếp tục");
                } else {
                    if (edt_taikhoan_Login.getText().length() > 0 && edt_matkhau_Login.getText().length() > 0) {
                        sUserName = edt_taikhoan_Login.getText().toString();
                        sPassWord = edt_matkhau_Login.getText().toString();
                        login_api();
                    } else
                        showDialogNotify("Thông báo",
                                "Mời bạn nhập vào tài khoản và mật khẩu để đăng nhập");

                }
            }
        });

        img_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowpass) {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_show));
                    //Glide.with(ActivityLogin.this).load(R.drawable.ic_eye_hide).into(img_showpass);
                    edt_matkhau_Login.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowpass = !isShowpass;

                } else {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_hide));

                    edt_matkhau_Login.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShowpass = !isShowpass;

                }
            }
        });
    }

    @Override
    public void show_api_register(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void create_sub_user(List<ErrorApi> mLis) {

    }

    @Override
    public void show_api_login(List<Login> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                SharedPrefs.getInstance().put(Constants.KEY_ISLOGIN, true);
                SharedPrefs.getInstance().put(Constants.KEY_USERNAME, sUserName);
                SharedPrefs.getInstance().put(Constants.KEY_PASSWORD, sPassWord);
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityLogin.this, ActivityUpdateInfo.class);
                intent.putExtra(Constants.KEY_SEND_LOGIN_INFO, mLis.get(0));
                intent.putExtra(Constants.KEY_IS_START_LOGIN, true);
                SharedPrefs.getInstance().put(Constants.KEY_LOGININFO, mLis.get(0));
                startActivity(intent);
                finish();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }

    @Override
    public void show_api_update_info(List<Login> mLis) {

    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {

    }

    public void login_api() {
        sTokenKey = SharedPrefs.getInstance().get(Constants.KEY_TOKEN, String.class);
        showDialogLoading();
        mPresenter.api_login(sUserName, sPassWord, BuildConfig.VERSION_NAME,
                android.os.Build.BRAND + " " + android.os.Build.MODEL,
                "2", android.os.Build.VERSION.RELEASE, sTokenKey);
    }
}
