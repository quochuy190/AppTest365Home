package neo.vn.test365home.View.Baitap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterListSticker;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.ReportExcercise;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;

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
    @BindView(R.id.btn_send_comment)
    ImageView btn_send_comment;
    @BindView(R.id.edt_content_comment)
    EditText edt_content_comment;
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

    private void initData() {
        lisSticker = new ArrayList<>();
        String sMonhoc = getIntent().getStringExtra(Constants.KEY_SEND_OBJECTIVE);
        setTexMonhoc(sMonhoc);
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mTuanDamua = (TuanDamua) getIntent().getSerializableExtra(Constants.KEY_SEND_ID_WEEKTEST);
        mChildren = SharedPrefs.getInstance()
                .get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
        showDialogLoading();
        mPresenter.get_api_des_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
        mPresenter.get_api_report_excercise(sUserMe, mChildren.getsUSERNAME(), mTuanDamua.getsWEEK_TEST_ID());
        mPresenter.get_api_get_sticker(sUserMe,mChildren.getsID_LEVEL());
        if (mChildren != null)
            txt_title.setText(mChildren.getsFULLNAME());
        Glide.with(this).load(R.drawable.sticker).into(img_sticker);
        txt_mota.setText(mTuanDamua.getsREQUIREMENT());
        txt_khoi.setText(mChildren.getsID_LEVEL());
        txt_tuan.setText(mTuanDamua.getsWEEK_TEST_ID());
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
                mPresenter.get_api_mt_comment(sUserMe, sUserCon, mEX.getsID(),
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
                        lisSticker,dialog.getContext() );
                horizontalAdapter.setOnIListener(new ItemClickListener() {
                    @Override
                    public void onClickItem(int position, Object item) {
                        dialog.dismiss();
                    }
                });
                horizontalList.setAdapter(horizontalAdapter);
                dialog.show();
            }
        });
        txt_xembailam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBaitapDetail.this, ActivityCauhoiDetail.class);
                SharedPrefs.getInstance().put(Constants.KEY_SEND_CAUHOI, mEX);
                startActivity(intent);
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

    }

    @Override
    public void show_list_children(List<Childrens> mLis) {

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
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000"))
            setInfo(mLis.get(0));
    }

    @Override
    public void show_list_get_part(List<Cauhoi> mLis) {

    }

    @Override
    public void show_list_report_excercise(List<ReportExcercise> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")){

        }

    }

    @Override
    public void show_list_get_sticker(List<Sticker> mLis) {
        hideDialogLoading();
        if (mLis!=null&&mLis.get(0).getsERROR().equals("0000")){
            lisSticker.addAll(mLis);
        }
    }

    @Override
    public void show_list_gift_sticker(List<ErrorApi> mLis) {

    }

    @Override
    public void show_list_mt_comment(List<ErrorApi> mLis) {
        hideDialogLoading();
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                Toast.makeText(this, "Nhận xét con thành công", Toast.LENGTH_SHORT).show();
            } else {
                showDialogNotify("Thông báo", mLis.get(0).getsRESULT());
            }
        }
    }

    private void setInfo(ExcerciseDetail excerciseDetail) {
        if (excerciseDetail != null) {
            mEX = excerciseDetail;
            txt_khoi.setText(excerciseDetail.getsLEVEL_ID());
            txt_nam.setText(excerciseDetail.getsYEAR_NAME());
            txt_tuan.setText(excerciseDetail.getsWEEK_ID());
          //  setTexMonhoc(excerciseDetail.getsSUBJECT_ID());
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
