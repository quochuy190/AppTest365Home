package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import neo.vn.test365home.Untils.StringUtil;

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
    @BindView(R.id.tv_dangnhap_otp)
    TextView txt_dangnhapngay;
    @BindView(R.id.edt_password_register)
    TextView txtPass;
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R.id.edt_password_confirm)
    TextView txtPassConfirm;
    @BindView(R.id.cb_remember_login)
    CheckBox cb_remember_login;
    @BindView(R.id.edt_gift_code)
    EditText edt_gift_code;
    @BindView(R.id.img_showpass1)
    ImageView img_showpass1;
    @BindView(R.id.img_showpass)
    ImageView img_showpass;
    PresenterLogin mPresenter;
    boolean isDangky = false;
    boolean isShowpass = true, isShowpassComfirm = true;
    String id;

    @Override
    public int setContentViewId() {
        return R.layout.activity_register;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting unique id for device
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.i(TAG, "onCreate: " + " : " + id);
        mPresenter = new PresenterLogin(this);
        initEvent();
    }


    public void initEvent() {
        ll_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                intent.putExtra(Constants.KEY_REGISTER_SUCCESS, false);
                startActivity(intent);
                finish();
            }
        });
        txt_dangnhapngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityWebviewDieukhoan.class);
                startActivity(intent);
            }
        });
        cb_remember_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDangky = cb_remember_login.isChecked();
                if (isDangky) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btn_Register.setBackground(getResources().getDrawable(R.drawable.spr_click_button));
                        btn_Register.setTextColor(getResources().getColor(R.color.white));
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btn_Register.setBackground(getResources().getDrawable(R.drawable.spr_click_button_disible));
                        btn_Register.setTextColor(getResources().getColor(R.color.button_not_click));
                    }
                }
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDangky) {
                    if (isNetwork()) {
                        sUserName = txtUsername.getText().toString().trim();
                        if (!StringUtil.check_tiengviet(sUserName)) {
                            showDialogNotify("Lỗi", "Tên đăng nhập phải là tiếng việt không dấu," +
                                    " không chứa dấu cách và ký tự đặc biệt");
                            return;
                        }
                        sPassWord = txtPass.getText().toString().trim();
                        sPassComfirm = txtPassConfirm.getText().toString().trim();
                        if (!StringUtil.check_tiengviet(sPassWord)) {
                            showDialogNotify("Lỗi", "Mật khẩu nhập vào là tiếng việt không dấu," +
                                    " không chứa dấu cách và ký tự đặc biệt");
                            return;
                        }
                        if (sUserName.length() > 4 && sUserName.indexOf(" ") < 0) {
                            if (sPassWord.length() > 7 && sPassComfirm.length() > 7 && sPassWord.indexOf(" ") < 0) {
                                if (sPassWord.equals(sPassComfirm)) {
                                    showDialogLoading();
                                    mPresenter.api_register(sUserName, sPassWord,
                                            edt_gift_code.getText().toString().toUpperCase(), id);
                                } else {
                                    showDialogNotify(getString(R.string.error),
                                            "Mật khẩu xác nhận không trùng với mật khẩu");
                                }
                            } else {
                                showDialogNotify("Lỗi", "Mật khẩu phải trên 8 ký tự," +
                                        " không chứa dấu cách và ký tự đặc biệt");
                            }
                        } else
                            showDialogNotify("Lỗi", "Tên đăng nhập phải dài hơn 4 ký tự," +
                                    " không chứa dấu cách và ký tự đặc biệt");

                    } else
                        showDialogNotify(getString(R.string.error_network), getString(R.string.error_network_message));
                } else
                    showDialogNotify("Lỗi", "Điều khoản sử dụng đảm bảo việc cung cấp và sử dụng Home365 đúng quy cách và hợp pháp." +
                            " Quý Phụ huynh vui lòng đọc kỹ và bấm chọn Đồng ý.");
            }
        });
        img_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowpassComfirm) {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_hide));
                    //Glide.with(ActivityLogin.this).load(R.drawable.ic_eye_hide).into(img_showpass);
                    txtPassConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowpassComfirm = !isShowpassComfirm;

                } else {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_show));
                    txtPassConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShowpassComfirm = !isShowpassComfirm;

                }
            }
        });
        img_showpass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowpass) {
                    img_showpass1.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_hide));
                    //Glide.with(ActivityLogin.this).load(R.drawable.ic_eye_hide).into(img_showpass);
                    txtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowpass = !isShowpass;

                } else {
                    img_showpass1.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_show));
                    txtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShowpass = !isShowpass;

                }
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
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
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
