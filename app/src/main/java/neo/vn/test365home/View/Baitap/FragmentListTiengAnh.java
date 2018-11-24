package neo.vn.test365home.View.Baitap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterHeader;
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
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static android.app.Activity.RESULT_OK;

public class FragmentListTiengAnh extends BaseFragment implements View.OnClickListener, ImpBaitap.View {
    private static final String TAG = "FragmentListTiengViet";
    private Childrens mChildren;

    public static FragmentListTiengAnh newInstance(Childrens childrens) {
        FragmentListTiengAnh restaurantDetailFragment = new FragmentListTiengAnh();
        Bundle args = new Bundle();
        //args.putSerializable("cauhoi",restaurant);
        args.putParcelable("childrens", Parcels.wrap(childrens));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }
    PresenterBaitap mPresenter;
    AdapterHeader adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<TuanDamua> mLisTuanDamua;
    @BindView(R.id.recycle_lis_baitap)
    StickyListHeadersListView recyleBaitap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_baitap, container, false);
        mChildren = SharedPrefs.getInstance().get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
        Log.i(TAG, "onCreateView: " + mChildren.getsUSERNAME());
        ButterKnife.bind(this, view);
        mPresenter = new PresenterBaitap(this);
        Log.i(TAG, "onCreateView: ");
        init();
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.get_api_list_buy(user, mChildren.getsUSERNAME(),
                "3", mChildren.getsID_LEVEL());

    }

    private void init() {
        mLisTuanDamua = new ArrayList<>();
        adapter = new AdapterHeader(getActivity(), mLisTuanDamua, new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                Intent intent = new Intent(getActivity(), ActivityKhobaitap.class);
                intent.putExtra(Constants.KEY_SEND_OBJECTIVE, "3");
                intent.putExtra(Constants.KEY_SEND_CHILDREN, mChildren);
                startActivityForResult(intent, Constants.RequestCode.GET_KHO_BAITAP);
               /* Intent intent = new Intent(getActivity(), ActivityKhobaitap.class);
                intent.putExtra(Constants.KEY_SEND_OBJECTIVE, "3");
                intent.putExtra(Constants.KEY_SEND_CHILDREN, mChildren);
                startActivity(intent);*/
            }
        });
        //set ignore case when searched
        /*    adapter.setIgnoreCase(true);*/
        recyleBaitap.setAdapter(adapter);
        //recyleBaitap.setScrollContainer(false);
        adapter.notifyDataSetChanged();
        recyleBaitap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ActivityBaitapDetail.class);
                intent.putExtra(Constants.KEY_SEND_OBJECTIVE, "3");
                intent.putExtra(Constants.KEY_SEND_ID_WEEKTEST, mLisTuanDamua.get(i));
                startActivity(intent);
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

    }

    @Override
    public void show_list_week_test(List<ObjTuanhoc> mList) {

    }

    @Override
    public void show_list_buy_excercise(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_list_buy(List<TuanDamua> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR() != null && mLis.get(0).getsERROR().equals("0000")) {
                List<TuanDamua> mLisDanglam = new ArrayList<>();
                List<TuanDamua> mLisQuahan = new ArrayList<>();
                List<TuanDamua> mLisChualam = new ArrayList<>();
                List<TuanDamua> mLisDalam = new ArrayList<>();
                List<TuanDamua> mLisChuaco = new ArrayList<>();
                mLisTuanDamua.clear();
                // mLisTuanDamua.addAll(mLis);
                for (TuanDamua obj : mLis) {
                    if (obj.getsTYPETAB() != null) {
                        switch (obj.getsTYPETAB()) {
                            case "1":
                                obj.setsHeaderId("e");
                                mLisChuaco.add(obj);
                                break;
                            case "2":
                                obj.setsHeaderId("c");
                                mLisDalam.add(obj);
                                break;
                            case "3":
                                obj.setsHeaderId("b");
                                mLisQuahan.add(obj);
                                break;
                            case "4":
                                obj.setsHeaderId("a");
                                mLisDanglam.add(obj);
                                break;
                            case "5":
                                obj.setsHeaderId("d");
                                mLisChualam.add(obj);
                                break;
                        }
                    }
                }
                if (mLisDanglam.size() > 0) {
                    mLisTuanDamua.addAll(mLisDanglam);
                }
                if (mLisQuahan.size() > 0) {
                    mLisTuanDamua.addAll(mLisQuahan);
                }
                if (mLisDalam.size() > 0) {
                    mLisTuanDamua.addAll(mLisDalam);
                }
                if (mLisChualam.size() > 0) {
                    mLisTuanDamua.addAll(mLisChualam);
                }
                if (mLisChuaco.size() > 0) {
                    mLisTuanDamua.addAll(mLisChuaco);
                }
                mLisTuanDamua.add(new TuanDamua());
                adapter.notifyDataSetChanged();
            } else {
                mLisTuanDamua.add(new TuanDamua());
                adapter.notifyDataSetChanged();
            }
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.GET_KHO_BAITAP:
                if (resultCode == RESULT_OK) {
                    showDialogLoading();
                    String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                    mPresenter.get_api_list_buy(user, mChildren.getsUSERNAME(),"3",
                            mChildren.getsID_LEVEL());
                }
                break;

        }
    }
}
