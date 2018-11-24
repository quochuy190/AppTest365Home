package neo.vn.test365home.View.Thongke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterBXH;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Chart_To_Subject;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

public class ActivityBangXepHang extends BaseFragment implements ImpThongke.View {
    @BindView(R.id.recycle_bangxephang)
    RecyclerView recycle_bangxephang;
    AdapterBXH mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<BXH> mList;
    String sUserMe, sUserCon, sDate;
    Childrens mChildren;
    PresenterThongke mPresenter;
  /*  @Override
    public int setContentViewId() {
        return R.layout.activity_bangxephang;
    }*/

    /*  @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          mPresenter = new PresenterThongke(this);
          init();
          initData();
      }*/
    public static ActivityBangXepHang fragment;

    public static synchronized ActivityBangXepHang getInstance() {
        if (fragment == null) {
            fragment = new ActivityBangXepHang();
        }
        return (fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bangxephang, container, false);
        init();
        initData();
        return view;
    }

    private void initData() {
        mChildren = SharedPrefs.getInstance().get(Constants.KEY_SEND_CHILDREN, Childrens.class);
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        sDate = StringUtil.get_current_time();
        showDialogLoading();
        mPresenter.api_get_bxh_week_chart(sUserMe, sUserCon, sDate);

    }

    private void init() {
        mList = new ArrayList<>();
        mAdapter = new AdapterBXH(mList, getContext());
      //  mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL, false);
        recycle_bangxephang.setNestedScrollingEnabled(false);
        recycle_bangxephang.setHasFixedSize(true);
        recycle_bangxephang.setLayoutManager(mLayoutManager);
        recycle_bangxephang.setItemAnimator(new DefaultItemAnimator());
        recycle_bangxephang.setAdapter(mAdapter);

    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_chart_to_subject_all(List<Chart_To_Subject> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_chart_to_subject(List<Chart_To_Subject> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_bxh_week_chart(List<BXH> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_bxh_month_chart(List<BXH> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_bxh_year_chart(List<BXH> mLis) {
        hideDialogLoading();
    }
}
