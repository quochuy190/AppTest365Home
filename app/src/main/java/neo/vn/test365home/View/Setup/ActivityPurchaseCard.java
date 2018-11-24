package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
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
import neo.vn.test365home.Untils.KeyboardUtil;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivityPurchaseCard extends BaseActivity implements ImpPayCard.View {
    @BindView(R.id.edt_card_input)
    EditText edt_card_input;
    @BindView(R.id.btn_purchase)
    Button btn_purchase;
    PresenterPayCard mPresenter;

    @Override
    public int setContentViewId() {
        return R.layout.activity_purchase_card;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeyboardUtil.requestKeyboard(this, R.id.edt_card_input);
        mPresenter = new PresenterPayCard(this);
        btn_purchase.getBackground().setAlpha(50);
        btn_purchase.setEnabled(false);
        initEvent();
        initAppbar();
    }
    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("NẠP THẺ HOME365");
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
                if (s.length() > 13) {
                    btn_purchase.getBackground().setAlpha(225);
                    btn_purchase.setEnabled(true);
                } else {
                    btn_purchase.getBackground().setAlpha(50);
                    btn_purchase.setEnabled(false);
                }
                if (lock || s.length() > 14) {
                    return;
                }
                lock = true;
                for (int i = 4; i < s.length(); i += 5) {
                    if (s.toString().charAt(i) != ' ') {
                        s.insert(i, " ");
                    }
                }
                lock = false;
            }
        });
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sCodeNumber = edt_card_input.getText().toString().trim().replaceAll(" ", "");
                String sUserMe  = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                showDialogLoading();
                mPresenter.api_pay_card(sUserMe, sCodeNumber);

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
        if (mList != null && mList.get(0).getsERROR().equals("0000")) {
            Toast.makeText(this, mList.get(0).getsRESULT(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            showDialogNotify("Thông báo", mList.get(0).getsRESULT());
        }
    }

    @Override
    public void show_refcode(List<HistoryBalance> mList) {

    }
}
