package neo.vn.test365home.View.Baitap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterLeftMenu;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.MessageEvent;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class FragmentLeftMenu extends BaseFragment implements View.OnClickListener, ImpBaitap.View {
    public static FragmentLeftMenu fragment;
    AdapterLeftMenu adapter;
    @BindView(R.id.recycle_left_menu)
    StickyListHeadersListView recycle_left_menu;
    List<Childrens> mLisChil;
    PresenterBaitap mPresenter;

    public static synchronized FragmentLeftMenu getInstance() {
        if (fragment == null) {
            fragment = new FragmentLeftMenu();
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
        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mPresenter = new PresenterBaitap(this);
        init();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.message.equals(Constants.KEY_SEND_LEFT_MENU)) {
            initData();
        }
    }

    String sUserMe;

    private void initData() {
        if (isNetwork()) {
            showDialogLoading();
            sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
            mPresenter.get_api_list_get_children(sUserMe);
        }

        /*mLisChil.add(new Childrens("Quốc Huy", "a"));
        mLisChil.add(new Childrens("Quốc Huy", "b"));
        mLisChil.add(new Childrens("Quốc Huy", "c"));
        mLisChil.add(new Childrens("Quốc Huy", "d"));
        mLisChil.add(new Childrens("Quốc Huy", "e"));
*/

        adapter.notifyDataSetChanged();
    }

    private void init() {
        mLisChil = new ArrayList<>();
        adapter = new AdapterLeftMenu(getActivity(), mLisChil, new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });
        recycle_left_menu.setAdapter(adapter);
        //recyleBaitap.setScrollContainer(false);
        adapter.notifyDataSetChanged();
        recycle_left_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EventBus.getDefault().post(new MessageEvent(Constants.KEY_SEND_EVENT_CHIL, 1,
                        0, mLisChil.get(i)));
            }
        });
    }

    private void initEvent() {

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
                mLisChil.clear();
                App.mLisChildren.clear();
                App.mLisChildren.addAll(mLis);
                List<Childrens> mLisKhoi1 = new ArrayList<>();
                List<Childrens> mLisKhoi2 = new ArrayList<>();
                List<Childrens> mLisKhoi3 = new ArrayList<>();
                List<Childrens> mLisKhoi4 = new ArrayList<>();
                List<Childrens> mLisKhoi5 = new ArrayList<>();

              /*  mLisTuanDamua.clear();
                // mLisTuanDamua.addAll(mLis);*/
                for (Childrens obj : mLis) {
                    if (obj.getsID_LEVEL() != null) {
                        switch (obj.getsID_LEVEL()) {
                            case "1":
                                obj.setsHeaderId("a");
                                mLisKhoi1.add(obj);
                                break;
                            case "2":
                                obj.setsHeaderId("b");
                                mLisKhoi2.add(obj);
                                break;
                            case "3":
                                obj.setsHeaderId("c");
                                mLisKhoi3.add(obj);
                                break;
                            case "4":
                                obj.setsHeaderId("d");
                                mLisKhoi4.add(obj);
                                break;
                            case "5":
                                obj.setsHeaderId("e");
                                mLisKhoi5.add(obj);
                                break;
                        }
                    }
                }
                if (mLisKhoi1.size() > 0) {
                    mLisChil.addAll(mLisKhoi1);
                }
                if (mLisKhoi2.size() > 0) {
                    mLisChil.addAll(mLisKhoi2);
                }
                if (mLisKhoi3.size() > 0) {
                    mLisChil.addAll(mLisKhoi3);
                }
                if (mLisKhoi4.size() > 0) {
                    mLisChil.addAll(mLisKhoi4);
                }
                if (mLisKhoi5.size() > 0) {
                    mLisChil.addAll(mLisKhoi5);
                }
                EventBus.getDefault().post(new MessageEvent(Constants.KEY_SEND_EVENT_CHIL, 1,
                        0, mLisChil.get(0)));
                if (!App.isFirstLoadData) {
                    App.isFirstLoadData = true;
                }
                adapter.notifyDataSetChanged();
            } else if (mLis.get(0).getsERROR().equals("0001")) {
                EventBus.getDefault().post(new MessageEvent(Constants.KEY_SEND_EVENT_CHIL, 2,
                        0, null));
            } else {

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
}
