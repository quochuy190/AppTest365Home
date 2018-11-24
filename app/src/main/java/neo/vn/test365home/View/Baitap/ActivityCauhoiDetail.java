package neo.vn.test365home.View.Baitap;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterViewpager;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.CauhoiDetail;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
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
public class ActivityCauhoiDetail extends BaseActivity implements ImpBaitap.View {
    public ViewPager viewPager;
    public AdapterViewpager adapterViewpager;
    @BindView(R.id.txt_current)
    TextView txt_current;
    @BindView(R.id.img_next)
    ImageView img_next;
    @BindView(R.id.img_back_cauhoi)
    ImageView img_back;
    private List<Cauhoi> mLisCauhoi;
    List<CauhoiDetail> mLisCauhoiDetail;
    ExcerciseDetail mExcercise;
    int iCurrentCauhoi = 0;
    PresenterBaitap mPresenter;
    Childrens mChildren;
    String sUserMe;

    @Override
    public int setContentViewId() {
        return R.layout.activity_cauhoi_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = findViewById(R.id.viewpage_cauhoi);
        mPresenter = new PresenterBaitap(this);
        initAppbar();
        initDataCauhoi();
        initData();

        initEvent();
    }

    private void initDataCauhoi() {
        mLisCauhoi = new ArrayList<>();


        //    setupViewPager(mLisCauhoi.get(iCurrentCauhoi));
    }
    TextView txt_title;
    public void initAppbar() {
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
       // txt_title.setText("Xem lại bài tập");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        mExcercise = SharedPrefs.getInstance().get(Constants.KEY_SEND_CAUHOI, ExcerciseDetail.class);
        txt_title.setText("Xem lại bài tập");
        sUserMe = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
        mChildren = SharedPrefs.getInstance().get(Constants.KEY_SEND_CHILDREN_FRAGMENT, Childrens.class);
        if (mExcercise != null && mExcercise.getsID() != null) {
            txt_title.setText(mExcercise.getsSUBJECT_NAME()+  " - "+mExcercise.getsWEEK_NAME());
            showDialogLoadingtime(10000);
            mPresenter.get_api_get_part(sUserMe, mChildren.getsUSERNAME(), mExcercise.getsWEEK_TEST_ID());
        }
    }

    private void initEvent() {

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = viewPager.getCurrentItem();
                if (maxPage > 0)
                    if (current < (maxPage))
                        viewPager.setCurrentItem((current + 1));

            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = viewPager.getCurrentItem();
                if (current > 0)
                    viewPager.setCurrentItem((current - 1));
            }
        });
      /*  img_show_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNotify("Câu hỏi", "Ngày hôm nay là ngày khai trường đầu tiên ở nước Việt Nam Dân chủ Cộng hòa. Tôi đã tưởng tượng thấy trước mắt cái cảnh nhộn nhịp tưng bừng của ngày tựu trường ở khắp các nơi. Các em hết thảy đều vui vẻ vì sau mấy tháng giời nghỉ học, sau bao nhiêu cuộc chuyển biến khác thường, các em lại được gặp thầy gặp bạn. Nhưng sung sướng hơn nữa, từ giờ phút này giở đi, các em bắt đầu được nhận một nền giáo dục hoàn toàn Việt nam. Các em được hưởng sự may mắn đó là nhờ sự hi sinh của biết bao nhiêu đồng bào các em. Vậy các em nghĩ sao?\n" +
                        "Trong năm học tới đây, các em hãy cố gắng, siêng năng học tập, ngoan ngoãn, nghe thầy, yêu bạn. Sau 80 năm giời nô lệ làm cho nước nhà bị yếu hèn, ngày nay chúng ta cần phải xây dựng lại cơ đồ mà tổ tiên đã để lại cho chúng ta, làm sao cho chúng ta theo kịp các nước khác trên hoàn câu. Trong công cuộc kiến thiết đó, nước nhà trông mong chờ đợi ở các em rất nhiều. Non sông Việt nam có trở nên tươi đẹp được hay không, dân tộc Việt nam có bước tới đài vinh quang để sánh vai với các cường quốc năm châu được hay không, chính là nhờ một phần lớn ở công học tập của các em. Ngày hôm nay, nhân buổi tựu trường của các em, tôi chỉ biết chúc các em một năm đầy vui vẻ và đầy kết quả tốt.\n");
            }
        });*/


    }

    public void showDialogNotify(String title, String message) {
        final Dialog dialog_yes = new Dialog(this);
        //dialog_yes.setCancelable(false);
        dialog_yes.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_yes.setContentView(R.layout.dialog_cauhoi);
        dialog_yes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txt_title = (TextView) dialog_yes.findViewById(R.id.txt_warning_title);
        TextView txt_message = (TextView) dialog_yes.findViewById(R.id.txt_warning_message);
        TextView btn_ok = (TextView) dialog_yes.findViewById(R.id.btn_warning_ok);
        TextView btn_cancel = (TextView) dialog_yes.findViewById(R.id.btn_warning_cancel);
        View view_warning = (View) dialog_yes.findViewById(R.id.view_warning);

        txt_title.setText(title);
        txt_message.setText(message);
        view_warning.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        // txt_buysongs.setText(Html.fromHtml("Để hoàn tất đăng ký dịch vụ RingTunes, Quý khách vui lòng thực hiện thao tác soạn tin nhắn <font color='#060606'>\"Y2 gửi 9194\"</font> từ số điện thoại giá cước: 3.000Đ/7 ngày. Cảm ơn Quý khách!"));
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_yes.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_yes.dismiss();

            }
        });
        dialog_yes.show();
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
    }

    int maxPage = 0;

    @Override
    public void show_list_get_part(List<Cauhoi> mLis) {
        if (mLis != null&&mLis.get(0).getsERROR().equals("0000")) {
            adapterViewpager = new AdapterViewpager(getSupportFragmentManager());
            maxPage = 0;
            for (int j = 0; j < mLis.size(); j++) {
                Cauhoi obj = mLis.get(j);
                if (obj.getLisInfo() != null) {
                    for (int i = 0; i < obj.getLisInfo().size(); i++) {
                        maxPage++;
                        obj.getLisInfo().get(i).setsNumberDe("" + (j + 1));
                        obj.getLisInfo().get(i).setsSubNumberCau("" + (i + 1));
                        obj.getLisInfo().get(i).setsCauhoi_huongdan(obj.getsHUONGDAN());
                        if (obj.getsKIEU().equals("1") || obj.getsKIEU().equals("10"))
                            adapterViewpager.addFragment(FragmentCauhoi.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        else if (obj.getsKIEU().equals("2"))
                            adapterViewpager.addFragment(FragmentCauhoi.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        else if (obj.getsKIEU().equals("3"))
                            adapterViewpager.addFragment(FragmentCauhoi.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        else if (obj.getsKIEU().equals("4")) {
                            adapterViewpager.addFragment(FragmentCauhoiSapxep.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        } else if (obj.getsKIEU().equals("5") || obj.getsKIEU().equals("11")) {
                            adapterViewpager.addFragment(FragmentCauhoiNoicau.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        } else if (obj.getsKIEU().equals("6")) {
                            adapterViewpager.addFragment(FragmentCauhoiDienvaochotrong.newInstance(obj.getLisInfo().get(i)), obj.getsERROR());
                        }
                    }
                }

            }
            viewPager.setOffscreenPageLimit(maxPage);
            viewPager.setAdapter(adapterViewpager);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    // txt_current.setText("Câu " + (position + 1) + "/" + maxPage);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
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
}
