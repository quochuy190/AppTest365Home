package neo.vn.test365home.View.Longin;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.Login;

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
public class PresenterLogin implements ImpLogin.Presenter {
    private static final String TAG = "PresenterLogin";
    ApiServiceIml mApiService;
    ImpLogin.View mView;

    public PresenterLogin(ImpLogin.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }


    @Override
    public void api_login( String sUserName,String sPassWord, String sAppVersion,
                          String sDeviceModel, String sDeviceType, String sOsVersion, String sTokenkey) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "login");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "7");
        mMap.put("P1", sUserName);
        mMap.put("P2", sPassWord);
        mMap.put("P3", sAppVersion);
        mMap.put("P4", sDeviceModel);
        mMap.put("P5", sDeviceType);
        mMap.put("P6", sOsVersion);
        mMap.put("P7", sTokenkey);


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
                    List<Login> mLisFlight = Login.getList(objT);
                    mView.show_api_login(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);

    }

    @Override
    public void api_register(String sUserName, String sPassWord) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "signup");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserName);
        mMap.put("P2", sPassWord);
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
                    mView.show_api_register(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_update_info(String sUserName, String sFullname, String sPhone, String sEmail, String sAvata) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "update");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "5");
        mMap.put("P1", sUserName);
        mMap.put("P2", sFullname);
        mMap.put("P3", sPhone );
        mMap.put("P4", sEmail);
        mMap.put("P5", sAvata);

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
                    List<Login> mLisFlight = Login.getList(objT);
                    mView.show_api_update_info(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_create_children(String sUserName, String sIdTinh, String sIdQuan, String sIdTruong,
                                    String sIdKhoi, String sIdNam, String sLop, String sFullName,
                                    String sAvata, String sUsernameChildren, String sPassChildren) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "create_children");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "11");
        mMap.put("P1", sUserName);
        mMap.put("P2", sIdTinh);
        mMap.put("P3", sIdQuan );
        mMap.put("P4", sIdTruong);
        mMap.put("P5", sIdKhoi);
        mMap.put("P6", sIdNam);
        mMap.put("P7", sLop);
        mMap.put("P8", sFullName );
        mMap.put("P9", sAvata);
        mMap.put("P10", sUsernameChildren);
        mMap.put("P11", sPassChildren);

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
                    mView.create_sub_user(mLisFlight);
                    //mView.show_api_update_info(mLisFlight);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void api_lis_children(String sUserName) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_children");
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
                    List<Childrens> mLis = Childrens.getList(objT);
                    mView.show_lis_children(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
