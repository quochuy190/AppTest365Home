package neo.vn.test365home.View.Setup;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import hotchemi.stringpicker.StringPicker;
import neo.vn.test365home.Adapter.AdapterGame;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ClickDialogPicker;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Game;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.TimeUtils;

public class ActivityConfigSubUser extends BaseActivity implements ImpSetup.View {
    RecyclerView.LayoutManager mLayoutManager;
    private List<Game> mLisGame;
    @BindView(R.id.recycle_listgame)
    RecyclerView recyleChildren;
    AdapterGame adapter;
    @BindView(R.id.txt_setup_cal_namemon)
    TextView txt_namemon;
    @BindView(R.id.txt_setup_cal_time)
    TextView txt_time;
    @BindView(R.id.txt_setup_cal_dayweek)
    TextView txt_weekday;
    @BindView(R.id.txt_setup_cal_time_tv)
    TextView txt_time_tv;
    @BindView(R.id.txt_setup_cal_week_tv)
    TextView txt_weekday_tv;
    @BindView(R.id.txt_setup_cal_time_ta)
    TextView txt_time_ta;
    @BindView(R.id.txt_setup_cal_week_ta)
    TextView txt_weekday_ta;
    @BindView(R.id.txt_setuptime_toan)
    TextView txt_setuptime_toan;
    @BindView(R.id.txt_setuptime_tv)
    TextView txt_setuptime_tv;
    @BindView(R.id.txt_setuptime_ta)
    TextView txt_setuptime_ta;
    List<String> mLisWeek = new ArrayList<>();
    List<String> mLisTime = new ArrayList<>();
    @BindView(R.id.btn_update_config)
    Button btn_update;
    PresenterSetup mPresenter;
    String pmath_taken_time = "20:00:00", pvietnam_taken_time = "20:00:00", peng_taken_time = "20:00:00",
            pmath_notify = "1", pvietnam_notify = "1", peng_notify = "1",
            pmath_taken_duration = "900", pvietnam_taken_duration = "900", peng_taken_duration = "900", pmath_dy = "MON", pvietnam_dy = "TUE", peng_dy = "WED";

    @Override
    public int setContentViewId() {
        return R.layout.activity_config_sub_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cal = Calendar.getInstance();
        mPresenter = new PresenterSetup(this);
        initAppbar();
        init();
        initData();
        initEvent();
    }

    private Childrens objChildren;

    private void initData() {

        mLisWeek.add("Thứ hai");
        mLisWeek.add("Thứ ba");
        mLisWeek.add("Thứ tư");
        mLisWeek.add("Thứ năm");
        mLisWeek.add("Thứ sáu");
        mLisWeek.add("Thứ bảy");
        mLisWeek.add("Chủ nhật");
        mLisTime.add("10");
        mLisTime.add("20");
        mLisTime.add("30");
        mLisTime.add("40");
        mLisTime.add("50");
        mLisTime.add("60");
        objChildren = getIntent().getParcelableExtra(Constants.KEY_SEND_CHILDREN);
        //objChildren = (Childrens) getIntent().getSerializableExtra(Constants.KEY_SEND_CHILDREN_CONFIG);
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        showDialogLoading();
        if (objChildren != null && objChildren.getsUSERNAME() != null)
            mPresenter.api_get_config_children(sUserMe, objChildren.getsUSERNAME());

    }

