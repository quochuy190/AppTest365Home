package neo.vn.test365home.View.Thongke;

import java.util.List;

import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Chart_To_Subject;
import neo.vn.test365home.Models.ErrorApi;

public interface ImpThongke {
    interface Presenter {
        void api_get_chart_to_subject_all(String sUserName, String sUserCon);

        void api_get_chart_to_subject(String sUserName, String sUserCon);

        void api_get_bxh_week_chart(String sUserName, String sUserCon, String sDate);

        void api_get_bxh_month_chart(String sUserName, String sUserCon, String sDate);

        void api_get_bxh_year_chart(String sUserName, String sUserCon, String sDate);
    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_chart_to_subject_all(List<Chart_To_Subject> mLis);

        void show_chart_to_subject(List<Chart_To_Subject> mLis);

        void show_bxh_week_chart(List<BXH> mLis);

        void show_bxh_month_chart(List<BXH> mLis);

        void show_bxh_year_chart(List<BXH> mLis);
    }
}
