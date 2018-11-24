package neo.vn.test365home.View.Setup;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.ConfigNotify;
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
public class PresenterSetupNotify implements ImpSetupNotify.Presenter {
    private static final String TAG = "PresenterLogin";
    ApiServiceIml mApiService;
    ImpSetupNotify.View mView;

    public PresenterSetupNotify(ImpSetupNotify.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }

    @Override
    public void api_get_get_cf_notify(String sUserName) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_cf_notify");
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
                    List<ConfigNotify> mLis = ConfigNotify.getList(objT);
                    mView.show_get_cf_notify(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_update_cf_notify(String sUserName, String ptaken_notify, String plate_notify,
                                     String pstart_notify, String pend_notify, String pstart_game_notify,
                                     String pend_game_notify, String pbuy_exe_notify) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "update_cf_notify");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "8");
        mMap.put("P1", sUserName);
        mMap.put("P2", ptaken_notify);
        mMap.put("P3", plate_notify);
        mMap.put("P4", pstart_notify);
        mMap.put("P5", pend_notify);
        mMap.put("P6", pstart_game_notify);
        mMap.put("P7", pend_game_notify);
        mMap.put("P8", pbuy_exe_notify);
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
                    List<ErrorApi> mLisFlight = ErrorApi.getList(objT);
                    mView.show_update_cf_notify(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }


}
