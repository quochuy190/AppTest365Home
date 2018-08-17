package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.R;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/30/2018
 * @updated 7/30/2018
 * @modified by
 * @updated on 7/30/2018
 * @since 1.0
 */
public class ActivityStart extends BaseActivity {
    @BindView(R.id.btn_dangnhap_Login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    @Override
    public int setContentViewId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityStart.this, ActivityLogin.class));
                finish();
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityStart.this, ActivityRegister.class));
                finish();
            }
        });
    }
}
