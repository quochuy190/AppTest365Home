package neo.vn.test365home.View.Setup;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.UserInfo;

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
public class PresenterSetup implements ImpSetup.Presenter {
    private static final String TAG = "PresenterLogin";
    ApiServiceIml mApiService;
    ImpSetup.View mView;

    public PresenterSetup(ImpSetup.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }

    @Override
    public void api_get_user_info(String sUserName) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "user_info");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserName);


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
                    List<UserInfo> mLisFlight = UserInfo.getList(objT);
                    mView.show_user_info(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
