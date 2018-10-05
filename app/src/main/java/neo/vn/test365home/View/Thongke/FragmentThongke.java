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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.R;

public class FragmentThongke extends BaseFragment implements View.OnClickListener, OnChartValueSelectedListener {
    public static FragmentThongke fragment;
    private CombinedChart mChart;

    public static synchronized FragmentThongke getInstance() {
        if (fragment == null) {
            fragment = new FragmentThongke();
        }
        return (fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        ButterKnife.bind(this, view);
        mChart = (CombinedChart) view.findViewById(R.id.combinedChart);
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
        xLabel.add("1");
        xLabel.add("2");
        xLabel.add("3");
        xLabel.add("4");
        xLabel.add("5");
        xLabel.add("6");
        xLabel.add("7");
        xLabel.add("8");
        xLabel.add("9");
        xLabel.add("10");
        xLabel.add("11");
        xLabel.add("12");
        xLabel.add("13");
        xLabel.add("14");
        xLabel.add("15");
        xLabel.add("16");
        xLabel.add("17");
        xLabel.add("18");
        xLabel.add("19");
        xLabel.add("20");
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
        lineDatas.addDataSet((ILineDataSet) dataChart());
        lineDatas.addDataSet((ILineDataSet) dataChart1());
        lineDatas.addDataSet((ILineDataSet) dataChartTiengAnh());
        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
        initEvent();
        return view;
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

    private static DataSet dataChart() {

        LineData d = new LineData();
        int[] data = new int[]{1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9};

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.length; index++) {
            entries.add(new Entry(index, data[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Tiếng Việt");
        set.setColor(Color.GREEN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.GREEN);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    private static DataSet dataChart1() {

        LineData d = new LineData();
        int[] data = new int[]{4, 6, 3, 6, 5, 7, 8, 9, 10, 9, 10, 9};

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.length; index++) {
            entries.add(new Entry(index, data[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Toán");
        set.setColor(Color.RED);
        set.setLineWidth(1.5f);
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
    private static DataSet dataChartTiengAnh() {

        LineData d = new LineData();
        float[] data = new float[]{4, 7, 5, 10, 6, 9, 5, 4, 8, 8, 9, 10};

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.length; index++) {
            entries.add(new Entry(index, data[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Toán");
        set.setColor(Color.CYAN);
        set.setLineWidth(1.5f);
        set.setCircleColor(Color.CYAN);
        set.setCircleRadius(3f);
        set.setFillColor(Color.CYAN);
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.CYAN);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }
}
