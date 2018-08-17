package neo.vn.test365home.View.Baitap;

import java.util.List;

import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.ReportExcercise;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 8/2/2018
 * @updated 8/2/2018
 * @modified by
 * @updated on 8/2/2018
 * @since 1.0
 */
public interface ImpBaitap {
    interface Presenter {
        void get_api_list_get_children(String sUserMe);

        void get_api_weektest(String sUserMe, String sIdKhoi, String sObjective, String mChildrenId);

        void get_api_buy_excercise(String sUserMe, String sUserCon, String sChuoimade, String sIdmon);

        void get_api_list_buy(String sUserMe, String sUserCon, String sIdMon, String sIdKhoi);

        void get_api_des_excercise(String sUserMe, String sUserCon, String sIdDebai);

        void get_api_get_part(String sUserMe, String sUserCon, String sIdDebai);

        void get_api_report_excercise(String sUserMe, String sUserCon, String sIdDebai);

        void get_api_get_sticker(String sUserMe, String sIdKhoi);
        void get_api_gift_sticker(String sUserMe, String sUserCon, String sIdDebai, String sIdSticker);
        void get_api_mt_comment(String sUserMe, String sUserCon, String sIdDebai, String sNoidung);

    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_list_children(List<Childrens> mLis);

        void show_list_week_test(List<ObjTuanhoc> mList);

        void show_list_buy_excercise(List<ErrorApi> mLis);

        void show_list_list_buy(List<TuanDamua> mLis);

        void show_list_des_excercise(List<ExcerciseDetail> mLis);

        void show_list_get_part(List<Cauhoi> mLis);

        void show_list_report_excercise(List<ReportExcercise> mLis);

        void show_list_get_sticker(List<Sticker> mLis);

        void show_list_gift_sticker(List<ErrorApi> mLis);

        void show_list_mt_comment(List<ErrorApi> mLis);
    }
}
