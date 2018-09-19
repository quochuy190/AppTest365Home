package neo.vn.test365home.View.Setup;

import java.util.List;

import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.UserInfo;

public interface ImpSetup {
    interface Presenter{
        void api_get_user_info(String sUserName);
    }
    interface View{
        void show_error_api(List<ErrorApi> mLis);
        void show_user_info(List<UserInfo> mLis);

    }
}
