package neo.vn.test365home.View.Baitap;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.vn.test365home.ApiService.ApiServiceIml;
import neo.vn.test365home.Listener.CallbackData;
import neo.vn.test365home.Models.Cauhoi;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.ErrorApi;
import neo.vn.test365home.Models.ExcerciseDetail;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.Models.Sticker;
import neo.vn.test365home.Models.TuanDamua;

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
public class PresenterBaitap implements ImpBaitap.Presenter {
    private static final String TAG = "PresenterBaitap";
    ApiServiceIml mApiService;
    ImpBaitap.View mView;

    public PresenterBaitap(ImpBaitap.View mView) {
        this.mView = mView;
        mApiService = new ApiServiceIml();
    }

    @Override
    public void get_api_list_get_children(String sUserMe) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_children");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserMe);

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
                    //jArray = new JSONArray(c);
                    List<Childrens> mLis = Childrens.getList(objT);
                    mView.show_list_children(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_weektest(String sUserMe, String sIdKhoi, String sObjective, String mChildrenId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "weektest");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sIdKhoi);
        mMap.put("P3", sObjective);
        mMap.put("P4", mChildrenId);

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
                    List<ObjTuanhoc> mLis = ObjTuanhoc.getList(objT);
                    mView.show_list_week_test(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_buy_excercise(String sUserMe, String sUserCon, String sChuoimade, String sIdmon) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "buy_excercise");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sChuoimade);
        mMap.put("P4", sIdmon);

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
                    List<ErrorApi> mLis = ErrorApi.getList(objT);
                    mView.show_list_buy_excercise(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_list_buy(String sUserMe, String sUserCon, String sIdMon, String sIdKhoi) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "list_buy");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdMon);
        mMap.put("P4", sIdKhoi);

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
                    List<TuanDamua> mLis = TuanDamua.getList(objT);
                    mView.show_list_list_buy(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_des_excercise(String sUserMe, String sUserCon, String sIdDebai) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "des_excercise");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdDebai);


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
                    List<ExcerciseDetail> mLis = ExcerciseDetail.getList(objT);
                    mView.show_list_des_excercise(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_get_part(String sUserMe, String sUserCon, String sIdDebai) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_part");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdDebai);


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
                    List<Cauhoi> mLis = Cauhoi.getList(objT);
                    mView.show_list_get_part(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_report_excercise(String sUserMe, String sUserCon, String sIdDebai) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "report_excercise");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdDebai);


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
                    List<ExcerciseDetail> mLis = ExcerciseDetail.getList(objT);
                    mView.show_list_report_excercise(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_get_sticker(String sUserMe, String sIdKhoi) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_sticker");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sIdKhoi);

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
                    List<Sticker> mLis = Sticker.getList(objT);
                    mView.show_list_get_sticker(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_gift_sticker(String sUserMe, String sUserCon, String sIdDebai, String sIdSticker) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "gift_sticker");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdDebai);
        mMap.put("P4", sIdSticker);

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
                    List<ErrorApi> mLis = ErrorApi.getList(objT);
                    mView.show_list_gift_sticker(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_api_mt_comment(String sUserMe, String sUserCon, String sIdDebai, String sNoidung) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "mt_comment");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserMe);
        mMap.put("P2", sUserCon);
        mMap.put("P3", sIdDebai);
        mMap.put("P4", sNoidung);

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
                    List<ErrorApi> mLis = ErrorApi.getList(objT);
                    mView.show_list_mt_comment(mLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.show_error_api(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }
}
