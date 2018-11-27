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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterBXH;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Chart_To_Subject;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;


public class FragmentYearChart extends BaseFragment implements ImpThongke.View {
    public static FragmentYearChart fragment;
    String sUserMe, sUserCon, sDate;
    @BindView(R.id.recycle_bangxephang)
    RecyclerView recycle_bangxephang;
    AdapterBXH mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<BXH> mList;
    Childrens mChildren;
    PresenterThongke mPresenter;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_speed_time)
    TextView txt_speed_time;
    @BindView(R.id.txt_point)
    TextView txt_point;
    @BindView(R.id.txt_rank)
    TextView txt_stt;
    @BindView(R.id.txt_bxh_notify)
    TextView txt_bxh_notify;
    @BindView(R.id.rl_myrank)
    RelativeLayout rl_myrank;
    boolean isNotChart = false;

    public static synchronized FragmentYearChart getInstance() {
        if (fragment == null) {
            fragment = new FragmentYearChart();
        }
        return (fragment);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bangxephang, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterThongke(this);
        init();
        initData();
        return view;
    }

    private void initData() {
        mChildren = SharedPrefs.getInstance().get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
        sUserCon = mChildren.getsUSERNAME();
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        sDate = StringUtil.get_current_time();
        mPresenter.api_get_bxh_year_chart(sUserMe, sUserCon, sDate);

    }

    private void init() {
        mList = new ArrayList<>();
        mAdapter = new AdapterBXH(mList, getContext());
        mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recycle_bangxephang.setNestedScrollingEnabled(false);
        recycle_bangxephang.setHasFixedSize(true);
        recycle_bangxephang.setLayoutManager(mLayoutManager);
        recycle_bangxephang.setItemAnimator(new DefaultItemAnimator());
        recycle_bangxephang.setAdapter(mAdapter);

        mAdapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });
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
     /*   if (mLis!=null&&mLis.get(0).getsERROR().equals("0000")){
            mList.clear();
            mList.addAll(mLis);
            mAdapter.notifyDataSetChanged();
        }else showDialogNotify("Thông báo", "Lấy dữ liệu không thành công");*/
    }

    @Override
    public void show_bxh_month_chart(List<BXH> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_bxh_year_chart(List<BXH> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            for (int i = 0; i < mLis.size(); i++) {
                BXH obj = mLis.get(i);
                if (obj.getsUSERNAME().equals(sUserCon)) {
                    isNotChart = true;
                    if (obj.getsFULLNAME() != null && obj.getsFULLNAME().length() > 0)
                        txt_name.setText("Họ tên: " + obj.getsFULLNAME());
                   /* if (obj.getsSCHOOL_NAME() != null && obj.getsSCHOOL_NAME().length() > 0)
                        txt_school.setText(obj.getsSCHOOL_NAME());
                    if (obj.getsLEVEL_NAME() != null && obj.getsLEVEL_NAME().length() > 0)
                        txt_class.setText(obj.getsLEVEL_NAME());*/
                    if (obj.getsSPEED() != null && obj.getsSPEED().length() > 0)
                        txt_speed_time.setText("Tốc độ làm bài: " + obj.getsSPEED());
                    if (obj.getsDTB() != null && obj.getsDTB().length() > 0)
                        txt_point.setText(StringUtil.format_point(Float.parseFloat(obj.getsDTB())));
                    txt_stt.setText("" + (i + 1));
                }
            }
            if (isNotChart) {
                rl_myrank.setVisibility(View.VISIBLE);
                txt_bxh_notify.setVisibility(View.GONE);
            } else {
                rl_myrank.setVisibility(View.GONE);
                txt_bxh_notify.setVisibility(View.VISIBLE);
            }
            mList.clear();
            mList.addAll(mLis);
            mAdapter.notifyDataSetChanged();
        }else if (mLis != null && mLis.get(0).getsERROR().equals("0001")) {
            rl_myrank.setVisibility(View.GONE);
            txt_bxh_notify.setVisibility(View.VISIBLE);
            txt_bxh_notify.setText("Năm mới chưa có bảng xếp hạng, bạn hãy làm đủ 3 bài tập trong tuần để có mặt trên bảng xếp hạng");
        }
    }


}
