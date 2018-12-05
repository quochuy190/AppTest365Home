package neo.vn.test365home.View.Setup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.ActivityHome;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Config;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.MenuSearch.ActivityDanhsachKhoihoc;
import neo.vn.test365home.MenuSearch.ActivityListCity;
import neo.vn.test365home.MenuSearch.ActivityListDistrict;
import neo.vn.test365home.MenuSearch.ActivityListSchools;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.ReadPathUtil;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.View.Longin.ImpLogin;
import neo.vn.test365home.View.Longin.ImpUploadImage;
import neo.vn.test365home.View.Longin.PresenterLogin;
import neo.vn.test365home.View.Longin.PresenterUploadImage;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 8/1/2018
 * @updated 8/1/2018
 * @modified by
 * @updated on 8/1/2018
 * @since 1.0
 */
public class ActivityUpdateSubUser extends BaseActivity implements View.OnClickListener,
        ImpLogin.View, ImpUploadImage.View, ImpSetup.View {
    private static final String TAG = "ActivityAddSubUser";
    @BindView(R.id.edt_city_addsub)
    EditText edtCity;
    @BindView(R.id.edt_district_addsub)
    EditText edtDistrict;
    @BindView(R.id.edt_schools_addsub)
    EditText edtSchools;
    @BindView(R.id.edt_class_addsub)
    EditText edtClass;
    @BindView(R.id.edt_classname_addsub)
    EditText edtClassName;
    @BindView(R.id.edt_fullname_addsub)
    EditText edtFullname;
    @BindView(R.id.edt_user_addsub)
    EditText edtUser;
    @BindView(R.id.edt_password_addsub)
    EditText edtPass;
    @BindView(R.id.btn_ok_add_sub)
    Button btnAdd;
    @BindView(R.id.btn_back_add_sub)
    Button btn_back;
    @BindView(R.id.img_avata_update)
    CircleImageView img_avata_update;
    @BindView(R.id.img_get_image)
    ImageView img_getImage;
    PresenterLogin mPresenter;
    PresenterSetup mPresenterSetup;
    String sIdTinh, sIdQuan, sIdTruong, sIdKhoi, sIdNam = "1", sLop, sFullname, sAvata = "", sUser, sPass;
    String IMAGE_PATH = "";
    PresenterUploadImage mPresenterUploadImage;

    @Override
    public int setContentViewId() {
        return R.layout.activity_add_sub_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermistion();
        mPresenterSetup = new PresenterSetup(this);
        mPresenter = new PresenterLogin(this);
        mPresenterUploadImage = new PresenterUploadImage(this);
        btnAdd.setText("Cập nhật");
        initData();
        initEvent();
        edtUser.setFocusable(false);
        edtClass.setFocusable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(ActivityUpdateSubUser.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityUpdateSubUser.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(ActivityUpdateSubUser.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }
    boolean isShowpassComfirm = true;
    @BindView(R.id.img_showpass)
    ImageView img_showpass;
    private void initEvent() {
        img_getImage.setOnClickListener(this);
        edtDistrict.setOnClickListener(this);
        edtCity.setOnClickListener(this);
        edtSchools.setOnClickListener(this);
        img_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowpassComfirm) {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_hide));
                    //Glide.with(ActivityLogin.this).load(R.drawable.ic_eye_hide).into(img_showpass);
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowpassComfirm = !isShowpassComfirm;

                } else {
                    img_showpass.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_show));
                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShowpassComfirm = !isShowpassComfirm;
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetwork()) {
                    if (!(sIdTruong != null && sIdTruong.length() > 0)) {
                        showDialogNotify("Thông báo", "Bạn chưa chọn trường của bé");
                        return;
                    }
                    if (!(sIdTinh != null && sIdTinh.length() > 0)) {
                        showDialogNotify("Thông báo", "Bạn chưa chọn tỉnh thành phố");
                        return;
                    }
                    if (!(sIdQuan != null && sIdQuan.length() > 0)) {
                        showDialogNotify("Thông báo", "Bạn chưa chọn quận huyện");
                        return;
                    }

                    if (edtClassName.getText().length() == 0) {
                        showDialogNotify("Thông báo", "Bạn chưa nhập vào tên lớp của bé");
                        return;
                    } else sLop = edtClassName.getText().toString();
                    if (edtFullname.getText().length() == 0) {
                        showDialogNotify("Thông báo", "Bạn chưa nhập vào tên của bé");
                        return;
                    } else sFullname = edtFullname.getText().toString();
                    if (edtPass.getText().length() == 0) {
                        showDialogNotify("Thông báo", "Bạn chưa nhập mật khẩu");
                        return;
                    } else sPass = edtPass.getText().toString();
                    showDialogLoading();
                    String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                    mPresenterSetup.api_update_info_children(sUserMe, mChildren.getsUSERNAME(), sIdTinh, sIdQuan, sIdTruong
                            , sIdKhoi, edtClassName.getText().toString(), sIdNam, edtFullname.getText().toString(), edtPass.getText().toString(), sAvata);

                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityUpdateSubUser.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

    }

    Childrens mChildren;

    private void initData() {

        mChildren = App.mChilSetup;
        String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        if (mChildren != null) {
            if (mChildren.getsAVATAR() != null && mChildren.getsAVATAR().length() > 0) {
                sAvata = mChildren.getsAVATAR();
                Glide.with(this).load(Config.URL_IMAGE + mChildren.getsAVATAR())
                        .placeholder(R.drawable.icon_family)
                        .into(img_avata_update);
            } else
                Glide.with(this).load(R.drawable.icon_family).into(img_avata_update);
            if (mChildren.getsSCHOOL_NAME() != null && mChildren.getsSCHOOL_NAME().length() > 0)
                edtSchools.setText(mChildren.getsSCHOOL_NAME());
            if (mChildren.getsSCHOOL_ID() != null && mChildren.getsSCHOOL_ID().length() > 0)
                sIdTruong = mChildren.getsSCHOOL_ID();
            if (mChildren.getsDISTRICT_ID() != null && mChildren.getsDISTRICT_ID().length() > 0)
                sIdQuan = mChildren.getsDISTRICT_ID();
            if (mChildren.getsID_PROVINCE() != null && mChildren.getsID_PROVINCE().length() > 0)
                sIdTinh = mChildren.getsID_PROVINCE();
            if (mChildren.getsPROVINCE_NAME() != null && mChildren.getsPROVINCE_NAME().length() > 0)
                edtCity.setText(mChildren.getsPROVINCE_NAME());
           /* if (mChildren.get() != null && mChildren.getsFULLNAME().length() > 0)
                edtFullname.setText(mChildren.getsFULLNAME());*/
            if (mChildren.getsDISTRICT_NAME() != null && mChildren.getsDISTRICT_NAME().length() > 0)
                edtDistrict.setText(mChildren.getsDISTRICT_NAME());
            if (mChildren.getsLEVEL_ID() != null && mChildren.getsLEVEL_ID().length() > 0) {
                edtClass.setText(mChildren.getsLEVEL_ID());
                sIdKhoi = mChildren.getsLEVEL_ID();
            }
            edtClass.setTextColor(getResources().getColor(R.color.gray));
            edtUser.setTextColor(getResources().getColor(R.color.gray));
            if (mChildren.getsLEVEL_NAME() != null && mChildren.getsLEVEL_NAME().length() > 0)
                edtClassName.setText(mChildren.getsLEVEL_NAME());
            if (mChildren.getsFULLNAME() != null && mChildren.getsFULLNAME().length() > 0)
                edtFullname.setText(mChildren.getsFULLNAME());
            if (mChildren.getsUSERNAME() != null && mChildren.getsUSERNAME().length() > 0)
                edtUser.setText(mChildren.getsUSERNAME());
            if (mChildren.getsPASS() != null && mChildren.getsPASS().length() > 0)
                edtPass.setText(mChildren.getsPASS());

        }
        //  mPresenter.api_lis_children(sUserMe);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_district_addsub:
                if (App.mCity != null) {
                    App.mDistrict = null;
                    App.mSchools = null;
                    Intent intent = new Intent(ActivityUpdateSubUser.this, ActivityListDistrict.class);
                    intent.putExtra(Constants.KEY_SEND_CITY_ID, App.mCity.getsID());
                    startActivityForResult(intent, Constants.RequestCode.GET_DISTRICT);
                } else {
                    showDialogNotify(getString(R.string.notify), getString(R.string.notify_city_null));
                }

                break;
            case R.id.edt_city_addsub:
                App.mCity = null;
                App.mDistrict = null;
                Intent intent_city = new Intent(ActivityUpdateSubUser.this, ActivityListCity.class);
                startActivityForResult(intent_city, Constants.RequestCode.GET_CITY);
                break;
            case R.id.edt_schools_addsub:
                if (App.mDistrict != null) {
                    App.mSchools = null;
                    Intent intent_schools = new Intent(ActivityUpdateSubUser.this,
                            ActivityListSchools.class);
                    intent_schools.putExtra(Constants.KEY_SEND_DISTRICT_ID, App.mDistrict.getsID());
                    startActivityForResult(intent_schools, Constants.RequestCode.GET_SCHOOLS);
                } else {
                    showDialogNotify(getString(R.string.notify), getString(R.string.notify_city_null));
                }
                break;
            case R.id.edt_class_addsub:
                App.mKhoihoc = null;
                Intent intent_class = new Intent(ActivityUpdateSubUser.this,
                        ActivityDanhsachKhoihoc.class);
                startActivityForResult(intent_class, Constants.RequestCode.GET_KHOIHOC);
                break;
            case R.id.img_get_image:
                getImageFromAlbum();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.GET_DISTRICT:
                if (App.mDistrict != null) {
                    edtDistrict.setText(App.mDistrict.getsDISTRICT_NAME());
                    sIdQuan = App.mDistrict.getsID();
                    edtSchools.setText("");
                } else {
                    sIdQuan = "";
                    edtDistrict.setText("");
                    edtSchools.setText("");
                }

                break;
            case Constants.RequestCode.GET_CITY:
                if (App.mCity != null) {
                    sIdTinh = App.mCity.getsID();
                    edtCity.setText(App.mCity.getsPROVINCE_NAME());
                } else {
                    sIdTinh = "";
                    edtCity.setText("");
                }


                break;
            case Constants.RequestCode.GET_SCHOOLS:
                if (App.mSchools != null) {
                    sIdTruong = App.mSchools.getsID();
                    edtSchools.setText(App.mSchools.getsSCHOOL_NAME());
                } else {
                    sIdTruong = "";
                    edtSchools.setText("");
                    edtSchools.setHint("Tên trường");
                }

                break;
            case Constants.RequestCode.GET_KHOIHOC:
                if (App.mKhoihoc != null) {
                    sIdKhoi = App.mKhoihoc;
                    edtClass.setText(App.mKhoihoc);
                } else {
                    sIdKhoi = "";
                    edtClass.setText("");
                    edtClass.setHint("Chọn khối học");
                }
                break;
            case Constants.RequestCode.GET_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        IMAGE_PATH = ReadPathUtil.getPath(ActivityUpdateSubUser.this, data.getData());
                        File file = new File(IMAGE_PATH);
                        final Uri imageUri = data.getData();
                        File file1 = new File(imageUri.getPath());
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        img_avata_update.setImageBitmap(selectedImage);
                        mPresenterUploadImage.api_upload_image(IMAGE_PATH);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void getImageFromAlbum() {
        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, Constants.RequestCode.GET_IMAGE);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
       /* MultiImageSelector.create()
                .single() // single mode
                .start(ActivityAddSubUser.this, Constants.RequestCode.GET_IMAGE);*/
    }

    @Override
    public void show_api_register(List<ErrorApi> mLis) {

    }

    @Override
    public void create_sub_user(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                Intent intent = new Intent(ActivityUpdateSubUser.this, ActivityHome.class);
                startActivity(intent);
                finish();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }

    @Override
    public void show_api_login(List<Login> mLis) {

    }

    @Override
    public void show_api_update_info(List<Login> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_user_info(List<UserInfo> mLis) {

    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_config_children(List<ConfigChildren> mLis) {

    }

    @Override
    public void show_payment(List<ErrorApi> mLis) {

    }

    @Override
    public void show_change_pass(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_info_chil(List<Childrens> mLis) {

    }

    @Override
    public void show_update_info_chil(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else showDialogNotify("Lỗi", mLis.get(0).getsRESULT());
    }

    @Override
    public void show_get_history_balance(List<HistoryBalance> mLis) {

    }

    String sUrlAvata = "";

    @Override
    public void show_upload_image(String sUrlImage) {
        if (sUrlImage != null && sUrlImage.length() > 0) {
            sAvata = sUrlImage;
        }
    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {

    }

}
