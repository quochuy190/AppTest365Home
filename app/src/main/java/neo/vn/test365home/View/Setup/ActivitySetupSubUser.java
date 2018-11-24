package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import neo.vn.test365home.Adapter.AdapterListChildrenSetup;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.View.Baitap.ImpBaitap;
import neo.vn.test365home.View.Baitap.PresenterBaitap;
import neo.vn.test365home.View.Longin.ActivityAddSubUser;

public class ActivitySetupSubUser extends BaseActivity implements ImpBaitap.View {
    @Override
    public int setContentViewId() {
        return R.layout.activity_setup_sub_user;
    }

    RecyclerView.LayoutManager mLayoutManager;
    private List<Childrens> mLisChildren;
    @BindView(R.id.recycle_list_subuser)
    RecyclerView recyleChildren;
    @BindView(R.id.btn_add_subuser)
    Button btn_add_subuser;
    AdapterListChildrenSetup adapter;
    PresenterBaitap mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterBaitap(this);
        initLisChildren();
        initAppbar();
        initData();
        initEvent();
    }

    private void initEvent() {
        btn_add_subuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySetupSubUser.this, ActivityAddSubUser.class);
                startActivityForResult(intent, Constants.RequestCode.GET_ADD_SUBUSER);
            }
        });
    }

    private void initData() {
        showDialogLoading();
        String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.get_api_list_get_children(user);
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Danh sách tài khoản con");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initLisChildren() {
        mLisChildren = new ArrayList<>();
     /*   if (App.mLisChildren != null)
            mLisChildren.addAll(App.mLisChildren);*/
        adapter = new AdapterListChildrenSetup(mLisChildren, this);
        mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyleChildren.setHasFixedSize(true);
        recyleChildren.setNestedScrollingEnabled(false);
        recyleChildren.setLayoutManager(mLayoutManager);
        recyleChildren.setItemAnimator(new DefaultItemAnimator());
        recyleChildren.setAdapter(adapter);

        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                Childrens obj = (Childrens) item;
                //   Intent intent = new Intent(ActivitySetupSubUser.this, ActivityAddSubUser.class);
                Intent intent = new Intent(ActivitySetupSubUser.this, ActivityConfigSubUser.class);
                App.mChilSetup = obj;
                startActivityForResult(intent, Constants.RequestCode.GET_UPDATE_USER_CON);
                // startActivity(intent);
            }
        });
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_children(List<Childrens> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mLisChildren.clear();
            mLisChildren.addAll(mLis);
            adapter.notifyDataSetChanged();
        } else showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
    }

    @Override
    public void show_list_week_test(List<ObjTuanhoc> mList) {

    }

    @Override
    public void show_list_buy_excercise(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_list_buy(List<TuanDamua> mLis) {

    }

    @Override
    public void show_list_des_excercise(List<ExcerciseDetail> mLis) {

    }

    @Override
    public void show_list_get_part(List<Cauhoi> mLis) {

    }

    @Override
    public void show_list_report_excercise(List<ExcerciseDetail> mLis) {

    }

    @Override
    public void show_list_get_sticker(List<Sticker> mLis) {

    }

    @Override
    public void show_list_gift_sticker(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_mt_comment(List<ErrorApi> mLis) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GET_ADD_SUBUSER) {
            if (resultCode == RESULT_OK) {
                initData();
            }
        }
        if (requestCode == Constants.RequestCode.GET_UPDATE_USER_CON) {
            if (resultCode == RESULT_OK) {
                initData();
            }
        }
    }
}
