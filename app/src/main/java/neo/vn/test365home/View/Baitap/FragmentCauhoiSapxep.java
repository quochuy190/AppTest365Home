package neo.vn.test365home.View.Baitap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterDapAn;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Models.CauhoiDetail;
import neo.vn.test365home.Models.DapAn;
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
public class FragmentCauhoiSapxep extends BaseFragment {
    private static final String TAG = "FragmentCauhoi";
    private CauhoiDetail mCauhoi;
    AdapterDapAn adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<DapAn> mLisDapAn;
    @BindView(R.id.txtCauhoi)
    TextView txtCauhoi;
    @BindView(R.id.txt_debai_huongdan)
    TextView txt_debai_huongdan;

    @BindView(R.id.txt_number_de)
    TextView txt_number_de;
    @BindView(R.id.txtSubNumber)
    TextView txtSubNumber;
    @BindView(R.id.txt_current)
    TextView txt_current;

    public static FragmentCauhoiSapxep newInstance(CauhoiDetail restaurant) {
        FragmentCauhoiSapxep restaurantDetailFragment = new FragmentCauhoiSapxep();
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
        View view = inflater.inflate(R.layout.fragment_cauhoi_sapxep, container, false);
        ButterKnife.bind(this, view);
        Log.i(TAG, "onCreateView: " + mCauhoi.getsQUESTION());
        initData();
        return view;
    }

    @SuppressLint("NewApi")
    private void initData() {
        //txtCauhoi.setText(mCauhoi.getsQUESTION());
        txt_current.setText("Bài: "+mCauhoi.getsNumberDe()+" - Câu hỏi: "+mCauhoi.getsSubNumberCau());
        txt_number_de.setText("Bài: "+mCauhoi.getsNumberDe());
        txtSubNumber.setText("Câu hỏi: "+mCauhoi.getsSubNumberCau());
        txtCauhoi.setText(Html.fromHtml(mCauhoi.getsQUESTION(), Html.FROM_HTML_MODE_COMPACT));
        txt_debai_huongdan.setText(Html.fromHtml(mCauhoi.getsCauhoi_huongdan(), Html.FROM_HTML_MODE_COMPACT));
     //   txt_debai_huongdan.setText(mCauhoi.getsCauhoi_huongdan());

    }
}
