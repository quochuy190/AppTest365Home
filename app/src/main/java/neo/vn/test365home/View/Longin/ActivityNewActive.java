package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.R;

public class ActivityNewActive extends BaseActivity {
    @BindView(R.id.btn_taotk)
    Button btn_taotk;
    @BindView(R.id.img_background)
    ImageView img_background;

    @Override
    public int setContentViewId() {
        return R.layout.activity_new_active;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.img_bg_start_app).into(img_background);
        initEvent();
    }

    private void initEvent() {
        btn_taotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityNewActive.this, ActivityAddSubUser.class);
                intent.putExtra(Constants.KEY_IS_START_LOGIN, true);
                startActivity(intent);
                finish();
            }
        });
    }
}