    private void initEvent() {
        txt_weekday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisWeek, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_weekday.setText(sResult);

                    }
                });
            }
        });
        txt_weekday_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisWeek, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_weekday_ta.setText(sResult);

                    }
                });
            }
        });
        txt_weekday_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisWeek, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_weekday_tv.setText(sResult);

                    }
                });
            }
        });
        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(txt_time);

            }
        });
        txt_time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(txt_time_tv);
            }
        });
        txt_time_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(txt_time_ta);
            }
        });
        txt_setuptime_toan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisTime, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_setuptime_toan.setText(sResult);
                    }
                });
            }
        });
        txt_setuptime_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisTime, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_setuptime_ta.setText(sResult);
                    }
                });
            }
        });
        txt_setuptime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_pickerdialog(mLisTime, new ClickDialogPicker() {
                    @Override
                    public void onClickYesDialog(String sResult) {
                        txt_setuptime_tv.setText(sResult);
                    }
                });
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvietnam_dy = get_week_day(txt_weekday_tv.getText().toString());
                peng_dy = get_week_day(txt_weekday_ta.getText().toString());
                pmath_dy = get_week_day(txt_weekday.getText().toString());
                pmath_taken_time = txt_time.getText().toString().substring(0, 5) + ":00";
                pvietnam_taken_time = txt_time_tv.getText().toString().substring(0, 5) + ":00";
                peng_taken_time = txt_time_ta.getText().toString().substring(0, 5) + ":00";

                pmath_taken_duration = "" + (Integer.parseInt(txt_setuptime_toan.getText().toString()) * 60);
                pvietnam_taken_duration = "" + (Integer.parseInt(txt_setuptime_tv.getText().toString()) * 60);
                peng_taken_duration = "" + (Integer.parseInt(txt_setuptime_ta.getText().toString()) * 60);

                showDialogLoading();
                sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                if (objChildren.getsUSERNAME() != null)
                    mPresenter.api_config_to_children(sUserMe, objChildren.getsUSERNAME(), pmath_taken_time, "1",
                            pmath_taken_duration, pvietnam_taken_time, "1", pvietnam_taken_duration,
                            peng_taken_time, "1", peng_taken_duration,
                            pmath_dy, pvietnam_dy, peng_dy);
            }
        });
    }

    TextView txt_title;
    String sUserMe, SUserCon;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("Quản lý tài khoản");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        mLisGame = new ArrayList<>();
        mLisGame.add(new Game("Triệu phú tri thức", "", true));
        mLisGame.add(new Game("King of Word", "", true));
        mLisGame.add(new Game("Sudoku", "", true));
        mLisGame.add(new Game("Tính nhanh nhớ lâu", "", true));
        adapter = new AdapterGame(mLisGame, this);
     /*   mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);*/
        mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyleChildren.setHasFixedSize(true);
        recyleChildren.setLayoutManager(mLayoutManager);
        recyleChildren.setItemAnimator(new DefaultItemAnimator());
        recyleChildren.setAdapter(adapter);

        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });
    }

    public void show_pickerdialog(List<String> mLisString, final ClickDialogPicker clickDialog) {

        final BottomSheetDialog dialog =
                new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_string_picker);

        final StringPicker stringPicker = (StringPicker) dialog.findViewById(R.id.string_picker);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_ok);
        stringPicker.setValues(mLisString);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialog.onClickYesDialog(stringPicker.getCurrentValue());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    Calendar cal;
    Date hourFinish;

    /**
     * Hàm hiển thị TimePickerDialog
     */
    public void showTimePickerDialog(final TextView txtTime) {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s = hourOfDay + ":" + minute;
                int hourTam = hourOfDay;
                if (hourTam > 12)
                    hourTam = hourTam - 12;
                String time = hourTam + ":" + minute + (hourOfDay > 12 ? " PM" : " AM");
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hourFinish = cal.getTime();
                txtTime.setText(TimeUtils.StringTimetoDate(hourFinish, "HH:mm"));
               /* txtTime.setText
                        (hourTam + ":" + minute + (hourOfDay > 12 ? " PM" : " AM"));*/
                //lưu giờ thực vào tag
                txtTime.setTag(s);
                //lưu vết lại giờ vào hourFinish


            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên

        String s = "00:00";
   /*     if (s = null) {
            s = "00:00";
        }*/
        String strArr[] = s.split(":");
        int gio = Integer.parseInt(strArr[0]);
        int phut = Integer.parseInt(strArr[1]);
        TimePickerDialog time = new TimePickerDialog(
                ActivityConfigSubUser.this, R.style.DialogTheme, callback, gio, phut, true);
        time.setTitle("Chọn thời gian");
        time.show();
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_get_config_children(List<ConfigChildren> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            ConfigChildren obj = mLis.get(0);
            if (obj.getsMATH_DY() != null) {
                txt_weekday.setText(get_week_day_vi(obj.getsMATH_DY()));
            }
            if (obj.getsENGLISH_DY() != null) {
                txt_weekday_ta.setText(get_week_day_vi(obj.getsENGLISH_DY()));
            }
            if (obj.getsVIETNAMESE_DY() != null) {
                txt_weekday_tv.setText(get_week_day_vi(obj.getsVIETNAMESE_DY()));
            }
            if (obj.getsMATH_TAKEN_TIME() != null) {
                txt_time.setText(get_week_day_vi(obj.getsMATH_TAKEN_TIME().substring(0, 5)));
            }

            if (obj.getsVIETNAMESE_TAKEN_TIME() != null) {
                String s = obj.getsVIETNAMESE_TAKEN_TIME().substring(0, 4);
                txt_time_tv.setText(get_week_day_vi(obj.getsVIETNAMESE_TAKEN_TIME().substring(0, 5)));
            }
            if (obj.getsENGLISH_TAKEN_TIME() != null) {
                txt_time_ta.setText(get_week_day_vi(obj.getsENGLISH_TAKEN_TIME().substring(0, 5)));
            }
            if (obj.getsENGLISH_TAKEN_DURATION() != null) {
                txt_setuptime_ta.setText(""+(Integer.parseInt(obj.getsENGLISH_TAKEN_DURATION())/60));
            }
            if (obj.getsVIETNAMESE_TAKEN_DURATION() != null) {
                txt_setuptime_tv.setText(""+(Integer.parseInt(obj.getsVIETNAMESE_TAKEN_DURATION())/60));
            }
            if (obj.getsMATH_TAKEN_DURATION() != null) {
                txt_setuptime_toan.setText(""+(Integer.parseInt(obj.getsMATH_TAKEN_DURATION())/60));
            }
        }

    }

    public String get_week_day(String input) {
        String sResutl = "";
        switch (input) {
            case "Thứ hai":
                sResutl = "MON";
                break;
            case "Thứ ba":
                sResutl = "TUE";
                break;
            case "Thứ tư":
                sResutl = "WED";
                break;
            case "Thứ năm":
                sResutl = "THU";
                break;
            case "Thứ sáu":
                sResutl = "FRI";
                break;
            case "Thứ bảy":
                sResutl = "SAT";
                break;
            case "Chủ nhật":
                sResutl = "SUN";
                break;
        }
        return sResutl;
    }

    public String get_week_day_vi(String input) {
        String sResutl = "";
        switch (input) {
            case "MON":
                sResutl = "Thứ hai";
                break;
            case "TUE":
                sResutl = "Thứ ba";
                break;
            case "WED":
                sResutl = "Thứ tư";
                break;
            case "THU":
                sResutl = "Thứ năm";
                break;
            case "FRI":
                sResutl = "Thứ sáu";
                break;
            case "SAT":
                sResutl = "Thứ bảy";
                break;
            case "SUN":
                sResutl = "Chủ nhật";
                break;
        }
        return sResutl;
    }
}
