package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivityFeedback extends BaseActivity implements ImpFeedback.View {
    PresenterFeedBack mPresenter;
    @BindView(R.id.edt_content)
    EditText edt_content;
    @BindView(R.id.btn_send)
    Button btn_send;

    @Override
    public int setContentViewId() {
        return R.layout.activity_feedback;
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText(getString(R.string.txt_feedback_app));
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        mPresenter = new PresenterFeedBack(this);
        edt_content.setHint(" - Nhập vào ý kiến đóng góp của bạn");
        initEvent();
    }

    private void initEvent() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()) {
                    String sContent = edt_content.getText().toString();
                    if (sContent.length() == 0) {
                        edt_content.requestFocus();
                        edt_content.setHint("*Bạn chưa nhập vào nhận xét");
                        edt_content.setHintTextColor(getResources().getColor(R.color.red_mo));
                    } else {
                        String sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                        showDialogLoading();
                        mPresenter.api_add_feedback_app(sUserMe, sContent);
                    }
                }
            }
        });
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();

    }

    @Override
    public void show_add_feedback_app(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis!=null&&mLis.get(0).getsERROR().equals("0000")){
            edt_content.setText("");
            showAlertDialog("Thông báo", "Cảm ơn bạn đã gửi ý kiến đóng góp cho chúng tôi," +
                    " Chúng tôi sẽ tiếp nhận và xử lý sớm nhất");
        }
    }
}
