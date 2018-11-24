package neo.vn.test365home.View.Setup;

import java.util.List;

import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.HistoryBalance;

public class ImpPayCard {
    interface Presenter {
        void api_pay_card(String sUserMe, String sCodeNumber);
        void api_refcode(String sUserMe, String sCodeNumber, String UUID);
    }

    interface View {
        void show_error_api(List<ErrorApi> mList);

        void show_pay_card(List<HistoryBalance> mList);

        void show_refcode(List<HistoryBalance> mList);
    }
}
