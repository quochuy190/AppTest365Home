package neo.vn.test365home.View.Setup;

import java.util.List;

import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.Models.UserInfo;

public interface ImpSetup {
    interface Presenter {
        void api_get_user_info(String sUserName);

        void api_config_to_children(String sUserMe, String sUserCon, String pmath_take_time, String pmath_notify, String pmath_duration,
                                    String sTv_taken_time, String pTv_notify, String pTV_duration,
                                    String sEng_taken_time, String pEng_notify, String Eng_duration,
                                    String pmath_dy, String pTv_dy, String pENG_dy);

        void api_get_config_children(String sUserMe, String sUserCon);

        void api_payment(String sUserMe, String sMenhgia);

        void api_get_change_pass(String sUserMe, String sPassOld, String sPassNew);

        void api_get_info_children(String sUserMe, String sUserCon);

        void api_update_info_children(String sUserMe, String sUserCon, String sProvince, String sDistrict,
                                      String sSchool, String sLevel, String sClass, String sYear, String sChil_Name,
                                      String sPass, String sAvata);

        void api_get_history_balance(String sUserMe);
    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_user_info(List<UserInfo> mLis);

        void show_config_to_children(List<ErrorApi> mLis);

        void show_get_config_children(List<ConfigChildren> mLis);

        void show_payment(List<ErrorApi> mLis);

        void show_change_pass(List<ErrorApi> mLis);

        void show_get_info_chil(List<Childrens> mLis);

        void show_update_info_chil(List<ErrorApi> mLis);

        void show_get_history_balance(List<HistoryBalance> mLis);
    }
}
