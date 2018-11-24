package neo.vn.test365home.View.Longin;

import java.util.List;

import neo.vn.test365home.Models.ErrorApi;

public interface ImpUploadImage {
    interface Presenter {
        void api_upload_image(String part);

    }

    interface View {
        void show_error_api(List<ErrorApi> mLis);

        void show_upload_image(String sUrlImage);
    }
}
