package neo.vn.test365home.View.Setup;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivityMenuNapPurchase extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_purchase_sms)
    RelativeLayout rl_purchase_sms;
    @BindView(R.id.rl_purchase_banking)
    RelativeLayout rl_purchase_banking;
    @BindView(R.id.rl_purchase_visa)
    RelativeLayout rl_purchase_visa;
    @BindView(R.id.rl_purchase_card)
    RelativeLayout rl_purchase_card;
    @BindView(R.id.rl_purchase_promotion_code)
    RelativeLayout rl_purchase_promotion_code;
    String sUserMe;
    String sTkchinh = "", sTkThuong = "";

    @Override
    public int setContentViewId() {
        return R.layout.activity_naptien;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        rl_purchase_sms.setOnClickListener(this);
        rl_purchase_banking.setOnClickListener(this);
        rl_purchase_visa.setOnClickListener(this);
        rl_purchase_card.setOnClickListener(this);
        rl_purchase_promotion_code.setOnClickListener(this);
    }

    private void initData() {
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Nạp tiền");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_purchase_sms:
                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "8655");
                smsIntent.putExtra("sms_body", "Home365 NAP " + sUserMe);
                try {
                    startActivity(smsIntent);
                } catch (ActivityNotFoundException e) {
                    // Display some sort of error message here.
                }
                break;
            case R.id.rl_purchase_visa:
                Intent intent = new Intent(ActivityMenuNapPurchase.this, ActivityNaptienTructuyen.class);
                //intent.putExtra(Constants.KEY_SEND_TK_TOTAL_NAPTIEN, "" + iTkTotal);
                startActivity(intent);
                break;
            case R.id.rl_purchase_card:
                Intent intent2 = new Intent(ActivityMenuNapPurchase.this, ActivityPurchaseCard.class);
                startActivityForResult(intent2, Constants.RequestCode.GET_PURCHASE);
                break;
            case R.id.rl_purchase_promotion_code:
                Intent intent3 = new Intent(ActivityMenuNapPurchase.this, ActivityPurchaseCode.class);
                startActivityForResult(intent3, Constants.RequestCode.GET_PURCHASE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GET_PURCHASE && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
