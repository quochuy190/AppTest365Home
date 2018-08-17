package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
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
 * @created 7/31/2018
 * @updated 7/31/2018
 * @modified by
 * @updated on 7/31/2018
 * @since 1.0
 */
public class ActivityUpdateInfo extends BaseActivity implements ImpLogin.View {
    String sUserName, sPhone = "", sEmail = "", sAvata = "", sFullName = "";
    @BindView(R.id.edt_fullname_update)
    EditText edtFullname;
    @BindView(R.id.edt_phone_update)
    EditText edtPhone;
    @BindView(R.id.edt_email_update)
    EditText edtEmail;
    @BindView(R.id.img_avata_update)
    CircleImageView imgAvata;
    @BindView(R.id.btn_back_update)
    Button btnBack;
    @BindView(R.id.btn_ok_update)
    Button btnOkie;
    PresenterLogin mPresenter;
    Login mLogin;

    @Override
    public int setContentViewId() {
        return R.layout.activity_update_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterLogin(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        btnOkie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtFullname.getText().toString().length() > 0) {
                    sFullName = edtFullname.getText().toString();
                }
                if (edtPhone.getText().toString().length() > 0) {
                    sPhone = edtPhone.getText().toString();
                }
                if (edtEmail.getText().toString().length() > 0) {
                    sEmail = edtEmail.getText().toString();
                }
                if (sFullName.length() > 0 && sPhone.length() > 0 & sEmail.length() > 0) {
                    sUserName = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                    mPresenter.api_update_info(sUserName, sFullName, sPhone, sEmail, sAvata);
                } else
                    showDialogNotify(getString(R.string.error), getString(R.string.masseger_edt_null));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityUpdateInfo.this, ActivityAddSubUser.class);
                // intent.putExtra(Constants.KEY_REGISTER_SUCCESS, true);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
        mLogin = (Login) getIntent().getSerializableExtra(Constants.KEY_SEND_LOGIN_INFO);
        if (mLogin!=null){
            if (mLogin.getsFULLNAME()!=null){
                edtFullname.setText(mLogin.getsFULLNAME());
            }
            if (mLogin.getsPHONENUMBER()!=null){
                edtPhone.setText(mLogin.getsPHONENUMBER());
            }
            if (mLogin.getsEMAIL()!=null){
                edtEmail.setText(mLogin.getsEMAIL());
            }
        }
    }

    @Override
    public void show_api_register(List<ErrorApi> mLis) {

    }

    @Override
    public void create_sub_user(List<ErrorApi> mLis) {

    }

    @Override
    public void show_api_login(List<Login> mLis) {

    }

    @Override
    public void show_api_update_info(List<Login> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                //SharedPrefs.getInstance().put(Constants.KEY_ISLOGIN, true);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityUpdateInfo.this, ActivityAddSubUser.class);
                // intent.putExtra(Constants.KEY_REGISTER_SUCCESS, true);
                startActivity(intent);
                finish();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {

    }
}
