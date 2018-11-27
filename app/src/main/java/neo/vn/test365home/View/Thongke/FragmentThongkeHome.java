package neo.vn.test365home.View.Thongke;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterListChildren;
import neo.vn.test365home.Adapter.AdapterViewpager;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Chart_To_Subject;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;
import neo.vn.test365home.View.Baitap.ImpBaitap;
import neo.vn.test365home.View.Baitap.PresenterBaitap;


public class FragmentThongkeHome extends BaseFragment implements View.OnClickListener, ImpBaitap.View, ImpThongke.View {
    public static FragmentThongkeHome fragment;

    public static synchronized FragmentThongkeHome getInstance() {
        if (fragment == null) {
            fragment = new FragmentThongkeHome();
        }
        return (fragment);
    }

    PresenterThongke mPresenterChart;
    PresenterBaitap mPresenter;
    AdapterListChildren adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<Childrens> mLisChildren;
    @BindView(R.id.recyle_lis_sub_user)
    RecyclerView recyleChildren;
    public ViewPager viewPager;
    public AdapterViewpager adapterViewpager;
    @BindView(R.id.img_bxh_home)
    ImageView img_bxh;
    @BindView(R.id.txt_title_main)
    TextView txt_title;
    private Childrens mChildren;
    @BindView(R.id.txt_lable_toan)
    TextView txt_lable_toan;
    @BindView(R.id.txt_lable_tv)
    TextView txt_lable_tv;
    @BindView(R.id.txt_lable_ta)
    TextView txt_lable_ta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_home, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterBaitap(this);
        mPresenterChart = new PresenterThongke(this);
        initLisChildren();
        initData();
        initEvent();
        //setupViewPager();
        return view;
    }

    @BindView(R.id.combinedChart)
    CombinedChart mChart;
    List<String> mListTiengViet;
    List<String> mListToan;
    List<String> mListTiengAnh;

    private void initData() {
        mListToan = new ArrayList<>();
        mListTiengViet = new ArrayList<>();
        mListTiengAnh = new ArrayList<>();
        showDialogLoading();
        String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenter.get_api_list_get_children(user);
    }

    String sUserMe;

    private void initDataChart(Childrens obj) {
        txt_title.setText(obj.getsFULLNAME() + " - Lớp" + obj.getsID_LEVEL());
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPresenterChart.api_get_chart_to_subject_all(sUserMe, obj.getsUSERNAME());
    }

    private void initLisChildren() {
        mLisChildren = new ArrayList<>();
        adapter = new AdapterListChildren(mLisChildren, getContext());
        mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        //  mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyleChildren.setHasFixedSize(true);
        recyleChildren.setLayoutManager(mLayoutManager);
        recyleChildren.setItemAnimator(new DefaultItemAnimator());
        recyleChildren.setAdapter(adapter);

        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                Childrens objChil = (Childrens) item;
                mListToan.clear();
                mListTiengAnh.clear();
                mListTiengViet.clear();
                initChart(mListToan, mListTiengViet, mListTiengAnh);
                mChildren = objChil;
                initDataChart(objChil);
                /*SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, objChil);
                setupViewPager(objChil);*/
                for (int i = 0; i < mLisChildren.size()
                        ; i++) {
                    if (mLisChildren.get(i).getsID() != null && mLisChildren.get(i).getsID().equals(objChil.getsID())) {
                        mLisChildren.get(i).setChecked(true);
                    } else mLisChildren.get(i).setChecked(false);
                }
                adapter.updateList(mLisChildren);


            }
        });

    }

    private void initEvent() {
        img_bxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityBXH_Home.class);
                if (mChildren != null)
                    SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, mChildren);
                startActivity(intent);
            }
        });
        txt_lable_toan.setOnClickListener(this);
        txt_lable_tv.setOnClickListener(this);
        txt_lable_ta.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_lable_toan:
                show_chart_toan(mListToan, mListTiengViet, mListTiengAnh);
                break;
            case R.id.txt_lable_tv:
                initChart(mListToan, mListTiengViet, mListTiengAnh);
                break;
            case R.id.txt_lable_ta:
                show_chart_ta(mListToan, mListTiengViet, mListTiengAnh);
                break;
        }

    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_chart_to_subject_all(List<Chart_To_Subject> mLis) {
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
            show_chart_toan(mListToan, mListTiengViet, mListTiengAnh);
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

    @Override
    public void show_list_children(List<Childrens> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                mLisChildren.clear();
                App.mLisChildren.clear();
                App.mLisChildren.addAll(mLis);
                mLisChildren.addAll(mLis);
                mLisChildren.get(0).setChecked(true);
                adapter.notifyDataSetChanged();
                if (mLisChildren.size() > 0) {
                   /* SharedPrefs.getInstance().put(Constants.KEY_SEND_CHILDREN_FRAGMENT, mLisChildren.get(0));
                    setupViewPager(mLisChildren.get(0));*/
                    mChildren = mLisChildren.get(0);
                    initDataChart(mLisChildren.get(0));
                }

            }
        }
    }

    @Override
    public void show_list_week_test(List<ObjTuanhoc> mList) {

    }

    @Override
    public void show_list_buy_excercise(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_list_buy(List<TuanDamua> mLis) {

    }

    @Override
    public void show_list_des_excercise(List<ExcerciseDetail> mLis) {

    }

    @Override
    public void show_list_get_part(List<Cauhoi> mLis) {

    }

    @Override
    public void show_list_report_excercise(List<ExcerciseDetail> mLis) {

    }

    @Override
    public void show_list_get_sticker(List<Sticker> mLis) {

    }

    @Override
    public void show_list_gift_sticker(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_mt_comment(List<ErrorApi> mLis) {

    }

    private void setupViewPager(Childrens obj) {
        txt_title.setText(obj.getsFULLNAME() + " - Lớp" + obj.getsID_LEVEL());
        adapterViewpager = new AdapterViewpager(getChildFragmentManager());
        adapterViewpager.addFragment(FragmentThongke.newInstance(obj), "Thống kê tuần");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapterViewpager);
    }

    private void initChart(List<String> mLisPointToan, List<String> mListPointTV, List<String> mListPointTA) {
        txt_lable_toan.setTextColor(getResources().getColor(R.color.black_mo));
        txt_lable_ta.setTextColor(getResources().getColor(R.color.black_mo));
        txt_lable_tv.setTextColor(getResources().getColor(R.color.red_test365));

        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        //   mChart.setOnChartValueSelectedListener(this);
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
      /*  if (mLisPointToan != null && mLisPointToan.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartToan(mLisPointToan));

        if (mListPointTA != null && mListPointTA.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengAnh(mListPointTA));*/

        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
    }

    private void show_chart_toan(List<String> mLisPointToan, List<String> mListPointTV, List<String> mListPointTA) {
        txt_lable_toan.setTextColor(getResources().getColor(R.color.red_test365));
        txt_lable_ta.setTextColor(getResources().getColor(R.color.black_mo));
        txt_lable_tv.setTextColor(getResources().getColor(R.color.black_mo));
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        //   mChart.setOnChartValueSelectedListener(this);
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

        if (mLisPointToan != null && mLisPointToan.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartToan(mLisPointToan));
       /* if (mListPointTV != null && mListPointTV.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengViet(mListPointTV));
        if (mListPointTA != null && mListPointTA.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengAnh(mListPointTA));*/

        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
    }

    private void show_chart_ta(List<String> mLisPointToan, List<String> mListPointTV, List<String> mListPointTA) {
        txt_lable_toan.setTextColor(getResources().getColor(R.color.black_mo));
        txt_lable_ta.setTextColor(getResources().getColor(R.color.red_test365));
        txt_lable_tv.setTextColor(getResources().getColor(R.color.black_mo));
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        //   mChart.setOnChartValueSelectedListener(this);
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

     /*   if (mLisPointToan != null && mLisPointToan.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartToan(mLisPointToan));
        if (mListPointTV != null && mListPointTV.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengViet(mListPointTV));*/
        if (mListPointTA != null && mListPointTA.size() > 0)
            lineDatas.addDataSet((ILineDataSet) dataChartTiengAnh(mListPointTA));

        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
    }

    private static DataSet dataChartTiengViet(List<String> mListData) {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < mListData.size(); index++) {
            if (mListData.get(index) != null && mListData.get(index).length() > 0)
                entries.add(new Entry(index, Float.parseFloat(StringUtil.format_point(Float.parseFloat(mListData.get(index))))));
        }
        LineDataSet set = new LineDataSet(entries, "Tiếng Việt");
        //  set.setColor(Color.GREEN);
        set.setColor(Color.rgb(67, 145, 88));
        set.setLineWidth(2f);
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
        set.setLineWidth(2f);
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
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < mListData.size(); index++) {
            if (mListData.get(index) != null && mListData.get(index).length() > 0)
                entries.add(new Entry(index, Float.parseFloat(StringUtil.format_point(Float.parseFloat(mListData.get(index))))));
        }
        LineDataSet set = new LineDataSet(entries, "Tiếng Anh");
        set.setColor(Color.rgb(15, 97, 225));
        set.setLineWidth(2f);
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

}
