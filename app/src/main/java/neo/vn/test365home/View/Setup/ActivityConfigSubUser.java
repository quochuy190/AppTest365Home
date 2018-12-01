package neo.vn.test365home.View.Setup;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import neo.vn.test365home.Adapter.AdapterSetupNotify;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ClickDialogPicker;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Listener.SwitchChangeListenner;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ConfigGame;
import neo.vn.test365home.Models.ConfigNotify;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Game;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.ItemSetupNotify;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.TimeUtils;

public class ActivityConfigSubUser extends BaseActivity implements ImpSetup.View, ImpSetupNotify.View {
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
    @BindView(R.id.scroll_config)
    NestedScrollView scroll_config;
    List<String> mLisWeek = new ArrayList<>();
    List<String> mLisTime = new ArrayList<>();
    @BindView(R.id.btn_update_config)
    Button btn_update;
    @BindView(R.id.btn_capnhatthongtin)
    Button btn_capnhatthongtin;
    @BindView(R.id.btn_setup_cofig)
    Button btn_setup_cofig;
    @BindView(R.id.btn_update_config_game)
    Button btn_update_config_game;
    @BindView(R.id.recycle_listnotify)
    RecyclerView recycle_listnotify;
    PresenterSetup mPresenter;
    PresenterSetupNotify mPresenterNotify;
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
        mPresenterNotify = new PresenterSetupNotify(this);
        initAppbar();
        init();
        initEvent();
        initData();
        initDataNotify();
        initNotify();
    }

    AdapterSetupNotify adapterMother;
    RecyclerView.LayoutManager mLayoutMother;
    List<ItemSetupNotify> mLisChil;

    private void initNotify() {
        mLayoutMother = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapterMother = new AdapterSetupNotify(mLisChil, this, new SwitchChangeListenner() {
            @Override
            public void onListennerSwitchChange(int position, boolean isChecked) {
                mLisChil.get(position).setOnOff(isChecked);
            }
        });
        recycle_listnotify.setNestedScrollingEnabled(false);
        recycle_listnotify.setHasFixedSize(true);
        recycle_listnotify.setLayoutManager(mLayoutMother);
        recycle_listnotify.setItemAnimator(new DefaultItemAnimator());
        recycle_listnotify.setAdapter(adapterMother);
        adapterMother.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
               /* boolean isClick = mLisMother.get(position).isOnOff();
                mLisMother.get(position).setOnOff(!isClick);
                adapterMother.notifyDataSetChanged();*/
            }
        });
    }

    private void initDataNotify() {
        mLisChil = new ArrayList<>();
        mLisChil.add(new ItemSetupNotify("Thông báo nhắc làm bài tập",
                "Gửi thông báo khi đếm giờ làm bài tập của con mà bài tập đó chưa làm", false));
        mLisChil.add(new ItemSetupNotify("Thông báo nhắc làm bài tập muộn",
                "Gửi thông báo khi có bài tập muộn mà con chưa làm", true));
        mLisChil.add(new ItemSetupNotify("Thông báo mẹ tặng sticker",
                "Gửi thông báo khi mẹ gửi sticker cho con", false));
        mLisChil.add(new ItemSetupNotify("Thông báo mẹ đã tải bài tập",
                "Gửi thông báo khi mẹ thông báo mẹ mua bài tập cho con", true));
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        //   mPresenter.api_get_get_cf_notify(sUserMe);
    }

    private Childrens objChildren;
    private boolean isAddSubUser = false;
    String sUserCon = "";

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
        objChildren = App.mChilSetup;
        //objChildren = (Childrens) getIntent().getSerializableExtra(Constants.KEY_SEND_CHILDREN_CONFIG);

        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        isAddSubUser = getIntent().getBooleanExtra(Constants.KEY_SEND_ADD_ADDSUBUSER, false);
        if (isNetwork()){
            if (isAddSubUser) {
                sUserCon = getIntent().getStringExtra(Constants.KEY_SEND_USERCON_ADD_ADDSUBUSER);
                showDialogLoading();
                if (sUserCon != null && sUserCon.length() > 0) {
                    mPresenter.api_get_config_children(sUserMe, sUserCon);
                    mPresenter.api_get_info_children(sUserMe, sUserCon);
                    mPresenterNotify.api_get_no_children(sUserMe, sUserCon);
                    mPresenterNotify.api_get_game_children(sUserMe, sUserCon);
                }
            } else {
                showDialogLoading();
                if (objChildren != null && objChildren.getsUSERNAME() != null) {
                    sUserCon = objChildren.getsUSERNAME();
                    mPresenter.api_get_config_children(sUserMe, objChildren.getsUSERNAME());
                    mPresenter.api_get_info_children(sUserMe, objChildren.getsUSERNAME());
                    mPresenterNotify.api_get_no_children(sUserMe, objChildren.getsUSERNAME());
                    mPresenterNotify.api_get_game_children(sUserMe, objChildren.getsUSERNAME());
                }
            }
        }
        //txt_time_tv.setText("dfskd;fs");
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
                        txt_setuptime_toan.setText(sResult + " phút");
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
                        txt_setuptime_ta.setText(sResult + " phút");
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
                        txt_setuptime_tv.setText(sResult + " phút");
                    }
                });
            }
        });
        btn_setup_cofig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()){
                    pvietnam_dy = get_week_day(txt_weekday_tv.getText().toString());
                    peng_dy = get_week_day(txt_weekday_ta.getText().toString());
                    pmath_dy = get_week_day(txt_weekday.getText().toString());
                    pmath_taken_time = txt_time.getText().toString().substring(0, 5) + ":00";
                    pvietnam_taken_time = txt_time_tv.getText().toString().substring(0, 5) + ":00";
                    peng_taken_time = txt_time_ta.getText().toString().substring(0, 5) + ":00";

                    pmath_taken_duration = "" + (Integer.parseInt(txt_setuptime_toan.getText().toString().replaceAll(" phút", "")) * 60);
                    pvietnam_taken_duration = "" + (Integer.parseInt(txt_setuptime_tv.getText().toString().replaceAll(" phút", "")) * 60);
                    peng_taken_duration = "" + (Integer.parseInt(txt_setuptime_ta.getText().toString().replaceAll(" phút", "")) * 60);
                    showDialogLoading();
                    sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                    if (objChildren.getsUSERNAME() != null)
                        mPresenter.api_config_to_children(sUserMe, objChildren.getsUSERNAME(), pmath_taken_time, "1",
                                pmath_taken_duration, pvietnam_taken_time, "1", pvietnam_taken_duration,
                                peng_taken_time, "1", peng_taken_duration,
                                pmath_dy, pvietnam_dy, peng_dy);
                }

            }
        });
        btn_capnhatthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityConfigSubUser.this, ActivityUpdateSubUser.class);
                startActivityForResult(intent, Constants.RequestCode.GET_UPDATE_USER_CON);
                //  startActivityForResult(intent, );
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()) {
                    showDialogLoading();
                    String sAlertTaken = "", sAlertLate = "", sAlertSticker = "", sAlert_Mother_Buy = "";
                    if (mLisChil.get(0).isOnOff()) {
                        sAlertTaken = "1";
                    } else
                        sAlertTaken = "0";
                    if (mLisChil.get(1).isOnOff()) {
                        sAlertLate = "1";
                    } else
                        sAlertLate = "0";
                    if (mLisChil.get(2).isOnOff()) {
                        sAlertSticker = "1";
                    } else
                        sAlertSticker = "0";
                    if (mLisChil.get(3).isOnOff()) {
                        sAlert_Mother_Buy = "1";
                    } else
                        sAlert_Mother_Buy = "0";
                    mPresenterNotify.api_cf_notify_children(sUserMe, sUserCon, sAlertTaken, sAlertLate,
                            sAlertSticker, sAlert_Mother_Buy);
                }

            }
        });
        btn_update_config_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()) {
                    showDialogLoading();
                    String sGameTPTT = "", sGameSuDoKu = "", sGameKOW = "", sGameTNNL = "";
                    if (mLisGame.get(0).isOff()) {
                        sGameTPTT = "1";
                    } else
                        sGameTPTT = "0";
                    if (mLisGame.get(1).isOff()) {
                        sGameSuDoKu = "1";
                    } else
                        sGameSuDoKu = "0";
                    if (mLisGame.get(2).isOff()) {
                        sGameKOW = "1";
                    } else
                        sGameKOW = "0";
                    if (mLisGame.get(3).isOff()) {
                        sGameTNNL = "1";
                    } else
                        sGameTNNL = "0";
                    mPresenterNotify.api_cf_game_children(sUserMe, sUserCon, sGameTPTT, sGameSuDoKu,
                            sGameKOW, sGameTNNL);
                }

            }
        });
    }


    String sUserMe, SUserCon;
    TextView txt_title;

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText("CẤU HÌNH TÀI KHOẢN CON");
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
        mLisGame.add(new Game("Triệu phú tri thức", "", true,
                R.drawable.icon_game_tptt, R.string.detail_game_tptt));
        mLisGame.add(new Game("Sudoku", "", true,
                R.drawable.icon_game_sudoku, R.string.detail_game_sudoku));
        mLisGame.add(new Game("King of Word", "", false,
                R.drawable.icon_game_kigofword, R.string.detail_game_kow));
        mLisGame.add(new Game("Tính nhanh nhớ lâu", "", false,
                R.drawable.icon_game_tinhnhanh, R.string.detail_game_tnnl));
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
                Game obj = (Game) item;
                Intent intent = new Intent(ActivityConfigSubUser.this, ActivityGameDetail.class);
                intent.putExtra(Constants.KEY_SEND_GAME_DETAIL, obj);
                startActivity(intent);
            }
        });
        adapter.setOnSwitchChangeListenner(new SwitchChangeListenner() {
            @Override
            public void onListennerSwitchChange(int position, boolean isChecked) {
                mLisGame.get(position).setOff(isChecked);
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
        // showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
    }

    @Override
    public void show_get_cf_notify(List<ConfigNotify> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_update_cf_notify(List<ErrorApi> mLis) {
      /*  hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mPresenterNotify.api_get_no_children(sUserMe, sUserCon);
            showDialogNotify("Thông báo", "Cập nhật thông báo app con thành công");
        }*/
    }

    @Override
    public void show_get_cf_notify_chil(List<ConfigNotify> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            if (mLis.get(0).getsALERT_LATE().equals("1")) {
                mLisChil.get(1).setOnOff(true);
            } else
                mLisChil.get(1).setOnOff(false);
            if (mLis.get(0).getsALERT_TAKEN().equals("1")) {
                mLisChil.get(0).setOnOff(true);
            } else
                mLisChil.get(0).setOnOff(false);
            if (mLis.get(0).getsALERT_STICKER().equals("1")) {
                mLisChil.get(2).setOnOff(true);
            } else
                mLisChil.get(2).setOnOff(false);
            if (mLis.get(0).getsALERT_MOTHER_BUY().equals("1")) {
                mLisChil.get(3).setOnOff(true);
            } else
                mLisChil.get(3).setOnOff(false);

            adapterMother.notifyDataSetChanged();
        }
    }

    @Override
    public void show_update_cf_notify_chil(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mPresenterNotify.api_get_no_children(sUserMe, sUserCon);
            showDialogNotify("Thông báo", "Cập nhật thông báo app con thành công");
        } else showDialogNotify("Lỗi", mLis.get(0).getsRESULT());
    }

    @Override
    public void show_get_game_children(List<ConfigGame> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            if (mLis.get(0).getsGAME_TPTT().equals("1")) {
                mLisGame.get(0).setOff(true);
            } else
                mLisGame.get(0).setOff(false);
            if (mLis.get(0).getsGAME_SUDOKU().equals("1")) {
                mLisGame.get(1).setOff(true);
            } else
                mLisGame.get(1).setOff(false);
            if (mLis.get(0).getsGAME_KOW().equals("1")) {
                mLisGame.get(2).setOff(true);
            } else
                mLisGame.get(2).setOff(false);
            if (mLis.get(0).getsGAME_TNNL().equals("1")) {
                mLisGame.get(3).setOff(true);
            } else
                mLisGame.get(3).setOff(false);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void show_cf_game_children(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mPresenterNotify.api_get_game_children(sUserMe, sUserCon);
            showDialogNotify("Thông báo", "Cập nhật cài đặt trò chơi thành công");
        } else showDialogNotify("Lỗi", mLis.get(0).getsRESULT());
    }

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            if (objChildren != null && objChildren.getsUSERNAME() != null)
                mPresenter.api_get_config_children(sUserMe, objChildren.getsUSERNAME());
        } else {
            showDialogNotify("Lỗi", mLis.get(0).getsRESULT());
        }
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
            if (obj.getsENGLISH_TAKEN_DURATION() != null) {
                txt_setuptime_ta.setText("" + (Integer.parseInt(obj.getsENGLISH_TAKEN_DURATION()) / 60) + " phút");
            }
            if (obj.getsVIETNAMESE_TAKEN_DURATION() != null) {
                txt_setuptime_tv.setText("" + (Integer.parseInt(obj.getsVIETNAMESE_TAKEN_DURATION()) / 60) + " phút");
            }
            if (obj.getsMATH_TAKEN_DURATION() != null) {
                txt_setuptime_toan.setText("" + (Integer.parseInt(obj.getsMATH_TAKEN_DURATION()) / 60) + " phút");
            }
            if (obj.getsMATH_TAKEN_TIME() != null) {
                txt_time.setText(obj.getsMATH_TAKEN_TIME().substring(0, 5));
            }
            if (obj.getsVIETNAMESE_TAKEN_TIME() != null) {
                txt_time_tv.setText(obj.getsVIETNAMESE_TAKEN_TIME().substring(0, 5));
            }
            if (obj.getsENGLISH_TAKEN_TIME() != null) {
                txt_time_ta.setText(obj.getsENGLISH_TAKEN_TIME().substring(0, 5));
            }
        }
    }

    @Override
    public void show_payment(List<ErrorApi> mLis) {

    }

    @Override
    public void show_change_pass(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_info_chil(List<Childrens> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000"))
            App.mChilSetup = mLis.get(0);
    }

    @Override
    public void show_update_info_chil(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_history_balance(List<HistoryBalance> mLis) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        scroll_config.scrollTo(0, 0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GET_UPDATE_USER_CON && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
        }
    }
}
