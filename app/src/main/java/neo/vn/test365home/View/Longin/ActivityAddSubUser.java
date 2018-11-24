package neo.vn.test365home.View.Longin;

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

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.ActivityHome;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.MenuSearch.ActivityDanhsachKhoihoc;
import neo.vn.test365home.MenuSearch.ActivityDanhsachNamhoc;
import neo.vn.test365home.MenuSearch.ActivityListCity;
import neo.vn.test365home.MenuSearch.ActivityListDistrict;
import neo.vn.test365home.MenuSearch.ActivityListSchools;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.ReadPathUtil;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

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
public class ActivityAddSubUser extends BaseActivity implements View.OnClickListener, ImpLogin.View, ImpUploadImage.View {
    private static final String TAG = "ActivityAddSubUser";
    @BindView(R.id.edt_city_addsub)
    EditText edtCity;
    @BindView(R.id.edt_district_addsub)
    EditText edtDistrict;
    @BindView(R.id.edt_schools_addsub)
    EditText edtSchools;
    @BindView(R.id.edt_class_addsub)
    EditText edtClass;
    @BindView(R.id.edt_year_addsub)
    EditText edt_year_addsub;
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
    String sIdTinh, sIdQuan, sIdTruong, sIdKhoi, sIdNam = "1", sLop, sFullname, sAvata = "", sUser, sPass;
    String IMAGE_PATH = "";
    PresenterUploadImage mPresenterUploadImage;
    boolean isShowpassComfirm = true;
    @BindView(R.id.img_showpass)
    ImageView img_showpass;

    @Override
    public int setContentViewId() {
        return R.layout.activity_add_sub_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermistion();
        mPresenter = new PresenterLogin(this);
        mPresenterUploadImage = new PresenterUploadImage(this);
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(ActivityAddSubUser.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityAddSubUser.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(ActivityAddSubUser.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

    private void initEvent() {
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
        img_getImage.setOnClickListener(this);
        edtDistrict.setOnClickListener(this);
        edtCity.setOnClickListener(this);
        edtSchools.setOnClickListener(this);
        edtClass.setOnClickListener(this);
        edt_year_addsub.setOnClickListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (App.mCity == null) {
                    showDialogNotify("Thông báo", "Bạn chưa chọn tỉnh/thành phố");
                    return;
                }
                if (App.mDistrict == null) {
                    showDialogNotify("Thông báo", "Bạn chưa chọn quận/huyện");
                    return;
                }
                if (App.mSchools == null) {
                    showDialogNotify("Thông báo", "Bạn chưa chọn trường của bé");
                    return;
                }
                if (App.mKhoihoc == null) {
                    showDialogNotify("Thông báo", "Bạn chưa chọn khối học của bé");
                    return;
                }
                if (edtClassName.getText().length() == 0) {
                    showDialogNotify("Thông báo", "Bạn chưa nhập vào tên lớp của bé");
                    return;
                } else sLop = edtClassName.getText().toString();
                sFullname = edtFullname.getText().toString().trim();
                if (sFullname.length() == 0) {
                    showDialogNotify("Thông báo", "Bạn chưa nhập vào tên của bé");
                    return;
                }
                sUser = edtUser.getText().toString().trim();
                if (sUser.length() == 0) {
                    showDialogNotify("Thông báo", "Bạn chưa nhập vào tên đăng nhập cho bé");
                    return;
                }
                if (!StringUtil.check_tiengviet(sUser)) {
                    showDialogNotify("Lỗi", "Tên đăng nhập phải là tiếng việt không dấu," +
                            " không chứa dấu cách và ký tự đặc biệt");
                    return;
                }
                sPass = edtPass.getText().toString().trim();
                if (edtPass.getText().length() == 0) {
                    showDialogNotify("Thông báo", "Bạn chưa nhập mật khẩu");
                    return;
                }
                if (!StringUtil.check_tiengviet(sPass)) {
                    showDialogNotify("Lỗi", "Mật khẩu phải là tiếng việt không dấu," +
                            " không chứa dấu cách và ký tự đặc biệt");
                    return;
                }
                if (App.mCity != null && App.mDistrict != null && App.mSchools != null && App.mKhoihoc != null) {
                    if (sUser.length() > 4 && sUser.indexOf(" ") < 0) {
                        if (sPass.length() > 7 && sPass.indexOf(" ") < 0) {
                            showDialogLoading();
                            String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                            mPresenter.api_create_children(sUserMe, App.mCity.getsID(), App.mDistrict.getsID(),
                                    App.mSchools.getsID(), App.mKhoihoc, "1", sLop, sFullname, sAvata, sUser, sPass);
                        } else
                            showDialogNotify("Thông báo",
                                    "Mật khẩu phải lớn hơn 8 ký tự, không chứa dấu cách và ký tự đặc biệt");
                    } else showDialogNotify("Thông báo",
                            "Tên đăng nhập tài khoản con phải dài hơn 5 ký tự," +
                                    " không chứa dấu cách và ký tự đặc biệt");
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStartLogin) {
                    Intent intent = new Intent(ActivityAddSubUser.this, ActivityHome.class);
                    startActivity(intent);
                    finish();
                } else finish();

            }
        });

    }

