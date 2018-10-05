package neo.vn.test365home.View.Baitap;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private static final String TAG = "FragmentCauhoiNoicau";
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
    @BindView(R.id.txt_egg_result1)
    TextView txt_egg_result_1;
    @BindView(R.id.txt_egg_result2)
    TextView txt_egg_result_2;
    @BindView(R.id.txt_egg_result3)
    TextView txt_egg_result_3;
    @BindView(R.id.txt_egg_result4)
    TextView txt_egg_result_4;
    @BindView(R.id.textView3)
    TextView txt_lable_result;
    @BindView(R.id.ll_traloi)
    LinearLayout ll_traloi;

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

        mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
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
        String[] egg1 = mCauhoi.getsHTML_A().split("::");
        String[] egg2 = mCauhoi.getsHTML_B().split("::");
        String[] egg3 = mCauhoi.getsHTML_C().split("::");
        String[] egg4 = mCauhoi.getsHTML_D().split("::");
        if (mCauhoi.getsEGG_1_RESULT().length() > 0 && mCauhoi.getsEGG_1_RESULT().length() > 0
                && mCauhoi.getsEGG_1_RESULT().length() > 0 && mCauhoi.getsEGG_1_RESULT().length() > 0) {
            String[] egg_result_1 = mCauhoi.getsEGG_1_RESULT().split("::");
            String[] egg_result_2 = mCauhoi.getsEGG_2_RESULT().split("::");
            String[] egg_result_3 = mCauhoi.getsEGG_3_RESULT().split("::");
            String[] egg_result_4 = mCauhoi.getsEGG_4_RESULT().split("::");

            // So sanh trứng 1 và rổ còn lại
            Log.i(TAG, "initDataLeft: " + egg1[1] + " " + egg_result_1[1]);
            if (egg_result_1[1].equals(egg1[1])) {
                txt_egg_result_1.setText("1 - a");
                txt_egg_result_1.setTextColor(getResources().getColor(R.color.blue));
            } else if (egg_result_1[1].equals(egg2[1])) {
                txt_egg_result_1.setText("1 - b");
                txt_egg_result_1.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_1[1].equals(egg3[1])) {
                txt_egg_result_1.setText("1 - c");
                txt_egg_result_1.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_1[1].equals(egg4[1])) {
                txt_egg_result_1.setText("1 - d");
                txt_egg_result_1.setTextColor(getResources().getColor(R.color.red));
            }
            // So sanh trứng 2 và rổ còn lại
            Log.i(TAG, "initDataLeft: " + egg2[1] + " " + egg_result_2[1]);
            if (egg_result_2[1].equals(egg1[1])) {
                txt_egg_result_2.setText("2 - a");
                txt_egg_result_2.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_2[1].equals(egg2[1])) {
                txt_egg_result_2.setText("2 - b");
                txt_egg_result_2.setTextColor(getResources().getColor(R.color.blue));
            } else if (egg_result_2[1].equals(egg3[1])) {
                txt_egg_result_2.setText("2 - c");
                txt_egg_result_2.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_2[1].equals(egg4[1])) {
                txt_egg_result_2.setText("2 - d");
                txt_egg_result_2.setTextColor(getResources().getColor(R.color.red));
            }
// So sanh trứng 2 và rổ còn lại
            Log.i(TAG, "initDataLeft: " + egg3[1] + " " + egg_result_3[1]);
            if (egg_result_3[1].equals(egg1[1])) {
                txt_egg_result_3.setText("3 - a");
                txt_egg_result_3.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_3[1].equals(egg2[1])) {
                txt_egg_result_3.setText("3 - b");
                txt_egg_result_3.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_3[1].equals(egg3[1])) {
                txt_egg_result_3.setText("3 - c");
                txt_egg_result_3.setTextColor(getResources().getColor(R.color.blue));
            } else if (egg_result_3[1].equals(egg4[1])) {
                txt_egg_result_3.setText("3 - d");
                txt_egg_result_3.setTextColor(getResources().getColor(R.color.red));
            }
            // So sanh trứng 2 và rổ còn lại
            Log.i(TAG, "initDataLeft: " + egg4[1] + " " + egg_result_4[1]);
            if (egg_result_4[1].equals(egg1[1])) {
                txt_egg_result_4.setText("4 - a");
                txt_egg_result_4.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_4[1].equals(egg2[1])) {
                txt_egg_result_4.setText("4 - b");
                txt_egg_result_4.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_4[1].equals(egg3[1])) {
                txt_egg_result_4.setText("4 - c");
                txt_egg_result_4.setTextColor(getResources().getColor(R.color.red));
            } else if (egg_result_4[1].equals(egg4[1])) {
                txt_egg_result_4.setText("4 - d");
                txt_egg_result_4.setTextColor(getResources().getColor(R.color.blue));
            }
        } else {
            ll_traloi.setVisibility(View.GONE);
            txt_lable_result.setVisibility(View.GONE);
        }


        mLisLeft = new ArrayList<>();
        mLisLeft.add(new CauhoiDetail("", egg1[0], ""));
        mLisLeft.add(new CauhoiDetail("", egg2[0], ""));
        mLisLeft.add(new CauhoiDetail("", egg3[0], ""));
        mLisLeft.add(new CauhoiDetail("", egg4[0], ""));

        for (CauhoiDetail obj : mLisLeft) {
            obj.setmLeft(true);
        }

        mLisRight = new ArrayList<>();
        mLisRight.add(new CauhoiDetail("", egg1[1], ""));
        mLisRight.add(new CauhoiDetail("", egg2[1], ""));
        mLisRight.add(new CauhoiDetail("", egg3[1], ""));
        mLisRight.add(new CauhoiDetail("", egg4[1], ""));

        for (CauhoiDetail obj : mLisRight) {
            obj.setmRight(true);
        }
    }


}
