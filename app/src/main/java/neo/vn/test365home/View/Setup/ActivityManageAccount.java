package neo.vn.test365home.View.Setup;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ClickDialog;
import neo.vn.test365home.Models.ConfigChildren;
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
    String sUserMe;

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
        btn_naptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog =
                        new BottomSheetDialog(ActivityManageAccount.this);
                dialog.setContentView(R.layout.dialog_botton_setup_account);
                final TextView txt_naptien_tructuyen = dialog.findViewById(R.id.txt_naptien_tructuyen);
                TextView txt_naptien_sms_8655 = dialog.findViewById(R.id.txt_naptien_sms_8655);
                TextView txt_naptien_sms_9029 = dialog.findViewById(R.id.txt_naptien_sms_9029);
                TextView txt_naptien_the_test365 = dialog.findViewById(R.id.txt_naptien_the_test365);
                TextView txt_naptien_huybo = dialog.findViewById(R.id.txt_naptien_huybo);

                txt_naptien_tructuyen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent = new Intent(ActivityManageAccount.this, ActivityNaptienTructuyen.class);
                        if (sTkchinh.length() > 0 && sTkThuong.length() > 0) {
                            int iTkChinh = Integer.parseInt(sTkchinh);
                            int iTkThuong = Integer.parseInt(sTkThuong);
                            int iTkTotal = iTkChinh+iTkThuong;
                            intent.putExtra(Constants.KEY_SEND_TK_TOTAL_NAPTIEN, ""+iTkTotal);
                        }

                        startActivity(intent);
                    }
                });
                txt_naptien_sms_8655.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String s = "Để nạp 10.000đ vào tài khoản <b><font color='#3333CC'>" + sUserMe + "</font></b> " +
                                " Quý phụ hunh vui lòng soạn tin nhắn <b><font color='#FF0000'> 'Test365Home " + sUserMe +
                                " gửi đến 8655'.</font></b>\n \nGiá cước 10.000đ trên 1 tin nhắn. Xin cảm ơn";
                        showDialogComfirm("Thông báo", s, false, new ClickDialog() {
                            @Override
                            public void onClickYesDialog() {
                                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("address", "8655");
                                smsIntent.putExtra("sms_body", "Test365Home " + sUserMe);
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
                txt_naptien_sms_9029.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showDialogNotify("Thông báo", "Tính năng hiện tại đang phát triển");
                    }
                });
                txt_naptien_the_test365.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showDialogNotify("Thông báo", "Tính năng hiện tại đang phát triển");
                    }
                });
                txt_naptien_huybo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    private void initData() {
        showDialogLoading();
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.api_get_user_info(sUserMe);
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

    }

    String sTkchinh = "", sTkThuong = "";

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR() != null && mLis.get(0).getsERROR().equals("0000")) {
            UserInfo obj = mLis.get(0);
            if (obj.getsCORE_BALANCE() != null) {
                sTkchinh = obj.getsCORE_BALANCE();
                txt_main_account.setText(StringUtil.formatNumber(obj.getsCORE_BALANCE()));
            }
            if (obj.getsPROMOTION_BALANCE() != null) {
                sTkThuong = obj.getsPROMOTION_BALANCE();
                txt_bonus_account.setText(StringUtil.formatNumber(obj.getsPROMOTION_BALANCE()));
            }
            if (obj.getsFULLNAME() != null)
                txt_fullname.setText(obj.getsFULLNAME());
            if (obj.getsUSERNAME() != null)
                txt_username.setText(obj.getsUSERNAME());
            if (obj.getsEMAIL() != null)
                txt_email.setText(obj.getsEMAIL());
            if (obj.getsPHONENUMBER() != null)
                txt_phone.setText(obj.getsPHONENUMBER());
        }
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


}
