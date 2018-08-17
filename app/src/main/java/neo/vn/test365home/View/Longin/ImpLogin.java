package neo.vn.test365home.View.Longin;

import java.util.List;

import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/29/2018
 * @updated 7/29/2018
 * @modified by
 * @updated on 7/29/2018
 * @since 1.0
 */
public interface ImpLogin {
    interface Presenter{
        void api_login(String sPassWord, String sUserName, String sAppVersion, String sDeviceModel,
                       String sDeviceType, String sOsVersion, String sTokenkey);
        void api_register(String sUserName, String sPassWord);
        void api_update_info(String sUserName, String sFullname, String sPhone, String sEmail, String sAvata);
        void api_create_children(String sUserName, String sIdTinh, String sIdQuan, String sIdTruong,
                                 String sIdKhoi, String sIdNam, String sLop, String sFullName, String sAvata,
                                 String sUsernameChildren, String sPassChildren);
        void api_lis_children(String sUserName);
    }
    interface View{
        void show_api_register(List<ErrorApi> mLis);
        void create_sub_user(List<ErrorApi> mLis);
        void show_api_login(List<Login> mLis);
        void show_api_update_info(List<Login> mLis);
        void show_error_api(List<ErrorApi> mLis);
        void show_lis_children(List<Childrens> mLis);
    }
}
