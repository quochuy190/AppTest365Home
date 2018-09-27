package neo.vn.test365home.View.Setup;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hotchemi.stringpicker.StringPicker;
import neo.vn.test365home.Adapter.AdapterGame;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Listener.ClickDialogPicker;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Game;
import neo.vn.test365home.R;

public class ActivityConfigSubUser extends BaseActivity {
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
    List<String> mLisWeek = new ArrayList<>();

    @Override
    public int setContentViewId() {
        return R.layout.activity_config_sub_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        init();
        initData();
        initEvent();
    }

    private void initData() {
        mLisWeek.add("Thứ hai");
        mLisWeek.add("Thứ ba");
        mLisWeek.add("Thứ tư");
        mLisWeek.add("Thứ năm");
        mLisWeek.add("Thứ sáu");
        mLisWeek.add("Thứ bảy");
        mLisWeek.add("Chủ nhật");
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
    }

    TextView txt_title;

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
    /*    final Dialog dialog_yes = new Dialog(this);
        dialog_yes.setCancelable(false);
        dialog_yes.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_yes.setContentView(R.layout.dialog_string_picker);
        dialog_yes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final StringPicker stringPicker = (StringPicker) dialog_yes.findViewById(R.id.string_picker);
        Button btnOK = (Button) dialog_yes.findViewById(R.id.btn_ok);
        stringPicker.setValues(mLisString);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialog.onClickYesDialog(stringPicker.getCurrentValue());
                dialog_yes.dismiss();
            }
        });
        dialog_yes.show();
*/
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
}
