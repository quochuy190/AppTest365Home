package neo.vn.test365home.View.Longin;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.ActivityHome;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.BuildConfig;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ClickDialog;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.KeyboardUtil;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;


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
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R.id.txt_quenmatkhau)
    TextView txt_quenmatkhau;
    @BindView(R.id.txt_hotline)
    TextView txt_hotline;
    boolean isRegister;

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
        String s = "Hotline " + "<b><u><font color='#3F51B5'>1900561548</font></u></b> ";
        txt_hotline.setText(Html.fromHtml(s));
        isRegister = getIntent().getBooleanExtra(Constants.KEY_REGISTER_SUCCESS, false);
        sUserName = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        sPassWord = SharedPrefs.getInstance().get(Constants.KEY_PASSWORD, String.class);
        edt_taikhoan_Login.setText(sUserName);
        if (isRegister) {
            edt_matkhau_Login.setText(sPassWord);
            login_api();
        } else {

        }

    }

    private void initEvent() {
        txt_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_phone(ActivityLogin.this, "1900561548");
            }
        });
        txt_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "Nếu quên mật khẩu, quý phụ huynh có thể nhắn tin theo mẫu " +
                        "<b><font color='#FF6600'>Home365 MK</font></b> " +
                        " gửi <b><font color='#FF6600'>8055</font></b> để lấy lại mật khẩu." +
                        "\nCước tin nhắn 1.000 đồng";
                showDialogComfirm("Thông báo", s, false, new ClickDialog() {
                    @Override
                    public void onClickYesDialog() {
                        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", "8055");
                        smsIntent.putExtra("sms_body", "Home365 MK");
                        try {
                            startActivity(smsIntent);
                        } catch (ActivityNotFoundException e) {
                            // Display some sort of error message here.
                        }
                    }

                    @Override
                    public void onClickNoDialog() {

                    }
                });
            }
        });
        ll_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                intent.putExtra(Constants.KEY_REGISTER_SUCCESS, false);
                startActivity(intent);
                finish();
            }
        });

        btn_dangnhap_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_api();

            }
        });

        edt_matkhau_Login.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    login_api();
                    return true;
                }
                return false;
            }
        });

        img_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowpass) {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_hide));
                    //Glide.with(ActivityLogin.this).load(R.drawable.ic_eye_hide).into(img_showpass);
                    edt_matkhau_Login.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowpass = !isShowpass;

                } else {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_show));

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
                SharedPrefs.getInstance().put(Constants.KEY_LOGININFO, mLis.get(0));
/*                SharedPrefs.getInstance().put(Constants.KEY_URL_MEDIA, mLis.get(0).getsMEDIA_SERVER());
                SharedPrefs.getInstance().put(Constants.KEY_BASE_URL, mLis.get(0).getsAPI_SERVER());*/
             /*   Config.URL_IMAGE = mLis.get(0).getsMEDIA_SERVER();
                Config.URL_VIDEO = mLis.get(0).getsMEDIA_SERVER();*/
                if (isRegister) {
                    SharedPrefs.getInstance().put(Constants.KEY_REGISTER_SUCCESS, false);
                    Intent intent = new Intent(ActivityLogin.this, ActivityUpdateInfo.class);
                    intent.putExtra(Constants.KEY_SEND_LOGIN_INFO, mLis.get(0));
                    intent.putExtra(Constants.KEY_IS_START_LOGIN, true);
                    SharedPrefs.getInstance().put(Constants.KEY_LOGININFO, mLis.get(0));
                    startActivity(intent);
                    finish();
                } else {
                    mPresenter.api_lis_children(sUserName);
                }
                //SharedPrefs.getInstance().put(Constants.KEY_SAVE_PROMOTIONCODE, sUserName);


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
        btn_dangnhap_Login.setEnabled(true);
        hideDialogLoading();
    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            SharedPrefs.getInstance().put(Constants.KEY_ISLOGIN, true);
            Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
            intent.putExtra(Constants.KEY_SEND_LOGIN_INFO, mLis.get(0));
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(ActivityLogin.this, ActivityNewActive.class);
            startActivity(intent);
            finish();
            //   Toast.makeText(this, "Không có tài khoản", Toast.LENGTH_SHORT).show();
        }
    }

    public void login_api() {
        if (!isNetwork()) {
                 /*   showDialogNotify("Thông báo",
                            "Mất kết nối, vui lòng kiểm tra lại mạng để tiếp tục");*/
            return;
        }
        sUserName = edt_taikhoan_Login.getText().toString().trim();
        sPassWord = edt_matkhau_Login.getText().toString().trim();
        if (sUserName.length() == 0) {
            edt_taikhoan_Login.requestFocus();
            KeyboardUtil.requestKeyboard(edt_taikhoan_Login);
            Toast.makeText(ActivityLogin.this, "Bạn chưa nhập vào tên đăng nhập cho bé", Toast.LENGTH_SHORT).show();
            // showDialogNotify("Thông báo", "Bạn chưa nhập vào tên đăng nhập cho bé");
            return;
        }
        if (sPassWord.length() == 0) {
            edt_matkhau_Login.requestFocus();
            KeyboardUtil.requestKeyboard(edt_matkhau_Login);
            Toast.makeText(ActivityLogin.this, "Bạn chưa nhập vào tên đăng nhập cho bé", Toast.LENGTH_SHORT).show();
            // showDialogNotify("Thông báo", "Bạn chưa nhập vào tên đăng nhập cho bé");
            return;
        }
        if (!StringUtil.check_tiengviet(sUserName)) {
            btn_dangnhap_Login.setEnabled(true);
            showDialogNotify("Lỗi", "Tên đăng nhập phải là tiếng việt không dấu," +
                    " không chứa dấu cách và ký tự đặc biệt");
            return;
        }
        if (!StringUtil.check_tiengviet(sPassWord)) {
            btn_dangnhap_Login.setEnabled(true);
            showDialogNotify("Lỗi", "Mật khẩu nhập vào là tiếng việt không dấu");
            return;
        }
        sTokenKey = SharedPrefs.getInstance().get(Constants.KEY_TOKEN, String.class);
        showDialogLoading();
        mPresenter.api_login(sUserName, sPassWord, BuildConfig.VERSION_NAME,
                android.os.Build.BRAND + " " + android.os.Build.MODEL,
                "2", android.os.Build.VERSION.RELEASE, sTokenKey);
    }

    public static void call_phone(Context mContext, String phone) {
        sPhone = phone;
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall(mContext, phone);
        } else {
            if (ActivityCompat.checkSelfPermission(mContext,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                phoneCall(mContext, phone);
            } else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    public static String sPhone;

    private static void phoneCall(Context mContext, String phone) {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            mContext.startActivity(callIntent);
        } else {
            Toast.makeText(mContext, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch (requestCode) {
            case 9:
                permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (permissionGranted) {
            phoneCall(this, sPhone);
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
