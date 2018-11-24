package neo.vn.test365home.View.Longin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Config;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.ReadPathUtil;
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
public class ActivityUpdateInfo extends BaseActivity implements ImpLogin.View, ImpUploadImage.View {
    String sUserName, sPhone = "", sEmail = "", sAvata = "", sFullName = "";
    @BindView(R.id.edt_fullname_update)
    EditText edtFullname;
    @BindView(R.id.edt_phone_update)
    EditText edtPhone;
    @BindView(R.id.edt_email_update)
    EditText edtEmail;
    @BindView(R.id.txt_code)
    TextView txt_code;
    @BindView(R.id.img_avata_update)
    CircleImageView imgAvata;
    @BindView(R.id.btn_back_update)
    Button btnBack;
    @BindView(R.id.img_back)
    Button btnBack_appbar;
    @BindView(R.id.btn_ok_update)
    Button btnOkie;
    PresenterLogin mPresenter;
    Login mLogin;
    boolean isStartLogin;
    @BindView(R.id.img_update_avata)
    ImageView img_update_avata;
    @BindView(R.id.img_back)
    ImageView img_back;
    PresenterUploadImage mPresenterUploadImage;

    @Override
    public int setContentViewId() {
        return R.layout.activity_update_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isThongbaoAvata = false;
        checkPermistion();
        mPresenter = new PresenterLogin(this);
        mPresenterUploadImage = new PresenterUploadImage(this);
        initData();
        initEvent();
    }

    private void getImageFromAlbum() {
        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, Constants.RequestCode.GET_IMAGE);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
    }

    boolean isThongbaoAvata = false;

    private void initEvent() {
        btnBack_appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_update_avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* MultiImageSelector.create()
                        .single() // single mode
                        .start(ActivityUpdateInfo.this, Constants.RequestCode.GET_IMAGE);*/
                getImageFromAlbum();
            }
        });
        btnOkie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtFullname.getText().toString().length() > 0) {
                    sFullName = edtFullname.getText().toString().trim();
                }
                if (edtPhone.getText().toString().length() > 0) {
                    sPhone = edtPhone.getText().toString().trim();
                }
                if (edtEmail.getText().toString().length() > 0) {
                    sEmail = edtEmail.getText().toString().trim();
                }
                if (!(sAvata.length() > 0)) {
                    if (!isThongbaoAvata) {
                        showDialogNotify(getString(R.string.notify),
                                "Hình ảnh của quý Phụ huynh sẽ xuất hiện để động viên con mỗi khi gặp khó khăn. " +
                                        "Ba mẹ nên cập nhật ảnh đại diện để hình ảnh gần gũi hơn.");
                        isThongbaoAvata = true;
                    }

                    return;
                }
                if (sFullName.length() > 0 || sPhone.length() > 0 || sEmail.length() > 0 || sAvata.length() > 0) {
                    sUserName = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                    mPresenter.api_update_info(sUserName, sFullName, sPhone, sEmail, sAvata);
                } else
                    showDialogNotify(getString(R.string.notify),
                            "Quý phụ huynh chưa nhập vào thông tin nào để cập nhật");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStartLogin) {
                    Intent intent = new Intent(ActivityUpdateInfo.this, ActivityAddSubUser.class);
                    intent.putExtra(Constants.KEY_IS_START_LOGIN, true);
                    startActivity(intent);
                }
                finish();
              /*  Intent intent = new Intent(ActivityUpdateInfo.this, ActivityAddSubUser.class);
                // intent.putExtra(Constants.KEY_REGISTER_SUCCESS, true);
                startActivity(intent);
                finish();*/
            }
        });
    }

    private void initData() {
        mLogin = SharedPrefs.getInstance().get(Constants.KEY_LOGININFO, Login.class);
        if (mLogin != null) {
            if (mLogin.getsAVARTAR() != null && mLogin.getsAVARTAR().length() > 0) {
                sAvata = mLogin.getsAVARTAR();
                Glide.with(this)
                        .load(Config.URL_IMAGE + mLogin.getsAVARTAR())
                        .placeholder(R.drawable.avatar_default)
                        .into(imgAvata);
                //  Glide.with(this).load(Config.URL_IMAGE + mLogin.getsAVARTAR()).into(imgAvata);
            } else {
                Glide.with(this).load(R.drawable.avatar_default).into(imgAvata);
            }

            if (mLogin.getsFULLNAME() != null) {
                edtFullname.setText(mLogin.getsFULLNAME());
            }
            if (mLogin.getsPHONENUMBER() != null) {
                edtPhone.setText(mLogin.getsPHONENUMBER());
            }
            if (mLogin.getsEMAIL() != null) {
                edtEmail.setText(mLogin.getsEMAIL());
            }
            if (mLogin.getsPROMOTIONCODE() != null) {
                txt_code.setText(mLogin.getsPROMOTIONCODE());
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
                mLogin.setsFULLNAME(edtFullname.getText().toString());
                mLogin.setsEMAIL(edtEmail.getText().toString());
                mLogin.setsPHONENUMBER(edtPhone.getText().toString());
                mLogin.setsAVARTAR(sAvata);
                SharedPrefs.getInstance().put(Constants.KEY_LOGININFO, mLogin);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                if (isStartLogin) {
                    Intent intent = new Intent(ActivityUpdateInfo.this, ActivityAddSubUser.class);
                    startActivity(intent);
                }
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
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
    public void show_upload_image(String sUrlImage) {
        if (sUrlImage != null && sUrlImage.length() > 0) {
            sAvata = sUrlImage;
        }
    }

    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(ActivityUpdateInfo.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityUpdateInfo.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(ActivityUpdateInfo.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

    @Override
    public void show_lis_children(List<Childrens> mLis) {

    }

    String IMAGE_PATH = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                IMAGE_PATH = ReadPathUtil.getPath(ActivityUpdateInfo.this, data.getData());
                File file = new File(IMAGE_PATH);
                final Uri imageUri = data.getData();
                File file1 = new File(imageUri.getPath());
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgAvata.setImageBitmap(selectedImage);
                mPresenterUploadImage.api_upload_image(IMAGE_PATH);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
       /* break;
        if (requestCode == Constants.RequestCode.GET_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    final List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    if (path != null && path.size() > 0) {
                        IMAGE_PATH = path.get(0);
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(path.get(0), options);
                    imgAvata.setImageBitmap(bitmap);
                    mPresenterUploadImage.api_upload_image(IMAGE_PATH);
                    *//*IMAGE_PATH = ReadPathUtil.getPath(ActivityAddSubUser.this, data.getData());
                    File file = new File(IMAGE_PATH);
                    final Uri imageUri = data.getData();
                    File file1 = new File(imageUri.getPath());
                    Log.i(TAG, "onActivityResult: " + file1.toString());
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);*//*
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }*/
    }
}
