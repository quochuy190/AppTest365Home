package neo.vn.test365home.View.Baitap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
import neo.vn.test365home.Models.CauhoiDetail;
import neo.vn.test365home.Models.DapAn;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;

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
    @BindView(R.id.webview_cauhoi)
    WebView webview_cauhoi;
    @BindView(R.id.txt_debai_huongdan)
    TextView txt_debai_huongdan;

    @BindView(R.id.chechbox_A)
    ImageView chechbox_A;
    @BindView(R.id.webview_dapan_A)
    WebView webview_dapan_A;
    @BindView(R.id.ll_dapanA)
    LinearLayout ll_dapanA;

    @BindView(R.id.chechbox_B)
    ImageView chechbox_B;
    @BindView(R.id.webview_dapan_B)
    WebView webview_dapan_B;

    @BindView(R.id.ll_dapanB)
    LinearLayout ll_dapanB;

    @BindView(R.id.chechbox_C)
    ImageView chechbox_C;
    @BindView(R.id.webview_dapan_C)
    WebView webview_dapan_C;
    @BindView(R.id.ll_dapanC)
    LinearLayout ll_dapanC;

    @BindView(R.id.chechbox_D)
    ImageView chechbox_D;
    @BindView(R.id.webview_dapan_D)
    WebView webview_dapan_D;
    @BindView(R.id.ll_dapanD)
    LinearLayout ll_dapanD;

    @BindView(R.id.txt_number_de)
    TextView txt_number_de;
    @BindView(R.id.txtSubNumber)
    TextView txtSubNumber;
    @BindView(R.id.txt_current)
    TextView txt_current;
    @BindView(R.id.img_anwser_chil)
    ImageView img_anwser_chil;

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

    private void initData() {

        //txtCauhoi.setText(mCauhoi.getsQUESTION());
        txt_current.setText("Bài: " + mCauhoi.getsNumberDe() + " - Câu hỏi: " + mCauhoi.getsSubNumberCau());
        txt_number_de.setText("Bài: " + mCauhoi.getsNumberDe());
        txtSubNumber.setText("Câu hỏi: " + mCauhoi.getsSubNumberCau());

        if (mCauhoi.getsRESULT_CHILD()!=null&&mCauhoi.getsRESULT_CHILD().length() > 0) {
            if (mCauhoi.getsRESULT_CHILD().equals("1")) {
                Glide.with(getContext()).load(R.drawable.icon_anwser_true).into(img_anwser_chil);
            } else
                Glide.with(getContext()).load(R.drawable.icon_anwser_false).into(img_anwser_chil);
        } else {
            Glide.with(getContext()).load(R.drawable.icon_anwser_unknow).into(img_anwser_chil);
        }

        // txtCauhoi.setText(Html.fromHtml(mCauhoi.getsQUESTION(), Html.FROM_HTML_MODE_COMPACT));
        initWebview(webview_cauhoi, StringUtil.convert_html(mCauhoi.getsHTML_CONTENT()));
        txt_debai_huongdan.setText(Html.fromHtml(mCauhoi.getsCauhoi_huongdan()));
        //   txt_debai_huongdan.setText(mCauhoi.getsCauhoi_huongdan());
        if (mCauhoi.getsHTML_A().length() > 0) {
            if (mCauhoi.getsANSWER().equals("A")) {
                //  txt_dapan_A.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_A);
            }
            initWebview(webview_dapan_A, StringUtil.convert_html(mCauhoi.getsHTML_A()));
        } else ll_dapanA.setVisibility(View.GONE);

        if (mCauhoi.getsHTML_C().length() > 0) {
            if (mCauhoi.getsANSWER().equals("C")) {
                // txt_dapan_C.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_C);
            }
            initWebview(webview_dapan_C, StringUtil.convert_html(mCauhoi.getsHTML_C()));
        } else ll_dapanC.setVisibility(View.GONE);

        if (mCauhoi.getsHTML_B().length() > 0) {
            if (mCauhoi.getsANSWER().equals("B")) {
                //txt_dapan_B.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_B);
            }
            initWebview(webview_dapan_B, StringUtil.convert_html(mCauhoi.getsHTML_B()));
        } else ll_dapanB.setVisibility(View.GONE);

        if (mCauhoi.getsHTML_D().length() > 0) {
            if (mCauhoi.getsANSWER().equals("D")) {
                //   txt_dapan_D.setTextColor(getResources().getColor(R.color.blue));
                Glide.with(getContext()).load(R.drawable.ic_checked_blue).into(chechbox_D);
            }
            initWebview(webview_dapan_D, StringUtil.convert_html(mCauhoi.getsHTML_D()));
        } else ll_dapanD.setVisibility(View.GONE);
    }

    private void initWebview(WebView webview_debai, String link_web) {
        webview_debai.getSettings().setJavaScriptEnabled(true);
        webview_debai.getSettings();
        webview_debai.setBackgroundColor(Color.TRANSPARENT);
        WebSettings webSettings = webview_debai.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setDefaultFontSize(16);
        /* <html><body  align='center'>You scored <b>192</b> points.</body></html>*/
        String pish = "<html><body  align='center'>";
        String pas = "</body></html>";

        webview_debai.loadDataWithBaseURL("", pish + link_web + pas,
                "text/html", "UTF-8", "");

    }
}
