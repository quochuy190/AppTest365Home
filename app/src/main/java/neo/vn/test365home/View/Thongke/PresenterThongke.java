package neo.vn.test365home.View.Thongke;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.Models.Chart_To_Subject;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 8/2/2018
 * @updated 8/2/2018
 * @modified by
 * @updated on 8/2/2018
 * @since 1.0
 */
public class PresenterThongke implements ImpThongke.Presenter {
    private static final String TAG = "PresenterBaitap";
    ApiServiceIml mApiService;
    ImpThongke.View mView;

    public PresenterThongke(ImpThongke.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }

    @Override
    public void api_get_chart_to_subject_all(String sUserName, String sUserCon) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "chart_to_subject");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserName);
        mMap.put("P2", sUserCon);
        mApiService.getApiService(new CallbackData<String>() {
            JSONObject jobj;
            JSONArray jArray;
            @Override
            public void onGetDataErrorFault(Exception e) {
                mView.show_error_api(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Chart_To_Subject> mLis = Chart_To_Subject.getList(objT);
                    mView.show_chart_to_subject_all(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_chart_to_subject(String sUserName, String sUserCon) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "chart_to_subject_all");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserName);
        mMap.put("P2", sUserCon);
        mApiService.getApiService(new CallbackData<String>() {
            JSONObject jobj;
            JSONArray jArray;
            @Override
            public void onGetDataErrorFault(Exception e) {
                mView.show_error_api(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Chart_To_Subject> mLis = Chart_To_Subject.getList(objT);
                    mView.show_chart_to_subject_all(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_bxh_week_chart(String sUserName, String sUserCon, String sDate) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "week_chart");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserName);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sDate);
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
                    List<BXH> mLis = BXH.getList(objT);
                    mView.show_bxh_week_chart(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_bxh_month_chart(String sUserName, String sUserCon, String sDate) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "month_chart");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserName);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sDate);
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
                    List<BXH> mLis = BXH.getList(objT);
                    mView.show_bxh_month_chart(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_bxh_year_chart(String sUserName, String sUserCon, String sDate) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "year_chart");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserName);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sDate);
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
                    List<BXH> mLis = BXH.getList(objT);
                    mView.show_bxh_year_chart(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
