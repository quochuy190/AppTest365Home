package neo.vn.test365home.View.Baitap;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterListKhobaitap;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ButtonItemClickListener;
import neo.vn.test365home.Listener.ClickDialog;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.Models.UserInfo;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.Untils.StringUtil;
import neo.vn.test365home.View.Setup.ActivityManageAccount;
import neo.vn.test365home.View.Setup.ImpSetup;
import neo.vn.test365home.View.Setup.PresenterSetup;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 8/3/2018
 * @updated 8/3/2018
 * @modified by
 * @updated on 8/3/2018
 * @since 1.0
 */
public class ActivityKhobaitap extends BaseActivity implements ImpBaitap.View, ImpSetup.View {
    private static final String TAG = "ActivityKhobaitap";
    PresenterBaitap mPresenter;
    AdapterListKhobaitap adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<ObjTuanhoc> mLisBaitap;
    @BindView(R.id.recycle_khobaitap)
    RecyclerView recyleBaitap;
    private Childrens mChildren;
    @BindView(R.id.txt_chontatca)
    TextView txt_chontatca;
    @BindView(R.id.btn_taichocon)
    Button btn_taichocon;
    @BindView(R.id.txt_soluong)
    TextView txt_soluong;
    @BindView(R.id.txt_taikhoan)
    TextView txt_taikhoan;
    @BindView(R.id.txt_price)
    TextView txt_price;
    int iCount = 0;
    int iCount_adapter = 0;
    int iPrice = 0;
    TextView txt_title;
    PresenterSetup mPesenterSetup;

