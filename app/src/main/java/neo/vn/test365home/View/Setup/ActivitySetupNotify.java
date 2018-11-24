package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterSetupNotify;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Listener.SwitchChangeListenner;
import neo.vn.test365home.Models.ConfigNotify;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ItemSetupNotify;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

public class ActivitySetupNotify extends BaseActivity implements ImpSetupNotify.View {
    private static final String TAG = "ActivitySetupNotify";
    @BindView(R.id.recycle_setup_notify_mother)
    RecyclerView recycle_setup_mother;
    @BindView(R.id.btn_update)
    Button btn_update;
    AdapterSetupNotify adapterMother;
    RecyclerView.LayoutManager mLayoutMother;
    List<ItemSetupNotify> mLisMother;
    List<ItemSetupNotify> mLisChil;
    PresenterSetupNotify mPresenter;
    String sUserMe;
    String ptaken_notify, plate_notify, pstart_notify, pend_notify, pstart_game_notify,
            pend_game_notify, pbuy_exe_notify;

    @Override
    public int setContentViewId() {
        return R.layout.activity_setup_notify;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterSetupNotify(this);
        initAppbar();
        initData();
        init();
        initEvent();
    }

    private void initEvent() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLisMother.get(0).isOnOff()) {
                    ptaken_notify = "1";
                } else ptaken_notify = "0";
                if (mLisMother.get(1).isOnOff()) {
                    plate_notify = "1";
                } else plate_notify = "0";
                if (mLisMother.get(2).isOnOff()) {
                    pstart_notify = "1";
                } else pstart_notify = "0";
                if (mLisMother.get(3).isOnOff()) {
                    pend_notify = "1";
                } else pend_notify = "0";
                if (mLisMother.get(4).isOnOff()) {
                    pstart_game_notify = "1";
                } else pstart_game_notify = "0";
                if (mLisMother.get(5).isOnOff()) {
                    pend_game_notify = "1";
                } else pend_game_notify = "0";
                if (mLisMother.get(6).isOnOff()) {
                    pbuy_exe_notify = "1";
                } else pbuy_exe_notify = "0";

                showDialogLoading();
                mPresenter.api_update_cf_notify(sUserMe, ptaken_notify, plate_notify, pstart_notify, pend_notify,
                        pstart_game_notify, pend_game_notify, pbuy_exe_notify);
            }
        });
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Cầu hình thông báo");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        mLisChil = new ArrayList<>();
        mLisMother = new ArrayList<>();
        mLisMother.add(new ItemSetupNotify("Thông báo nhắc làm bài tập",
                "Gửi thông báo khi đếm giờ làm bài tập của con mà bài tập đó chưa làm", false));
        mLisMother.add(new ItemSetupNotify("Thông báo nhắc làm bài tập muộn",
                "Gửi thông báo khi có bài tập muộn mà con chưa làm", false));
        mLisMother.add(new ItemSetupNotify("Thông báo con bắt đầu làm bài tập",
                "Gửi thông báo khi con bắt đầu vào làm bài tập", true));
        mLisMother.add(new ItemSetupNotify("Thông báo con làm bài tập xong",
                "Gửi thông báo khi con làm bài tập xong", true));
        mLisMother.add(new ItemSetupNotify("Thông báo khi con bắt đầu trò chơi",
                "Gửi thông báo khi con chơi một trò chơi bất kì", false));
        mLisMother.add(new ItemSetupNotify("Thông báo kết thúc trò chơi",
                "Gửi thông báo khi con kết thúc trò chơi kết quả và giải thưởng dành cho con", true));
        mLisMother.add(new ItemSetupNotify("Thông báo hết bài tập",
                "Thông báo khi bài tập mua cho con đã hết", true));
        mLisChil.add(new ItemSetupNotify("Thông báo nhắc làm bài tập",
                "Gửi thông báo khi đếm giờ làm bài tập của con mà bài tập đó chưa làm", true));
        mLisChil.add(new ItemSetupNotify("Thông báo nhắc làm bài tập muộn",
                "Gửi thông báo khi có bài tập muộn mà con chưa làm", true));
        mLisChil.add(new ItemSetupNotify("Thông báo mẹ tặng sticker",
                "Gửi thông báo khi mẹ gửi sticker cho con", true));
        mLisChil.add(new ItemSetupNotify("Thông báo mẹ đã tải bài tập",
                "Gửi thông báo khi mẹ thông báo mẹ mua bài tập cho con", true));
        showDialogLoading();
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.api_get_get_cf_notify(sUserMe);
    }

    private void init() {

        mLayoutMother = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapterMother = new AdapterSetupNotify(mLisMother, this, new SwitchChangeListenner() {
            @Override
            public void onListennerSwitchChange(int position, boolean isChecked) {
                mLisMother.get(position).setOnOff(isChecked);
            }
        });


        recycle_setup_mother.setNestedScrollingEnabled(false);
        recycle_setup_mother.setHasFixedSize(true);
        recycle_setup_mother.setLayoutManager(mLayoutMother);
        recycle_setup_mother.setItemAnimator(new DefaultItemAnimator());
        recycle_setup_mother.setAdapter(adapterMother);

        adapterMother.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
               /* boolean isClick = mLisMother.get(position).isOnOff();
                mLisMother.get(position).setOnOff(!isClick);
                adapterMother.notifyDataSetChanged();*/
            }
        });


    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
        showDialogNotify("Lỗi", "Lỗi kết nối");
    }

    @Override
    public void show_get_cf_notify(List<ConfigNotify> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            ConfigNotify obj = mLis.get(0);
            if (obj.getsTAKEN_NOTIFY().equals("1")) {
                mLisMother.get(0).setOnOff(true);
            } else {
                mLisMother.get(0).setOnOff(false);
            }
            if (obj.getsLATE_NOTIFY().equals("1")) {
                mLisMother.get(1).setOnOff(true);
            } else {
                mLisMother.get(1).setOnOff(false);
            }
            if (obj.getsSTART_NOTIFY().equals("1")) {
                mLisMother.get(2).setOnOff(true);
            } else {
                mLisMother.get(2).setOnOff(false);
            }
            if (obj.getsEND_NOTIFY().equals("1")) {
                mLisMother.get(3).setOnOff(true);
            } else {
                mLisMother.get(3).setOnOff(false);
            }
            if (obj.getsSTART_GAME_NOTIFY().equals("1")) {
                mLisMother.get(4).setOnOff(true);
            } else {
                mLisMother.get(4).setOnOff(false);
            }
            if (obj.getsEND_GAME_NOTIFY().equals("1")) {
                mLisMother.get(5).setOnOff(true);
            } else {
                mLisMother.get(5).setOnOff(false);
            }
            if (obj.getsBUY_EXE_NOTIFY().equals("1")) {
                mLisMother.get(6).setOnOff(true);
            } else {
                mLisMother.get(6).setOnOff(false);
            }
            Log.i(TAG, "show_get_cf_notify: " + mLisMother);
            adapterMother.notifyDataSetChanged();
        }
    }

    @Override
    public void show_update_cf_notify(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mPresenter.api_get_get_cf_notify(sUserMe);
            Toast.makeText(this, mLis.get(0).getsRESULT(), Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, mLis.get(0).getsRESULT(), Toast.LENGTH_SHORT).show();
    }

}
