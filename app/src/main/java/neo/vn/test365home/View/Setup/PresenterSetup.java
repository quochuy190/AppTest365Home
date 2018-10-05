package neo.vn.test365home.View.Setup;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.ConfigChildren;
import neo.vn.test365home.Models.ErrorApi;
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

    @Override
    public void api_config_to_children(String sUserMe, String sUserCon, String pmath_take_time,
                                       String pmath_notify, String pmath_duration, String sTv_taken_time,
                                       String pTv_notify, String pTV_duration, String sEng_taken_time,
                                       String pEng_notify, String Eng_duration, String pmath_dy, String pTv_dy, String pENG_dy) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "config_to_children");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "14");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", pmath_take_time);
        mMap.put("P4", pmath_notify);
        mMap.put("P5", pmath_duration);
        mMap.put("P6", sTv_taken_time);
        mMap.put("P7", pTv_notify);
        mMap.put("P8", pTV_duration);
        mMap.put("P9", sEng_taken_time);
        mMap.put("P10", pEng_notify);
        mMap.put("P11", Eng_duration);
        mMap.put("P12", pmath_dy);
        mMap.put("P13", pTv_dy);
        mMap.put("P14", pENG_dy);

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
                    mView.show_config_to_children(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_config_children(String sUserMe, String sUserCon) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_config_children");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);

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
                    List<ConfigChildren> mLisFlight = ConfigChildren.getList(objT);
                    mView.show_get_config_children(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_payment(String sUserMe, String sMenhgia) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "payment");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sMenhgia);

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
                    mView.show_payment(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
