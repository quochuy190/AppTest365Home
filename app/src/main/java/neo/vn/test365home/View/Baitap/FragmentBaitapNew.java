package neo.vn.test365home.View.Baitap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterViewpager;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.MessageEvent;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.FragmentUtil;
import neo.vn.test365home.Untils.SharedPrefs;

public class FragmentBaitapNew extends BaseFragment implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "FragmentListTiengViet";
    public static FragmentBaitapNew fragment;
    public ViewPager viewPager;
    public AdapterViewpager adapterViewpager;
    public TabLayout tabLayout;
    @BindView(R.id.txt_title_main)
    TextView txt_title_main;
    DrawerLayout drawer;

    public static synchronized FragmentBaitapNew getInstance() {
        if (fragment == null) {
            fragment = new FragmentBaitapNew();
        }
        return (fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_baitap, container, false);
        ButterKnife.bind(this, view);
        viewPager = view.findViewById(R.id.viewpager_flight_home);
        tabLayout = view.findViewById(R.id.tab_layout);
        EventBus.getDefault().register(this);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        /*   getActivity().setSupportActionBar(toolbar);*/
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initEvent();
        initLeftMenu();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setupViewPager(Childrens obj) {
        Log.i(TAG, "setupViewPager lop: " + obj.getsID_LEVEL());
        SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, obj);
        txt_title_main.setText(obj.getsFULLNAME() + " - " + "lớp " + obj.getsID_LEVEL());
        adapterViewpager = new AdapterViewpager(getChildFragmentManager());
        adapterViewpager.addFragment(FragmentListToan.newInstance(obj), "TOÁN");
        adapterViewpager.addFragment(FragmentListTiengviet.newInstance(obj), "TIẾNG VIỆT");
        adapterViewpager.addFragment(FragmentListTiengAnh.newInstance(obj), "TIẾNG ANH");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapterViewpager);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.message.equals(Constants.KEY_SEND_EVENT_CHIL)) {
            Log.i(TAG, "onMessageEvent: " + event.obj.getsID_LEVEL());
            setupViewPager(event.obj);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    private void initEvent() {

    }

    private void initLeftMenu() {
        FragmentUtil.pushFragmentLayoutMain(getChildFragmentManager(),
                R.id.frame_leftmenu, FragmentLeftMenu.getInstance(), null);
    }

    @Override
    public void onClick(View view) {

    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
