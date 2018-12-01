package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterHistoryBalance;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

public class ActivityManageAccount extends BaseActivity implements ImpSetup.View {
    @BindView(R.id.txt_account_main)
    TextView txt_main_account;
    @BindView(R.id.txt_bonus_account)
    TextView txt_bonus_account;
    @BindView(R.id.btn_naptien)
    Button btn_naptien;
    PresenterSetup mPresenter;
    String sUserMe;
    AdapterHistoryBalance adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<HistoryBalance> mLisHistory;
    @BindView(R.id.recycle_history)
    RecyclerView recyclerviewHistory;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;


    @Override
    public int setContentViewId() {
        return R.layout.activity_account_manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterSetup(this);
        init();
        initAppbar();
        initData();
        initEvent();

    }


    private void init() {
        mLisHistory = new ArrayList<>();
        adapter = new AdapterHistoryBalance(mLisHistory, this);
       /* mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);*/
        mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerviewHistory.setHasFixedSize(true);
        recyclerviewHistory.setLayoutManager(mLayoutManager);
        recyclerviewHistory.setNestedScrollingEnabled(false);
        recyclerviewHistory.setItemAnimator(new DefaultItemAnimator());
        recyclerviewHistory.setAdapter(adapter);
        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });

    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("SỐ DƯ VÀ NẠP TIỀN");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initEvent() {

        btn_naptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManageAccount.this, ActivityMenuNapPurchase.class);
                startActivityForResult(intent, Constants.RequestCode.GET_PURCHASE);
            }
        });
    }


    private void initData() {
        if (isNetwork()){
            showDialogLoading();
            sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
            mPresenter.api_get_user_info(sUserMe);
            mPresenter.api_get_history_balance(sUserMe);
        }
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

    }

    String sTkchinh = "", sTkThuong = "";

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR() != null && mLis.get(0).getsERROR().equals("0000")) {
            UserInfo obj = mLis.get(0);
            if (obj.getsCORE_BALANCE() != null) {
                sTkchinh = obj.getsCORE_BALANCE();
                txt_main_account.setText(StringUtil.formatNumber(obj.getsCORE_BALANCE()));
            }
            if (obj.getsPROMOTION_BALANCE() != null) {
                sTkThuong = obj.getsPROMOTION_BALANCE();
                txt_bonus_account.setText(StringUtil.formatNumber(obj.getsPROMOTION_BALANCE()));
            }
        }
    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_get_config_children(List<ConfigChildren> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_payment(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_change_pass(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_get_info_chil(List<Childrens> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_update_info_chil(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_get_history_balance(List<HistoryBalance> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mLisHistory.clear();
            mLisHistory.addAll(mLis);
            adapter.notifyDataSetChanged();
            // nestedScrollView.fullScroll(View.FOCUS_UP);
            nestedScrollView.scrollTo(0, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GET_PURCHASE && resultCode == RESULT_OK) {
            initData();
        }
    }


}
