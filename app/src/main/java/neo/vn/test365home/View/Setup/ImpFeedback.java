package neo.vn.test365home.View.Setup;

import java.util.List;

import neo.vn.test365home.Models.ErrorApi;

public interface ImpFeedback {
    interface Presenter {
        void api_add_feedback_app(String sUserName, String sContent);

    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_add_feedback_app(List<ErrorApi> mLis);


    }
}
