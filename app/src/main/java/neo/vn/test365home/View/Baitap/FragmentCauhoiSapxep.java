package neo.vn.test365home.View.Baitap;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    @BindView(R.id.img_anwser_chil)
    ImageView img_anwser_chil;
    @BindView(R.id.txt_title_betraloi)
    TextView txt_title_betraloi;
      @BindView(R.id.txt_title_dapan)
    TextView txt_title_dapan;
      @BindView(R.id.txtDapan_noicau)
    TextView txtDapan_noicau;

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
        initData();
        return view;
    }


    private void initData() {
        //txtCauhoi.setText(mCauhoi.getsQUESTION());
        txt_current.setText("Bài: "+mCauhoi.getsNumberDe()+" - Câu hỏi: "+mCauhoi.getsSubNumberCau());
        txt_number_de.setText("Bài: "+mCauhoi.getsNumberDe());
        txtSubNumber.setText("Câu hỏi: "+mCauhoi.getsSubNumberCau());

        txt_debai_huongdan.setText(Html.fromHtml(mCauhoi.getsCauhoi_huongdan()));
     //   txt_debai_huongdan.setText(mCauhoi.getsCauhoi_huongdan());

        if (mCauhoi.getsANSWER_CHILD()!=null&&mCauhoi.getsANSWER_CHILD().length()>0){
            txt_title_betraloi.setVisibility(View.VISIBLE);
            txtCauhoi.setVisibility(View.VISIBLE);
            if (mCauhoi.getsRESULT_CHILD()!=null&&mCauhoi.getsRESULT_CHILD().length() > 0) {
                if (mCauhoi.getsRESULT_CHILD().equals("1")) {
                    txtCauhoi.setTextColor(getResources().getColor(R.color.blue));
                    Glide.with(getContext()).load(R.drawable.icon_anwser_true).into(img_anwser_chil);
                } else{
                    txtCauhoi.setTextColor(getResources().getColor(R.color.red));
                    Glide.with(getContext()).load(R.drawable.icon_anwser_false).into(img_anwser_chil);
                }

            }
        }else {
            txt_title_betraloi.setVisibility(View.GONE);
            txtCauhoi.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.icon_anwser_unknow).into(img_anwser_chil);
        }
        String s = "";
        if (mCauhoi.getsANSWER_CHILD()!=null&&mCauhoi.getsANSWER_CHILD().length()>0){
            String sAnwser[] = mCauhoi.getsANSWER_CHILD().split("::");
            for (int i = 0;i<sAnwser.length;i++){
                s = s+sAnwser[i].trim()+" ";
            }
            txtCauhoi.setText(s);
        }
        if (mCauhoi.getsQUESTION()!=null&&mCauhoi.getsQUESTION().length()>0){
            txtDapan_noicau.setText(mCauhoi.getsQUESTION().replaceAll("::", " "));
        }


    }
}
