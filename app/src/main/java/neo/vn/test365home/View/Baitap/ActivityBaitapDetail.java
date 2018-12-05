package neo.vn.test365home.View.Baitap;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterListSticker;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Config;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;
import neo.vn.test365home.Untils.TimeUtils;
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
public class ActivityBaitapDetail extends BaseActivity implements View.OnClickListener, ImpBaitap.View {
    @BindView(R.id.txt_xembailam)
    TextView txt_xembailam;
    @BindView(R.id.txt_nam)
    TextView txt_nam;
    @BindView(R.id.txt_khoi)
    TextView txt_khoi;
    @BindView(R.id.txt_tuan)
    TextView txt_tuan;
    @BindView(R.id.txt_mon)
    TextView txt_mon;
    @BindView(R.id.txt_mota)
    TextView txt_mota;
    PresenterBaitap mPresenter;
    String sUserCon;
    String sIdDebai;
    Childrens mChildren;
    TuanDamua mTuanDamua;
    String sUserMe;
    @BindView(R.id.img_sticker)
    ImageView img_sticker;
    private ExcerciseDetail mEX;
    @BindView(R.id.txt_tangsticker)
    TextView txt_tangsticker;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.btn_send_comment)
    ImageView btn_send_comment;
    @BindView(R.id.edt_content_comment)
    EditText edt_content_comment;
    @BindView(R.id.constrain_1)
    ConstraintLayout constrain_1;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;
    List<Sticker> lisSticker;
    @Override
    public int setContentViewId() {
        return R.layout.activity_baitap_detail;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterBaitap(this);
        initAppbar();
        initData();
        initEvent();

    }
    boolean isClickView = false;
    @SuppressLint("NewApi")
    private void initData() {
        txt_xembailam.setTextColor(getResources().getColor(R.color.button_not_click));
        lisSticker = new ArrayList<>();
        String sMonhoc = getIntent().getStringExtra(Constants.KEY_SEND_OBJECTIVE);
        setTexMonhoc(sMonhoc);
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mTuanDamua = (TuanDamua) getIntent().getSerializableExtra(Constants.KEY_SEND_ID_WEEKTEST);
        mChildren = SharedPrefs.getInstance()
                .get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
        if (isNetwork()) {
            showDialogLoading();
            mPresenter.get_api_get_sticker(sUserMe, mChildren.getsID_LEVEL());
        }
        if (mChildren != null)
            txt_title.setText(mChildren.getsFULLNAME());
        txt_mota.setText(Html.fromHtml("<b>Mục tiêu: </b>" + mTuanDamua.getsREQUIREMENT()));
        if (mTuanDamua.getsNAME() != null) {
            txt_name.setText(Html.fromHtml("<b>Nội dung: </b>" + mTuanDamua.getsNAME()));
        } else {
            switch (sMonhoc) {
                case "1":
                    txt_name.setText("Môn Toán - " + mTuanDamua.getsWEEK_NAME());
                    break;
                case "2":
                    txt_name.setText("Môn Tiếng Việt - " + mTuanDamua.getsWEEK_NAME());
                    break;
                case "3":
                    txt_name.setText("Môn Tiếng Anh - " + mTuanDamua.getsWEEK_NAME());
                    break;
            }

        }
        txt_khoi.setText(mChildren.getsID_LEVEL());
        txt_tuan.setText(mTuanDamua.getsWEEK_TEST_ID());
        if (mTuanDamua.getsTYPETAB().equals("5")) {
            txt_xembailam.setBackground(getResources().getDrawable(R.drawable.spr_btn_xembailam));
            txt_xembailam.setTextColor(getResources().getColor(R.color.white));
            isClickView = true;
            constrain_1.setVisibility(View.VISIBLE);
            ll_comment.setVisibility(View.VISIBLE);
        } else {
            txt_xembailam.setBackground(getResources().getDrawable(R.drawable.spr_btn_xembailam_notclick));
            isClickView = false;
            txt_xembailam.setTextColor(getResources().getColor(R.color.button_not_click));
            constrain_1.setVisibility(View.GONE);
            ll_comment.setVisibility(View.GONE);
        }
    }
    TextView txt_title;
    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        //txt_title.setText("Chi tiết bài tập");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initEvent() {
        btn_send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogLoading();
                sUserCon = mChildren.getsUSERNAME();
                mPresenter.get_api_mt_comment(sUserMe, sUserCon, mEX.getsWEEK_TEST_ID(),
                        edt_content_comment.getText().toString());
            }
        });
        txt_tangsticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog =
                        new BottomSheetDialog(ActivityBaitapDetail.this);
                dialog.setContentView(R.layout.dialog_sheet_bottom);
                RecyclerView horizontalList = dialog.findViewById(R.id.list_sticker);
                horizontalList.setLayoutManager(new GridLayoutManager(dialog.getContext(),
                        1, GridLayoutManager.HORIZONTAL,
                        false));
                AdapterListSticker horizontalAdapter = new AdapterListSticker(
                        lisSticker, dialog.getContext());
                horizontalAdapter.setOnIListener(new ItemClickListener() {
                    @Override
                    public void onClickItem(int position, Object item) {
                        dialog.dismiss();
                    }
                });
                horizontalList.setAdapter(horizontalAdapter);
                horizontalAdapter.setOnIListener(new ItemClickListener() {
                    @Override
                    public void onClickItem(int position, Object item) {
                        showDialogLoading();
                        Sticker obj = (Sticker) item;
                        sUserCon = mChildren.getsUSERNAME();
                        mPresenter.get_api_gift_sticker(sUserMe, sUserCon, mEX.getsWEEK_TEST_ID(),
                                obj.getsID());
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        txt_xembailam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickView) {
                    Intent intent = new Intent(ActivityBaitapDetail.this, ActivityCauhoiDetail.class);
                    SharedPrefs.getInstance().put(Constants.KEY_SEND_CAUHOI, mEX);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_xembailam:
                //  click(new ActivityListCauhoi());
                break;
        }
    }
    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(ActivityBaitapDetail.this, appCompatActivity.getClass());
        startActivity(intent);
    }
    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_children(List<Childrens> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_week_test(List<ObjTuanhoc> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_list_buy_excercise(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_list_buy(List<TuanDamua> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_des_excercise(List<ExcerciseDetail> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000"))
            setInfo(mLis.get(0));
    }

    @Override
    public void show_list_get_part(List<Cauhoi> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_report_excercise(List<ExcerciseDetail> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            setInfoReport(mLis.get(0));
        }
    }
    @Override
    public void show_list_get_sticker(List<Sticker> mLis) {
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            mPresenter.get_api_des_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
            mPresenter.get_api_report_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
            lisSticker.addAll(mLis);
        }
    }
    @Override
    public void show_list_gift_sticker(List<ErrorApi> mLis) {
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                edt_content_comment.setText("");
                showDialogNotify("Thông báo", "Cảm ơn mẹ, Sticker đã được gửi tới con");
                mPresenter.get_api_des_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
                // Toast.makeText(this, "Nhận xét con thành công", Toast.LENGTH_SHORT).show();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }
    @Override
    public void show_list_mt_comment(List<ErrorApi> mLis) {
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                mPresenter.get_api_des_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
                edt_content_comment.setText("");
                showDialogNotify("Thông báo", "Cảm ơn mẹ, nhận xét đã được gửi tới con");
                // Toast.makeText(this, "Nhận xét con thành công", Toast.LENGTH_SHORT).show();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }
    @BindView(R.id.txt_diem)
    TextView txt_diem;
    @BindView(R.id.txt_time_start)
    TextView txt_time_start;
    @BindView(R.id.txt_time_lambai)
    TextView txt_time_lambai;
    @BindView(R.id.txt_speed_lambai)
    TextView txt_speed_lambai;
    @BindView(R.id.txt_sobanlamcung)
    TextView txt_sobanlamcung;
    @BindView(R.id.txt_sobancungtruong)
    TextView txt_sobancungtruong;
    @BindView(R.id.txt_sobancunglop)
    TextView txt_sobancunglop;
    @BindView(R.id.txt_point_max)
    TextView txt_point_max;
    @BindView(R.id.txt_point_average)
    TextView txt_point_average;
    @BindView(R.id.txt_point_min)
    TextView txt_point_min;
    @BindView(R.id.txt_nhanxet)
    TextView txt_nhanxet;
    @BindView(R.id.lable_nhanxet)
    TextView lable_nhanxet;
    private void setInfo(ExcerciseDetail excerciseDetail) {
        if (excerciseDetail != null) {
            if (excerciseDetail.getsPOINT() != null && excerciseDetail.getsPOINT().length() > 0) {
                String s = StringUtil.format_point(Float.parseFloat(excerciseDetail.getsPOINT()));
                txt_diem.setText(s + " ĐIỂM");
            }
            if (excerciseDetail.getsLEVEL_ID() != null && excerciseDetail.getsLEVEL_ID().length() > 0)
                txt_khoi.setText(excerciseDetail.getsLEVEL_ID());
            mEX = excerciseDetail;
            if (excerciseDetail.getsYEAR_NAME() != null && excerciseDetail.getsYEAR_NAME().length() > 0)
                txt_nam.setText(excerciseDetail.getsYEAR_NAME());
            if (excerciseDetail.getsYEAR_NAME() != null && excerciseDetail.getsYEAR_NAME().length() > 0)
                txt_tuan.setText(excerciseDetail.getsWEEK_ID());
            if (excerciseDetail.getsSTART_TAKE_TIME() != null) {
                txt_time_start.setText("Bắt đầu: " + TimeUtils.convent_date(excerciseDetail.getsSTART_TAKE_TIME(),
                        "yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy HH:mm"));
            }
            if (excerciseDetail.getsDURATION() != null && excerciseDetail.getsDURATION().length() > 0) {
                int iDuration = Integer.parseInt(excerciseDetail.getsDURATION()) * 1000;
                txt_time_lambai.setText("Thời gian làm bài: "
                        + TimeUtils.formatTime_lambai(iDuration));
            }
            txt_speed_lambai.setText("Tốc độ làm bài");
            //  setTexMonhoc(excerciseDetail.getsSUBJECT_ID());
            if (lisSticker != null && lisSticker.size() > 0) {
                for (Sticker obj : lisSticker) {
                    if (excerciseDetail.getsSTICKER_ID().equals(obj.getsID())) {
                        img_sticker.setVisibility(View.VISIBLE);
                        Glide.with(this).load(Config.URL_IMAGE + obj.getsPATH()).into(img_sticker);
                    }
                }
            }
            if (excerciseDetail.getsRECOMMENT_MOTHER() != null && excerciseDetail.getsRECOMMENT_MOTHER().length() > 0) {
                lable_nhanxet.setVisibility(View.VISIBLE);
                txt_nhanxet.setText(excerciseDetail.getsRECOMMENT_MOTHER());
            } else {
                lable_nhanxet.setVisibility(View.INVISIBLE);
                txt_nhanxet.setText("Mời mẹ nhận xét và tặng sticker động viên con");
            }
        }
    }
    private void setInfoReport(ExcerciseDetail excerciseDetail) {
        if (excerciseDetail != null) {
            if (excerciseDetail.getsCungtruong() != null && excerciseDetail.getsCungtruong().length() > 0) {
                txt_sobancungtruong.setText("Số bạn cùng trường: " + excerciseDetail.getsCungtruong());
            }
            if (excerciseDetail.getsCunglam() != null && excerciseDetail.getsCunglam().length() > 0) {
                txt_sobanlamcung.setText("Số bạn cùng làm bài: " + excerciseDetail.getsCunglam());
            }
            if (excerciseDetail.getsCunglop() != null && excerciseDetail.getsCunglop().length() > 0) {
                txt_sobancunglop.setText("Số bạn cùng lớp: " + excerciseDetail.getsCunglop());
            }
            if (excerciseDetail.getsCaonhat() != null && excerciseDetail.getsCaonhat().length() > 0) {
                String sPointMax = StringUtil.format_point(Float.parseFloat(excerciseDetail.getsCaonhat()));
                txt_point_max.setText(sPointMax + " ĐIỂM");
            }
            if (excerciseDetail.getsTrungbinh() != null && excerciseDetail.getsTrungbinh().length() > 0) {
                String sPointAverage = StringUtil.format_point(Float.parseFloat(excerciseDetail.getsTrungbinh()));
                txt_point_average.setText(sPointAverage + " ĐIỂM");
            }
            if (excerciseDetail.getsThapnhat() != null && excerciseDetail.getsThapnhat().length() > 0) {
                String sPointMax = StringUtil.format_point(Float.parseFloat(excerciseDetail.getsThapnhat()));
                txt_point_min.setText(sPointMax + " ĐIỂM");
            }
        }
    }
    private void setTexMonhoc(String sMon) {
        switch (sMon) {
            case "1":
                txt_mon.setText("TOÁN");
                break;
            case "2":
                txt_mon.setText("TIẾNG VIỆT");
                break;
            case "3":
                txt_mon.setText("TIẾNG ANH");
                break;
        }
    }
}
