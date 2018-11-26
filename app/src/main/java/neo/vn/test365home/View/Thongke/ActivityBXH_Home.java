package neo.vn.test365home.View.Thongke;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import neo.vn.test365home.Adapter.AdapterListChildren;
import neo.vn.test365home.Adapter.AdapterViewpager;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.View.Baitap.ImpBaitap;
import neo.vn.test365home.View.Baitap.PresenterBaitap;


public class ActivityBXH_Home extends BaseActivity implements View.OnClickListener, ImpBaitap.View {
    public static ActivityBXH_Home fragment;

    public static synchronized ActivityBXH_Home getInstance() {
        if (fragment == null) {
            fragment = new ActivityBXH_Home();
        }
        return (fragment);
    }

    PresenterBaitap mPresenter;
    AdapterListChildren adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<Childrens> mLisChildren;

    public ViewPager viewPager;
    public AdapterViewpager adapterViewpager;
    public TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterBaitap(this);
        viewPager = findViewById(R.id.viewpage_bxh_home);
        tabLayout = findViewById(R.id.tab_layout_bxh_home);
        setupViewPager();
        initAppbar();
        initData();
        initEvent();
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Bảng xếp hạng");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public int setContentViewId() {
        return R.layout.fragment_bxh_home;
    }

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baitap, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterBaitap(this);
        viewPager = view.findViewById(R.id.viewpager_flight_home);
        tabLayout = view.findViewById(R.id.tab_layout);
        initLisChildren();
        initData();
        initEvent();
        //setupViewPager();
        return view;
    }*/


    private void initData() {

    }


    private void initEvent() {

    }


    @Override
    public void onClick(View view) {

    }

    private void click(AppCompatActivity appCompatActivity) {

    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_children(List<Childrens> mLis) {

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

    private void setupViewPager() {
        adapterViewpager = new AdapterViewpager(getSupportFragmentManager());
        adapterViewpager.addFragment(new FragmentWeekChart(), "BXH Tuần");
        adapterViewpager.addFragment(new FragmentMonthChart(), "BXH Tháng");
        adapterViewpager.addFragment(new FragmentYearChart(), "BXH Năm");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapterViewpager);
        tabLayout.setupWithViewPager(viewPager);
    }

}
