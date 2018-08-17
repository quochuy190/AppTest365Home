package neo.vn.test365home.View.Baitap;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterCauhoiNoicau;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Models.CauhoiDetail;
import neo.vn.test365home.R;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 8/6/2018
 * @updated 8/6/2018
 * @modified by
 * @updated on 8/6/2018
 * @since 1.0
 */
public class FragmentCauhoiNoicau extends BaseFragment {
    private CauhoiDetail mCauhoi;
    AdapterCauhoiNoicau adapterLeft;
    AdapterCauhoiNoicau adapterRight;
    RecyclerView.LayoutManager mLayoutManager, layoutManager2;
    @BindView(R.id.list_cauhoi_left)
    RecyclerView list_cauhoi_left;
    @BindView(R.id.list_cauhoi_right)
    RecyclerView list_cauhoi_right;
    private List<CauhoiDetail> mLisLeft;
    private List<CauhoiDetail> mLisRight;
    public static FragmentCauhoiNoicau newInstance(CauhoiDetail restaurant) {
        FragmentCauhoiNoicau restaurantDetailFragment = new FragmentCauhoiNoicau();
        Bundle args = new Bundle();
        //args.putSerializable("cauhoi",restaurant);
        args.putParcelable("cauhoi", Parcels.wrap(restaurant));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCauhoi = Parcels.unwrap(getArguments().getParcelable("cauhoi"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cauhoi_noicau, container, false);
        ButterKnife.bind(this, view);

        initDataLeft();
        initListLeft();

        return view;
    }

    private void initListLeft() {

        adapterLeft = new AdapterCauhoiNoicau(mLisLeft, getContext());
        adapterRight = new AdapterCauhoiNoicau(mLisRight, getContext());
       /* mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);*/

        mLayoutManager = new GridLayoutManager(getContext(),
                1, GridLayoutManager.VERTICAL, false);
        list_cauhoi_left.setHasFixedSize(true);
        list_cauhoi_left.setLayoutManager(mLayoutManager);
        list_cauhoi_left.setItemAnimator(new DefaultItemAnimator());
        list_cauhoi_left.setAdapter(adapterLeft);
        adapterLeft.notifyDataSetChanged();
        layoutManager2 = new GridLayoutManager(getContext(),
                1, GridLayoutManager.VERTICAL, false);
        list_cauhoi_right.setHasFixedSize(true);
        list_cauhoi_right.setLayoutManager(layoutManager2);
        list_cauhoi_right.setItemAnimator(new DefaultItemAnimator());
        list_cauhoi_right.setAdapter(adapterRight);
        adapterRight.notifyDataSetChanged();

    }

    private void initDataLeft() {
        mLisLeft = new ArrayList<>();

        mLisLeft.add(new CauhoiDetail("","Chăm chỉ",""));
        mLisLeft.add(new CauhoiDetail("","Con Lợn",""));
        mLisLeft.add(new CauhoiDetail("","Quốc gia",""));
        mLisLeft.add(new CauhoiDetail("","Tàu hoả",""));

        for (CauhoiDetail obj : mLisLeft){
            obj.setmLeft(true);
        }

        mLisRight = new ArrayList<>();
        mLisRight.add(new CauhoiDetail("","Siêng năng",""));
        mLisRight.add(new CauhoiDetail("","Xe lửa",""));
        mLisRight.add(new CauhoiDetail("","Con heo",""));
        mLisRight.add(new CauhoiDetail("","Đất nước",""));

        for (CauhoiDetail obj : mLisRight){
            obj.setmRight(true);
        }
    }



}