    boolean isStartLogin;

    private void initData() {
        String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        isStartLogin = getIntent().getBooleanExtra(Constants.KEY_IS_START_LOGIN, false);
        mPresenter.api_lis_children(sUserMe);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_district_addsub:
                if (App.mCity != null) {
                    App.mDistrict = null;
                    App.mSchools = null;
                    Intent intent = new Intent(ActivityAddSubUser.this, ActivityListDistrict.class);
                    intent.putExtra(Constants.KEY_SEND_CITY_ID, App.mCity.getsID());
                    startActivityForResult(intent, Constants.RequestCode.GET_DISTRICT);
                } else {
                    showDialogNotify(getString(R.string.notify), getString(R.string.notify_city_null));
                }

                break;
            case R.id.edt_city_addsub:
                App.mCity = null;
                App.mDistrict = null;
                Intent intent_city = new Intent(ActivityAddSubUser.this, ActivityListCity.class);
                startActivityForResult(intent_city, Constants.RequestCode.GET_CITY);
                break;
            case R.id.edt_schools_addsub:
                if (App.mDistrict != null) {
                    App.mSchools = null;
                    Intent intent_schools = new Intent(ActivityAddSubUser.this,
                            ActivityListSchools.class);
                    intent_schools.putExtra(Constants.KEY_SEND_DISTRICT_ID, App.mDistrict.getsID());
                    startActivityForResult(intent_schools, Constants.RequestCode.GET_SCHOOLS);
                } else {
                    showDialogNotify(getString(R.string.notify), getString(R.string.notify_city_null));
                }
                break;
            case R.id.edt_class_addsub:
                App.mKhoihoc = null;
                Intent intent_class = new Intent(ActivityAddSubUser.this,
                        ActivityDanhsachKhoihoc.class);
                startActivityForResult(intent_class, Constants.RequestCode.GET_KHOIHOC);
                break;
            case R.id.edt_year_addsub:
                App.mNamhoc = null;
                Intent intent_year = new Intent(ActivityAddSubUser.this,
                        ActivityDanhsachNamhoc.class);
                startActivityForResult(intent_year, Constants.RequestCode.GET_YEAR);
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
                    edtSchools.setText("");
                } else {
                    edtDistrict.setText("");
                    edtSchools.setText("");
                }

                break;
            case Constants.RequestCode.GET_CITY:
                if (App.mCity != null)
                    edtCity.setText(App.mCity.getsPROVINCE_NAME());
                else
                    edtCity.setText("");

                break;
            case Constants.RequestCode.GET_SCHOOLS:

                if (App.mSchools != null)
                    edtSchools.setText(App.mSchools.getsSCHOOL_NAME());
                else {
                    edtSchools.setText("");
                    edtSchools.setHint("Tên trường");
                }

                break;
            case Constants.RequestCode.GET_KHOIHOC:
                if (App.mKhoihoc != null)
                    edtClass.setText(App.mKhoihoc);
                else {
                    edtClass.setText("");
                    edtClass.setHint("Chọn khối học");
                }
                break;
            case Constants.RequestCode.GET_YEAR:
                if (App.mNamhoc != null)
                    edt_year_addsub.setText(App.mNamhoc);
                else {
                    edt_year_addsub.setText("");
                    edt_year_addsub.setHint("Chọn khối học");
                }
                break;
            case Constants.RequestCode.GET_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        IMAGE_PATH = ReadPathUtil.getPath(ActivityAddSubUser.this, data.getData());
                        File file = new File(IMAGE_PATH);
                        final Uri imageUri = data.getData();
                        File file1 = new File(imageUri.getPath());
                        Log.i(TAG, "onActivityResult: " + file1.toString());
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
                if (isStartLogin) {
                    SharedPrefs.getInstance().put(Constants.KEY_ISLOGIN, true);
                    Intent intent = new Intent(ActivityAddSubUser.this, ActivityDownloadAppKid.class);
                    intent.putExtra(Constants.KEY_SEND_ADD_ADDSUBUSER, true);
                    intent.putExtra(Constants.KEY_SEND_ADD_ADDSUBUSER, sUser);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
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

    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
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
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                btn_back.setVisibility(View.VISIBLE);
            } else {
                btn_back.setVisibility(View.GONE);
            }
        }
    }

}
