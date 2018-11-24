package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.R;

public class ActivityThanksWelcome extends BaseActivity {
    @BindView(R.id.btn_start_thanks)
    Button btn_start_thanks;
    @BindView(R.id.img_background)
    ImageView img_background;

    @Override
    public int setContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.img_bg_start_app).into(img_background);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_start_thanks.setVisibility(View.VISIBLE);
            }
        }, 3000);

        btn_start_thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityThanksWelcome.this, ActivityRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
