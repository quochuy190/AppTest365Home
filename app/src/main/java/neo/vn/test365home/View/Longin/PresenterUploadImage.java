package neo.vn.test365home.View.Longin;

import android.util.Log;

import neo.vn.test365home.ApiService.ApiServicePostImage;
import neo.vn.test365home.Listener.CallbackData;

public class PresenterUploadImage implements ImpUploadImage.Presenter {
    private static final String TAG = "PresenterUploadImage";
    ApiServicePostImage mApiService;
    ImpUploadImage.View mView;

    public PresenterUploadImage(ImpUploadImage.View mView) {
        mApiService = new ApiServicePostImage();
        this.mView = mView;
    }

    @Override
    public void api_upload_image(String part) {

        mApiService.api_uploadImage(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                mView.show_error_api(null);
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    mView.show_upload_image(objT);
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, part);
    }
}
