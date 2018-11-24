package neo.vn.test365home.View.Thongke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterListChildren;
import neo.vn.test365home.Adapter.AdapterViewpager;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseFragment;
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


public class FragmentThongkeHome extends BaseFragment implements View.OnClickListener, ImpBaitap.View {
    public static FragmentThongkeHome fragment;

    public static synchronized FragmentThongkeHome getInstance() {
        if (fragment == null) {
            fragment = new FragmentThongkeHome();
        }
        return (fragment);
    }

    PresenterBaitap mPresenter;
    AdapterListChildren adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<Childrens> mLisChildren;
    @BindView(R.id.recyle_lis_sub_user)
    RecyclerView recyleChildren;
    public ViewPager viewPager;
    public AdapterViewpager adapterViewpager;
    @BindView(R.id.img_bxh_home)
    ImageView img_bxh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_home, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterBaitap(this);
        viewPager = view.findViewById(R.id.viewpager_flight_home);

        initLisChildren();
        initData();
        initEvent();
        //setupViewPager();
        return view;
    }

    private void initData() {
        showDialogLoading();
        String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.get_api_list_get_children(user);
    }

    private void initLisChildren() {
        mLisChildren = new ArrayList<>();
        adapter = new AdapterListChildren(mLisChildren, getContext());
        mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        //  mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyleChildren.setHasFixedSize(true);
        recyleChildren.setLayoutManager(mLayoutManager);
        recyleChildren.setItemAnimator(new DefaultItemAnimator());
        recyleChildren.setAdapter(adapter);

        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                if (position == mLisChildren.size() - 1) {
                    startActivity(new Intent(getContext(), ActivityAddSubUser.class));
                } else {
                    Childrens objChil = (Childrens) item;
                    SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, objChil);
                    setupViewPager(objChil);
                    for (int i = 0; i < (mLisChildren.size() - 1); i++) {
                        if (mLisChildren.get(i).getsID() != null && mLisChildren.get(i).getsID().equals(objChil.getsID())) {
                            mLisChildren.get(i).setChecked(true);
                        } else mLisChildren.get(i).setChecked(false);
                    }
                    adapter.updateList(mLisChildren);

                }
            }
        });

    }

    private void initEvent() {
        img_bxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityBXH_Home.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_children(List<Childrens> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                mLisChildren.clear();
                App.mLisChildren.clear();
                App.mLisChildren.addAll(mLis);
                mLisChildren.addAll(mLis);
                mLisChildren.get(0).setChecked(true);
                mLisChildren.add(new Childrens());
                adapter.notifyDataSetChanged();
                if (mLisChildren.size() > 0) {
                    SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, mLisChildren.get(0));
                    setupViewPager(mLisChildren.get(0));
                }

            }
        }
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

    private void setupViewPager(Childrens obj) {

        adapterViewpager = new AdapterViewpager(getChildFragmentManager());
        adapterViewpager.addFragment(FragmentThongke.newInstance(obj), "Thống kê tuần");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapterViewpager);
    }

}
