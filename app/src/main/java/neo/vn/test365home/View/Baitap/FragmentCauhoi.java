package neo.vn.test365home.View.Baitap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterDapAn;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Config;
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
public class FragmentCauhoi extends BaseFragment {
    private static final String TAG = "FragmentCauhoi";
    private CauhoiDetail mCauhoi;
    AdapterDapAn adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<DapAn> mLisDapAn;
    @BindView(R.id.txtCauhoi)
    TextView txtCauhoi;
    @BindView(R.id.txt_debai_huongdan)
    TextView txt_debai_huongdan;

    @BindView(R.id.chechbox_A)
    ImageView chechbox_A;
    @BindView(R.id.img_dapan_A)
    ImageView img_dapan_A;
    @BindView(R.id.txt_dapan_A)
    TextView txt_dapan_A;
    @BindView(R.id.ll_dapanA)
    LinearLayout ll_dapanA;

    @BindView(R.id.chechbox_B)
    ImageView chechbox_B;
    @BindView(R.id.img_dapan_B)
    ImageView img_dapan_B;
    @BindView(R.id.txt_dapan_B)
    TextView txt_dapan_B;
    @BindView(R.id.ll_dapanB)
    LinearLayout ll_dapanB;

    @BindView(R.id.chechbox_C)
    ImageView chechbox_C;
    @BindView(R.id.img_dapan_C)
    ImageView img_dapan_C;
    @BindView(R.id.txt_dapan_C)
    TextView txt_dapan_C;
    @BindView(R.id.ll_dapanC)
    LinearLayout ll_dapanC;

    @BindView(R.id.chechbox_D)
    ImageView chechbox_D;
    @BindView(R.id.img_dapan_D)
    ImageView img_dapan_D;
    @BindView(R.id.txt_dapan_D)
    TextView txt_dapan_D;
    @BindView(R.id.ll_dapanD)
    LinearLayout ll_dapanD;
    @BindView(R.id.txt_number_de)
    TextView txt_number_de;
    @BindView(R.id.txtSubNumber)
    TextView txtSubNumber;
    @BindView(R.id.txt_current)
    TextView txt_current;

    public static FragmentCauhoi newInstance(CauhoiDetail restaurant) {
        FragmentCauhoi restaurantDetailFragment = new FragmentCauhoi();
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
        View view = inflater.inflate(R.layout.fragment_cauhoi_chondapan, container, false);
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
        if (mCauhoi.getsA().length() > 0) {
            if (mCauhoi.getsANSWER().equals("A")){
                txt_dapan_A.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_A);
            }
            if (mCauhoi.getsA().indexOf("upload") > 0) {
                txt_dapan_A.setVisibility(View.GONE);
                Glide.with(getContext()).load(Config.URL_IMAGE + mCauhoi.getsA()).into(img_dapan_A);
            } else {
                img_dapan_A.setVisibility(View.GONE);

                txt_dapan_A.setText(mCauhoi.getsA());
            }
        } else ll_dapanA.setVisibility(View.GONE);

        if (mCauhoi.getsC().length() > 0) {
            if (mCauhoi.getsANSWER().equals("C")){
                txt_dapan_C.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_C);
            }
            if (mCauhoi.getsC().indexOf("upload") > 0) {
                txt_dapan_C.setVisibility(View.GONE);
                Glide.with(getContext()).load(Config.URL_IMAGE + mCauhoi.getsC()).into(img_dapan_C);
            } else {
                img_dapan_C.setVisibility(View.GONE);
                txt_dapan_C.setText(mCauhoi.getsC());
            }
        } else ll_dapanC.setVisibility(View.GONE);

        if (mCauhoi.getsB().length() > 0) {
            if (mCauhoi.getsANSWER().equals("B")){
                txt_dapan_B.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_B);
            }
            if (mCauhoi.getsB().indexOf("upload") > 0) {
                txt_dapan_B.setVisibility(View.GONE);
                Glide.with(getContext()).load(Config.URL_IMAGE + mCauhoi.getsB()).into(img_dapan_B);
            } else {
                img_dapan_B.setVisibility(View.GONE);
                txt_dapan_B.setText(mCauhoi.getsB());
            }
        } else ll_dapanB.setVisibility(View.GONE);

        if (mCauhoi.getsD().length() > 0) {
            if (mCauhoi.getsANSWER().equals("D")){
                txt_dapan_D.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_D);
            }
            if (mCauhoi.getsD().indexOf("upload") > 0) {
                txt_dapan_D.setVisibility(View.GONE);
                Glide.with(getContext()).load(Config.URL_IMAGE + mCauhoi.getsD()).into(img_dapan_D);
            } else {
                img_dapan_D.setVisibility(View.GONE);
                txt_dapan_D.setText(mCauhoi.getsD());
            }
        } else ll_dapanD.setVisibility(View.GONE);
    }
}