    @Override
    public int setContentViewId() {
        return R.layout.activity_khobaitap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterBaitap(this);
        mPesenterSetup = new PresenterSetup(this);
        initAppbar();
        initData();
        init();
        initEvent();
    }

    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String user;
    boolean isChonAll = false;
    String sChuoimade = "";
    private void initEvent() {
        txt_chontatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCount_adapter = 0;
                if (mLisBaitap.size() > 0 && !isChonAll) {
                    iCount = 0;
                    iPrice = 0;
                    for (int i = 0; i < mLisBaitap.size(); i++) {
                        if (mLisBaitap.get(i).getsSTATUS().equals("0")) {
                            iCount = iCount + 1;
                            iPrice = iPrice + Integer.parseInt(mLisBaitap.get(i).getsPRICE());
                            mLisBaitap.get(i).setsSTATUS("4");
                        } else if (mLisBaitap.get(i).getsSTATUS().equals("4")) {
                            // mLisBaitap.get(i).setsSTATUS("0");
                            iCount = iCount + 1;
                            iPrice = iPrice + Integer.parseInt(mLisBaitap.get(i).getsPRICE());
                        }
                    }
                    isChonAll = true;
                    txt_chontatca.setText("Bỏ chọn");
                    txt_soluong.setText("" + iCount);
                    txt_price.setText(StringUtil.formatNumber("" + iPrice));
                    adapter.notifyDataSetChanged();
                } else if (mLisBaitap.size() > 0 && isChonAll) {
                    for (int i = 0; i < mLisBaitap.size(); i++) {
                        if (mLisBaitap.get(i).getsSTATUS().equals("4")) {
                            mLisBaitap.get(i).setsSTATUS("0");
                        }
                    }
                    iCount = 0;
                    iPrice = 0;
                    isChonAll = false;
                    txt_chontatca.setText("Chọn tất cả");
                    txt_soluong.setText("0");
                    txt_price.setText(StringUtil.formatNumber("0"));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btn_taichocon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sChuoimade="";
                if (mLisBaitap.size() > 0) {
                    for (int i = 0; i < mLisBaitap.size(); i++) {
                        if (mLisBaitap.get(i).getsSTATUS().equals("4")) {
                            if (i != (mLisBaitap.size() - 1)) {
                                sChuoimade = sChuoimade + mLisBaitap.get(i).getsID() + "#";
                            } else {
                                sChuoimade = sChuoimade + mLisBaitap.get(i).getsID();
                            }
                        }
                    }
                }
                if (sChuoimade.length() > 0) {
                    if (iPrice > iTkTotal) {
                        showDialogComfirm_Khobaitap("Thông báo", "Tài khoản của bạn không đủ để thực hiện giao dich",
                                false, new ClickDialog() {
                                    @Override
                                    public void onClickYesDialog() {
                                        startActivity(new Intent(ActivityKhobaitap.this, ActivityManageAccount.class));
                                    }

                                    @Override
                                    public void onClickNoDialog() {

                                    }
                                });
                        return;
                    }
                    showDialogComfirm("Thông báo", "Chi phí tải bài sẽ được trừ vào tài khoản của ba mẹ trên" +
                                    " Home365. Ba mẹ có chắc chắn muốn tải các bài tập này cho con?",
                            false, new ClickDialog() {
                                @Override
                                public void onClickYesDialog() {
                                    showDialogLoading();
                                    String sObjective = getIntent().getStringExtra(Constants.KEY_SEND_OBJECTIVE);
                                    user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
                                    mPresenter.get_api_buy_excercise(user, mChildren.getsUSERNAME(), sChuoimade, sObjective);
                                }

                                @Override
                                public void onClickNoDialog() {

                                }
                            });

                } else showDialogNotify("Thông báo", "Bạn chưa chọn bài tập nào để mua");
            }
        });
    }

    private void initData() {
        mChildren = getIntent().getParcelableExtra(Constants.KEY_SEND_CHILDREN);

        showDialogLoading();
        String sObjective = getIntent().getStringExtra(Constants.KEY_SEND_OBJECTIVE);
        if (mChildren != null) {
            switch (sObjective) {
                case "1":
                    txt_title.setText("Toán lớp " + mChildren.getsID_LEVEL());
                    break;
                case "2":
                    txt_title.setText("Tiếng Việt lớp " + mChildren.getsID_LEVEL());
                    break;
                case "3":
                    txt_title.setText("Tiếng Anh lớp " + mChildren.getsID_LEVEL());
                    break;
            }
        }
        String user = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mPesenterSetup.api_get_user_info(user);
        if (mChildren != null)
            mPresenter.get_api_weektest(user, mChildren.getsID_LEVEL(), sObjective, mChildren.getsUSERNAME());


    }

    private void init() {
        mLisBaitap = new ArrayList<>();
        // mLisBaitap.add(new ObjTuanhoc("", "",""));
        adapter = new AdapterListKhobaitap(mLisBaitap, this, new ButtonItemClickListener() {
            @Override
            public void onClickButtonItem(int position) {
                if (mLisBaitap.get(position).getsSTATUS().equals("0")) {
                    iCount = iCount + 1;
                    iPrice = iPrice + Integer.parseInt(mLisBaitap.get(position).getsPRICE());
                    mLisBaitap.get(position).setsSTATUS("4");
                } else if (mLisBaitap.get(position).getsSTATUS().equals("4")) {
                    if (iCount > 0) {
                        iCount = iCount - 1;
                        iPrice = iPrice - Integer.parseInt(mLisBaitap.get(position).getsPRICE());
                    }
                    mLisBaitap.get(position).setsSTATUS("0");
                }
                txt_soluong.setText("" + iCount);
                txt_price.setText(StringUtil.formatNumber("" + iPrice));
                adapter.notifyDataSetChanged();
            }
        });
       /* mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);*/
        mLayoutManager = new GridLayoutManager(this,
                2, GridLayoutManager.VERTICAL, false);
        recyleBaitap.setHasFixedSize(true);
        recyleBaitap.setLayoutManager(mLayoutManager);
        recyleBaitap.setItemAnimator(new DefaultItemAnimator());
        recyleBaitap.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });
    }

    @Override
    public void show_error_api(List<ErrorApi> mLis) {
        hideDialogLoading();
    }

    int iTkTotal;

    @Override
    public void show_user_info(List<UserInfo> mLis) {
        hideDialogLoading();
        if (mLis != null && mLis.get(0).getsERROR().equals("0000")) {
            UserInfo obj = mLis.get(0);
            int iTkChinh = Integer.parseInt(obj.getsCORE_BALANCE());
            int iTkThuong = Integer.parseInt(obj.getsPROMOTION_BALANCE());
            iTkTotal = iTkChinh + iTkThuong;
            txt_taikhoan.setText(StringUtil.formatNumber("" + iTkTotal));
        }
    }

    @Override
    public void show_config_to_children(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_config_children(List<ConfigChildren> mLis) {

    }

    @Override
    public void show_payment(List<ErrorApi> mLis) {

    }

    @Override
    public void show_change_pass(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_info_chil(List<Childrens> mLis) {

    }

    @Override
    public void show_update_info_chil(List<ErrorApi> mLis) {

    }

    @Override
    public void show_get_history_balance(List<HistoryBalance> mLis) {

    }

    @Override
    public void show_list_children(List<Childrens> mLis) {
        hideDialogLoading();
    }

    @Override
    public void show_list_week_test(List<ObjTuanhoc> mList) {
        hideDialogLoading();
        if (mList != null) {
            if (mList.get(0).getsERROR().equals("0000")) {
                mLisBaitap.addAll(mList);
                Collections.sort(mLisBaitap);
                adapter.notifyDataSetChanged();
                boolean isCheck = false;
                if (mLisBaitap.size() > 0) {
                    for (ObjTuanhoc obj : mLisBaitap) {
                        if (obj.getsSTATUS().equals("0")) {
                            isCheck = true;
                        }
                    }
                }
                if (isCheck) {
                    txt_chontatca.setVisibility(View.VISIBLE);
                } else {
                    txt_chontatca.setVisibility(View.INVISIBLE);
                }
            }
        }


    }

    @Override
    public void show_list_buy_excercise(List<ErrorApi> mLis) {
        if (mLis != null) {
            if (mLis.get(0).getsERROR().equals("0000")) {
                setResult(RESULT_OK, new Intent());
                finish();
            }
        }
        hideDialogLoading();
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

    public void showDialogComfirm_Khobaitap(String title, String message, boolean is_hide_cancel,
                                  final ClickDialog clickDialog) {
        final Dialog dialog_yes = new Dialog(this);
        dialog_yes.setCancelable(false);
        dialog_yes.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_yes.setContentView(R.layout.dialog_warning);
        dialog_yes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txt_title = (TextView) dialog_yes.findViewById(R.id.txt_warning_title);
        TextView txt_message = (TextView) dialog_yes.findViewById(R.id.txt_warning_message);
        TextView btn_ok = (TextView) dialog_yes.findViewById(R.id.btn_warning_ok);
        TextView btn_cancel = (TextView) dialog_yes.findViewById(R.id.btn_warning_cancel);
        View view_warning = (View) dialog_yes.findViewById(R.id.view_warning);
        btn_ok.setText("Nạp tiền");
        txt_title.setText(title);
        txt_message.setText(Html.fromHtml(message));
        // txt_buysongs.setText(Html.fromHtml("Để hoàn tất đăng ký dịch vụ RingTunes, Quý khách vui lòng thực hiện thao tác soạn tin nhắn <font color='#060606'>\"Y2 gửi 9194\"</font> từ số điện thoại giá cước: 3.000Đ/7 ngày. Cảm ơn Quý khách!"));
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_yes.dismiss();
                clickDialog.onClickYesDialog();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_yes.dismiss();
                clickDialog.onClickNoDialog();
            }
        });
        if (is_hide_cancel) {
            view_warning.setVisibility(View.GONE);
            btn_cancel.setVisibility(View.GONE);
        } else {
            view_warning.setVisibility(View.VISIBLE);
            btn_cancel.setVisibility(View.VISIBLE);
        }
        dialog_yes.show();

    }
}
