package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivityPurchaseCode extends BaseActivity implements ImpPayCard.View {
    @BindView(R.id.btn_share)
    Button btn_share;
    @BindView(R.id.btn_purchase)
    Button btn_purchase;
    @BindView(R.id.edt_card_input)
    EditText edt_card_input;
    String id;
    PresenterPayCard mPresenter;

    @Override
    public int setContentViewId() {
        return R.layout.activity_purchase_code;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterPayCard(this);
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        btn_purchase.getBackground().setAlpha(50);
        btn_purchase.setEnabled(false);
        initAppbar();
        initEvent();
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("MÃ GIỚI THIỆU");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEvent() {
        edt_card_input.addTextChangedListener(new TextWatcher() {
            private boolean lock;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 5) {
                    btn_purchase.getBackground().setAlpha(225);
                    btn_purchase.setEnabled(true);
                } else {
                    btn_purchase.getBackground().setAlpha(50);
                    btn_purchase.setEnabled(false);
                }
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /*Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Home365");
                    String sAux = "\nTải Home365 ngay nhập mã để nhận đc 100k vào tài khoản\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=neo.vn.test365home \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));*/
                    String message = "Home365 ngay nhập mã để nhận đc 100k vào tài khoản.";
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, message);
                    startActivity(Intent.createChooser(share, "Home365"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sCodeNumber = edt_card_input.getText().toString().trim().replaceAll(" ", "");
                String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                showDialogLoading();
                mPresenter.api_refcode(sUserMe, sCodeNumber, id);
            }
        });
    }

    @Override
    public void show_error_api(List<ErrorApi> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_pay_card(List<HistoryBalance> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_refcode(List<HistoryBalance> mList) {
        hideDialogLoading();
        if (mList != null && mList.get(0).getsERROR().equals("0000")) {
            Toast.makeText(this, mList.get(0).getsRESULT(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            showDialogNotify("Thông báo", mList.get(0).getsRESULT());
        }
    }
}
