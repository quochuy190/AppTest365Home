package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hotchemi.stringpicker.StringPicker;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ClickDialogPicker;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

public class ActivityNaptienTructuyen extends BaseActivity implements ImpSetup.View {
    @BindView(R.id.txt_value_monney)
    TextView txt_value_monney;
    @BindView(R.id.btn_start_naptien)
    TextView btn_start_naptien;
    PresenterSetup mPresenter;
    List<String> mLisMonney;
    @BindView(R.id.textView2)
    TextView txt_total_tk;

    @Override
    public int setContentViewId() {
        return R.layout.activity_naptien_tructuyen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterSetup(this);
        mLisMonney = new ArrayList<>();
        initAppbar();
        initData();
        initEvent();
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Nạp tiền trực tuyến");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String sUserMe;

    private void initEvent() {
        txt_value_monney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisMonney, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_value_monney.setText(sResult);
                    }
                });
            }
        });
        btn_start_naptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                String sMonney = txt_value_monney.getText().toString();
                sMonney = sMonney.replaceAll("\\.", "");
                sMonney = sMonney.replaceAll("VND", "");
                sMonney.trim();
                showDialogLoading();
                mPresenter.api_payment(sUserMe, sMonney);
            }
        });
    }

    private void initData() {
        String tk = getIntent().getStringExtra(Constants.KEY_SEND_TK_TOTAL_NAPTIEN);
        txt_total_tk.setText(StringUtil.formatNumber(tk));
        mLisMonney.add("50.000 VND");
        mLisMonney.add("100.000 VND");
        mLisMonney.add("200.000 VND");
        mLisMonney.add("300.000 VND");
        mLisMonney.add("400.000 VND");
        mLisMonney.add("500.000 VND");
        mLisMonney.add("1.000.000 VND");

    }

    public void show_pickerdialog(List<String> mLisString, final ClickDialogPicker clickDialog) {

        final BottomSheetDialog dialog =
                new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_string_picker);

        final StringPicker stringPicker = (StringPicker) dialog.findViewById(R.id.string_picker);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_ok);
        stringPicker.setValues(mLisString);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialog.onClickYesDialog(stringPicker.getCurrentValue());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

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
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            Intent intent = new Intent(ActivityNaptienTructuyen.this, ActivityWebviewNaptien.class);
            intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW_NAPTIEN, mLis.get(0).getsURL());
            startActivity(intent);
        }
    }
}
