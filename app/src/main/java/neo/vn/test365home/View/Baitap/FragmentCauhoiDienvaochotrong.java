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
public class FragmentCauhoiDienvaochotrong extends BaseFragment {
    private static final String TAG = "FragmentCauhoi";
    private CauhoiDetail mCauhoi;
    AdapterDapAn adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<DapAn> mLisDapAn;
    @BindView(R.id.txtCauhoi)
    WebView txtCauhoi;
    @BindView(R.id.title_anwser)
    TextView title_anwser;
    @BindView(R.id.view_anwser)
    View view_anwser;
    @BindView(R.id.txtTraloi)
    WebView txtTraloi;
    @BindView(R.id.txtDapan)
    WebView txtDapan;
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
    public static FragmentCauhoiDienvaochotrong newInstance(CauhoiDetail restaurant) {
        FragmentCauhoiDienvaochotrong restaurantDetailFragment = new FragmentCauhoiDienvaochotrong();
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
        View view = inflater.inflate(R.layout.fragment_cauhoi_dienchotrong, container, false);
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
        if (mCauhoi.getsRESULT_CHILD() != null && mCauhoi.getsRESULT_CHILD().length() > 0) {
            if (mCauhoi.getsRESULT_CHILD().equals("1")) {
                Glide.with(getContext()).load(R.drawable.icon_anwser_true).into(img_anwser_chil);
            } else if (mCauhoi.getsANSWER_CHILD() != null && mCauhoi.getsANSWER_CHILD().length() > 0) {
                Glide.with(getContext()).load(R.drawable.icon_anwser_false).into(img_anwser_chil);
            } else {
                Glide.with(getContext()).load(R.drawable.icon_anwser_unknow).into(img_anwser_chil);
            }

        }
        if (mCauhoi.getsHTML_CONTENT() != null && mCauhoi.getsHTML_CONTENT().length() > 0) {
            String s = mCauhoi.getsHTML_CONTENT();
            String s_new = replaceXML("<<", ">>", s);
            initWebview(txtCauhoi, StringUtil.convert_html(s_new));
         //   txtCauhoi.setText(Html.fromHtml(s_new));
        }

        txt_debai_huongdan.setText(Html.fromHtml(mCauhoi.getsCauhoi_huongdan()
        ));
        String s_Traloi = mCauhoi.getsHTML_CONTENT().replaceAll("<<", "<u><b><font color='blue'>")
                .replaceAll(">>", "</font></b></u>");
        //   txt_debai_huongdan.setText(mCauhoi.getsCauhoi_huongdan());
        // txtDapan.setText(s_Traloi);
        initWebview(txtDapan, StringUtil.convert_html(s_Traloi));
        //txtDapan.setText(Html.fromHtml(s_Traloi));
        if (mCauhoi.getsANSWER_CHILD() != null && mCauhoi.getsANSWER_CHILD().length() > 0) {
            txtTraloi.setVisibility(View.VISIBLE);
            title_anwser.setVisibility(View.VISIBLE);
            view_anwser.setVisibility(View.VISIBLE);
            String s_AnwserChil = mCauhoi.getsANSWER_CHILD().replaceAll("<<", "<u><b><font color='red'>")
                    .replaceAll(">>", "</font></b></u>");
            initWebview(txtTraloi, StringUtil.convert_html(s_AnwserChil));
           // txtTraloi.setText(Html.fromHtml(s_AnwserChil));
        } else {
            txtTraloi.setVisibility(View.GONE);
            title_anwser.setVisibility(View.GONE);
            view_anwser.setVisibility(View.GONE);
        }

        //txtTraloi.setText(s_Traloi);
    }

    public String replaceStringBuffer(int first, int last, String st) {
        String s = "";
        StringBuffer sbf = new StringBuffer(st);
        s = String.valueOf(sbf.replace(first, last, "<font color='blue'>__</font>"));
        return s;
    }

    public String replaceXML(String start, String end, String st) {
        String s = st;
        if (s.length() == 0)
            return s;
        int index = s.indexOf(start);
        // int index_end = st.indexOf(end);
        int matchLength = start.length();
        while (index >= 0) {  // indexOf returns -1 if no match found
            int startIndex = s.indexOf(start, index);
            int endIndex = s.indexOf(end, index + matchLength);
            //   System.out.println(s.substring(startIndex + 1, endIndex));
            if (startIndex > -1 && startIndex < endIndex) {
                s = replaceStringBuffer((startIndex), endIndex + 2, s);
            }
            index = s.indexOf(start, index + matchLength);
        }
        //  s.replaceAll("<<", "");
        return s;
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
