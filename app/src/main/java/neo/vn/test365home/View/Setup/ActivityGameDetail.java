package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Game;
import neo.vn.test365home.R;

public class ActivityGameDetail extends BaseActivity {
    Game objGame;
    @BindView(R.id.txt_content)
    TextView txt_content;
    @BindView(R.id.img_avata)
    ImageView img_avata;

    @Override
    public int setContentViewId() {
        return R.layout.activity_detail_game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();

    }

    private void initData() {
        objGame = (Game) getIntent().getSerializableExtra(Constants.KEY_SEND_GAME_DETAIL);
        if (objGame != null && objGame.getsName() != null)
            txt_title.setText(objGame.getsName());
        if (objGame != null)
            txt_content.setText(getString(objGame.getiContent()));
        Glide.with(this).load(objGame.getiAvata()).into(img_avata);
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);

        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
