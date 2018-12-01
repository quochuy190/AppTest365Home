package neo.vn.test365home.View.Setup;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.ErrorApi;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/31/2018
 * @updated 7/31/2018
 * @modified by
 * @updated on 7/31/2018
 * @since 1.0
 */
public class PresenterFeedBack implements ImpFeedback.Presenter {
    private static final String TAG = "PresenterLogin";
    ApiServiceIml mApiService;
    ImpFeedback.View mView;

    public PresenterFeedBack(ImpFeedback.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }


    @Override
    public void api_add_feedback_app(String sUserName, String sContent) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "app_feedback");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserName);
        mMap.put("P2", sContent);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                mView.show_error_api(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ErrorApi> mLiss = ErrorApi.getList(objT);
                    mView.show_add_feedback_app(mLiss);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_add_feedback_app(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
