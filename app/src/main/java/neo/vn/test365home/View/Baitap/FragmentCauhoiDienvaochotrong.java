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
import java.util.regex.Pattern;

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
public class FragmentCauhoiDienvaochotrong extends BaseFragment {
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
        View view = inflater.inflate(R.layout.fragment_cauhoi_sapxep, container, false);
        ButterKnife.bind(this, view);
        Log.i(TAG, "onCreateView: " + mCauhoi.getsQUESTION());
        initData();
        return view;
    }

    @SuppressLint("NewApi")
    private void initData() {
        //txtCauhoi.setText(mCauhoi.getsQUESTION());
        txt_current.setText("Bài: " + mCauhoi.getsNumberDe() + " - Câu hỏi: " + mCauhoi.getsSubNumberCau());
        txt_number_de.setText("Bài: " + mCauhoi.getsNumberDe());
        txtSubNumber.setText("Câu hỏi: " + mCauhoi.getsSubNumberCau());
        Log.i(TAG, "initData: " + mCauhoi.getsQUESTION());

         String s = mCauhoi.getsQUESTION();

        //String s_new = replaceBetween(mCauhoi.getsQUESTION(), "<<", ">>", "....");


        //txtCauhoi.setText(replaceXML("<<", ">>", mCauhoi.getsQUESTION()));
        String s_new = replaceXML("<<", ">>", s);

        Log.i(TAG, "initData: " + s_new);

        txtCauhoi.setText(Html.fromHtml(s_new, Html.FROM_HTML_MODE_COMPACT));
        //txtCauhoi.setText(replaceBetween(1, 5, "Quốc huy"));
        // txtCauhoi.setText(s_new.replaceAll("<<", "").replace(">>",""));
        txt_debai_huongdan.setText(Html.fromHtml(mCauhoi.getsCauhoi_huongdan(),
                Html.FROM_HTML_MODE_COMPACT));
        //   txt_debai_huongdan.setText(mCauhoi.getsCauhoi_huongdan());

    }

    public static String replaceBetween(String input,
                                        String start, String end,
                                        String replaceWith) {
        return replaceBetween(input, start, end, false, false, replaceWith);
    }

    public static String replaceBetween(String input,
                                        String start, String end,
                                        boolean startInclusive,
                                        boolean endInclusive,
                                        String replaceWith) {
        start = Pattern.quote(start);
        end = Pattern.quote(end);
        return input.replaceAll("(" + start + ")" + ".*" + "(" + end + ")",
                (startInclusive ? "" : "$1") + replaceWith + (endInclusive ? "" : "$2"));
    }

    public String replaceStringBuffer(int first, int last, String st) {
        String s = "";
        StringBuffer sbf = new StringBuffer(st);
        s = String.valueOf(sbf.replace(first, last, "<font color='blue'>____</font>"));
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
            int startIndex = s.indexOf(start, index );
            int endIndex = s.indexOf(end, index+matchLength );
            //   System.out.println(s.substring(startIndex + 1, endIndex));
            if (startIndex>-1&&startIndex < endIndex) {
                s = replaceStringBuffer((startIndex), endIndex+2, s);
            }
            index = s.indexOf(start, index + matchLength);
        }
      //  s.replaceAll("<<", "");
        return s;
    }
}
