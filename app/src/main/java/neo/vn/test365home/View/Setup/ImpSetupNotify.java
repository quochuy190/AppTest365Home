package neo.vn.test365home.View.Setup;

import java.util.List;

import neo.vn.test365home.Models.ConfigGame;
import neo.vn.test365home.Models.ConfigNotify;
import neo.vn.test365home.Models.ErrorApi;

public interface ImpSetupNotify {
    interface Presenter {
        void api_get_get_cf_notify(String sUserName);

        void api_update_cf_notify(String sUserName, String ptaken_notify, String plate_notify,
                                  String pstart_notify, String pend_notify, String pstart_game_notify
                , String pend_game_notify, String pbuy_exe_notify);

        void api_get_no_children(String sUserName, String sUserCon);

        void api_get_game_children(String sUserName, String sUserCon);

        void api_cf_notify_children(String sUserName, String sUserCon, String sNhaclambai, String sBaolambaimuon,
                                    String sBaoTangSticker, String sMuabaitap);

        void api_cf_game_children(String sUserName, String sUserCon, String sGameTPTT, String sGameSUDOKU,
                                  String sGameKOW, String sGameTNNL);

    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_get_cf_notify(List<ConfigNotify> mLis);

        void show_update_cf_notify(List<ErrorApi> mLis);

        void show_get_cf_notify_chil(List<ConfigNotify> mLis);

        void show_update_cf_notify_chil(List<ErrorApi> mLis);

        void show_get_game_children(List<ConfigGame> mLis);

        void show_cf_game_children(List<ErrorApi> mLis);
    }
}
