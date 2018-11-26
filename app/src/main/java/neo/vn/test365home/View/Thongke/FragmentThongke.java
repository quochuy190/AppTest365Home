package neo.vn.test365home.View.Thongke;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Chart_To_Subject;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;

public class FragmentThongke extends BaseFragment implements View.OnClickListener,
        OnChartValueSelectedListener, ImpThongke.View {
    public static FragmentThongke fragment;
    PresenterThongke mPresenter;
    @BindView(R.id.combinedChart)
    CombinedChart mChart;
    List<String> mListTiengViet;
    List<String> mListToan;
    List<String> mListTiengAnh;
    private Childrens mChildren;

    public static FragmentThongke newInstance(Childrens childrens) {
        FragmentThongke restaurantDetailFragment = new FragmentThongke();
        Bundle args = new Bundle();
        //args.putSerializable("cauhoi",restaurant);
        args.putParcelable("childrens", Parcels.wrap(childrens));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterThongke(this);
        mChildren = SharedPrefs.getInstance().get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        ButterKnife.bind(this, view);
        initData();

        initEvent();
        return view;
    }

    String sUserMe;

    private void initData() {
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mListToan = new ArrayList<>();
        mListTiengViet = new ArrayList<>();
        mListTiengAnh = new ArrayList<>();
        mPresenter.api_get_chart_to_subject_all(sUserMe, mChildren.getsUSERNAME());
    }

    private void initChart(List<String> mLisPointToan, List<String> mListPointTV, List<String> mListPointTA) {
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Tuần");
        xLabel.add("Tuần 1");
        xLabel.add("Tuần 2");
        xLabel.add("Tuần 3");
        xLabel.add("Tuần 4");
        xLabel.add("Tuần 5");
        xLabel.add("Tuần 6");
        xLabel.add("Tuần 7");
        xLabel.add("Tuần 8");
        xLabel.add("Tuần 9");
        xLabel.add("Tuần 10");
        xLabel.add("Tuần 11");
        xLabel.add("Tuần 12");
        xLabel.add("Tuần 13");
        xLabel.add("Tuần 14");
        xLabel.add("Tuần 15");
        xLabel.add("Tuần 16");
        xLabel.add("Tuần 17");
        xLabel.add("Tuần 18");
        xLabel.add("Tuần 19");
        xLabel.add("Tuần 20");
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });
        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        if (mListPointTV != null && mListPointTV.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengViet(mListPointTV));
        if (mLisPointToan != null && mLisPointToan.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartToan(mLisPointToan));
        if (mListPointTA != null && mListPointTA.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengAnh(mListPointTA));

        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
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
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private static DataSet dataChartTiengViet(List<String> mListData) {

        LineData d = new LineData();
        // int[] data = new int[]{1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9};

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < mListData.size(); index++) {
            if (mListData.get(index) != null && mListData.get(index).length() > 0)
                entries.add(new Entry(index, Float.parseFloat(StringUtil.format_point(Float.parseFloat(mListData.get(index))))));
        }
        LineDataSet set = new LineDataSet(entries, "Tiếng Việt");
        //  set.setColor(Color.GREEN);
        set.setColor(Color.rgb(67, 145, 88));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(67, 145, 88));
        set.setCircleRadius(3f);
        set.setFillColor(Color.rgb(67, 145, 88));
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(67, 145, 88));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    private static DataSet dataChartToan(List<String> mListData) {

        LineData d = new LineData();
        //  int[] data = new int[]{4, 6, 3, 6, 5, 7, 8, 9, 10, 9, 10, 9};

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < mListData.size(); index++) {
            if (mListData.get(index) != null && mListData.get(index).length() > 0) {
                float point = Float.parseFloat(mListData.get(index));
                String sPoint = StringUtil.format_point(point);
                entries.add(new Entry(index, Float.parseFloat(sPoint)));
            }

        }

        LineDataSet set = new LineDataSet(entries, "Toán");
        set.setColor(Color.RED);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.RED);
        set.setCircleRadius(3f);
        set.setFillColor(Color.RED);
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.RED);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    private static DataSet dataChartTiengAnh(List<String> mListData) {
        LineData d = new LineData();
        // float[] data = new float[]{4, 7, 5, 10, 6, 9, 5, 4, 8, 8, 9, 10};

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < mListData.size(); index++) {
            if (mListData.get(index) != null && mListData.get(index).length() > 0)
                entries.add(new Entry(index, Float.parseFloat(StringUtil.format_point(Float.parseFloat(mListData.get(index))))));
        }
        LineDataSet set = new LineDataSet(entries, "Tiếng Anh");
        set.setColor(Color.rgb(15, 97, 225));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(15, 97, 225));
        set.setCircleRadius(3f);
        set.setFillColor(Color.rgb(15, 97, 225));
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(15, 97, 225));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_chart_to_subject_all(List<Chart_To_Subject> mLis) {
        /*hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            for (Chart_To_Subject obj : mLis) {
                if (obj.getsSUBJECT_ID().equals("1")) {
                    mListToan.add(obj.getsPOINT());
                }
                if (obj.getsSUBJECT_ID().equals("2")) {
                    mListTiengViet.add(obj.getsPOINT());
                }
                if (obj.getsSUBJECT_ID().equals("3")) {
                    mListTiengAnh.add(obj.getsPOINT());
                }
            }
            mListToan.add("9");
            mListToan.add("8.5");
            mListToan.add("10");
            mListTiengViet.add("5");
            mListTiengViet.add("9");
            mListTiengViet.add("10");
            mListTiengAnh.add("6");
            mListTiengAnh.add("9");
            mListTiengAnh.add("8.5");
            initChart(mListToan, mListTiengViet, mListTiengAnh);
        }*/
        hideDialogLoading();
        List<Chart_To_Subject> mTiengViet = new ArrayList<>();
        List<Chart_To_Subject> mToan = new ArrayList<>();
        List<Chart_To_Subject> mTiengAnh = new ArrayList<>();
        mListTiengAnh.clear();
        mListToan.clear();
        mListTiengViet.clear();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            int iWeekToanMax = 0, iWeekTVMax = 0, iWeekTAMax = 0;
            for (Chart_To_Subject obj : mLis) {
                if (obj.getsSUBJECT_ID().equals("1")) {
                    int iWeekToan = Integer.parseInt(obj.getsWEEK_ID());
                    if (iWeekToan > iWeekToanMax)
                        iWeekToanMax = iWeekToan;
                    mToan.add(obj);
                }
                if (obj.getsSUBJECT_ID().equals("2")) {
                    int iWeekTV = Integer.parseInt(obj.getsWEEK_ID());
                    if (iWeekTV > iWeekTVMax)
                        iWeekTVMax = iWeekTV;
                    mTiengViet.add(obj);
                }
                if (obj.getsSUBJECT_ID().equals("3")) {
                    int iWeekTA = Integer.parseInt(obj.getsWEEK_ID());
                    if (iWeekTA > iWeekTAMax)
                        iWeekTAMax = iWeekTA;
                    mTiengAnh.add(obj);
                }
            }
            if (iWeekToanMax > 0) {
                for (int k = 0; k < iWeekToanMax; k++) {
                    mListToan.add(null);
                }
                if (mToan.size() > 0) {
                    for (int i = 0; i < (mListToan.size()); i++) {
                        for (int j = 0; j < mToan.size(); j++) {
                            int week = Integer.parseInt(mToan.get(j).getsWEEK_ID());
                            if ((i + 1) == week) {
                                mListToan.set(i, mToan.get(j).getsPOINT());
                            }
                        }
                    }
                }

            }

            if (iWeekTVMax > 0) {
                for (int k = 0; k < iWeekTVMax; k++) {
                    mListTiengViet.add(null);
                }
                if (mTiengViet.size() > 0) {
                    for (int i = 0; i < mListTiengViet.size(); i++) {
                        for (int j = 0; j < mTiengViet.size(); j++) {
                            int week = Integer.parseInt(mTiengViet.get(j).getsWEEK_ID());
                            if ((i + 1) == week) {
                                mListTiengViet.set(i, mTiengViet.get(j).getsPOINT());
                            }
                        }
                    }
                }
            }
            if (iWeekTAMax > 0) {
                for (int k = 0; k < iWeekTVMax; k++) {
                    mListTiengAnh.add(null);
                }
                if (mTiengAnh.size() > 0) {

                    for (int i = 0; i < mListTiengAnh.size(); i++) {
                        for (int j = 0; j < mTiengAnh.size(); j++) {
                            int week = Integer.parseInt(mTiengAnh.get(j).getsWEEK_ID());
                            if (i == week) {
                                mListTiengAnh.set(i, mTiengAnh.get(j).getsPOINT());
                            }
                        }
                    }
                }
            }
            mListToan.add(0, "0");
            mListTiengViet.add(0, "0");
            mListTiengAnh.add(0, "0");
            initChart(mListToan, mListTiengViet, mListTiengAnh);
        }
    }

    @Override
    public void show_chart_to_subject(List<Chart_To_Subject> mLis) {

    }

    @Override
    public void show_bxh_week_chart(List<BXH> mLis) {

    }

    @Override
    public void show_bxh_month_chart(List<BXH> mLis) {

    }

    @Override
    public void show_bxh_year_chart(List<BXH> mLis) {

    }
}
