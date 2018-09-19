package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;

    @SerializedName("ID")
    String sID;
    @SerializedName("FULLNAME")
    String sFULLNAME;
    @SerializedName("PHONENUMBER")
    String sPHONENUMBER;
    @SerializedName("EMAIL")
    String sEMAIL;
    @SerializedName("AVARTAR")
    String sAVARTAR;
    @SerializedName("API_SERVER")
    String sAPI_SERVER;

    @SerializedName("USERNAME")
    String sUSERNAME;
    @SerializedName("PROMOTIONCODE")
    String sPROMOTIONCODE;
    @SerializedName("STATE")
    String sSTATE;
    @SerializedName("MATH_NOTIFY")
    String sMATH_NOTIFY;
    @SerializedName("VIETNAMESE_NOTIFY")
    String sVIETNAMESE_NOTIFY;
    @SerializedName("ENGLISH_NOTIFY")
    String sENGLISH_NOTIFY;
    @SerializedName("CORE_BALANCE")
    String sCORE_BALANCE;
    @SerializedName("PROMOTION_BALANCE")
    String sPROMOTION_BALANCE;

    public UserInfo() {
    }

    private static UserInfo getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), UserInfo.class);
    }

    public static ArrayList<UserInfo> getList(String jsonArray) throws JSONException {
        ArrayList<UserInfo> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<UserInfo>>() {
        }.getType();
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);
        return arrayList;
    }


    public String getsERROR() {
        return sERROR;
    }

    public void setsERROR(String sERROR) {
        this.sERROR = sERROR;
    }

    public String getsMESSAGE() {
        return sMESSAGE;
    }

    public void setsMESSAGE(String sMESSAGE) {
        this.sMESSAGE = sMESSAGE;
    }

    public String getsRESULT() {
        return sRESULT;
    }

    public void setsRESULT(String sRESULT) {
        this.sRESULT = sRESULT;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsFULLNAME() {
        return sFULLNAME;
    }

    public void setsFULLNAME(String sFULLNAME) {
        this.sFULLNAME = sFULLNAME;
    }

    public String getsPHONENUMBER() {
        return sPHONENUMBER;
    }

    public void setsPHONENUMBER(String sPHONENUMBER) {
        this.sPHONENUMBER = sPHONENUMBER;
    }

    public String getsEMAIL() {
        return sEMAIL;
    }

    public void setsEMAIL(String sEMAIL) {
        this.sEMAIL = sEMAIL;
    }

    public String getsAVARTAR() {
        return sAVARTAR;
    }

    public void setsAVARTAR(String sAVARTAR) {
        this.sAVARTAR = sAVARTAR;
    }

    public String getsAPI_SERVER() {
        return sAPI_SERVER;
    }

    public void setsAPI_SERVER(String sAPI_SERVER) {
        this.sAPI_SERVER = sAPI_SERVER;
    }

    public String getsUSERNAME() {
        return sUSERNAME;
    }

    public void setsUSERNAME(String sUSERNAME) {
        this.sUSERNAME = sUSERNAME;
    }

    public String getsPROMOTIONCODE() {
        return sPROMOTIONCODE;
    }

    public void setsPROMOTIONCODE(String sPROMOTIONCODE) {
        this.sPROMOTIONCODE = sPROMOTIONCODE;
    }

    public String getsSTATE() {
        return sSTATE;
    }

    public void setsSTATE(String sSTATE) {
        this.sSTATE = sSTATE;
    }

    public String getsMATH_NOTIFY() {
        return sMATH_NOTIFY;
    }

    public void setsMATH_NOTIFY(String sMATH_NOTIFY) {
        this.sMATH_NOTIFY = sMATH_NOTIFY;
    }

    public String getsVIETNAMESE_NOTIFY() {
        return sVIETNAMESE_NOTIFY;
    }

    public void setsVIETNAMESE_NOTIFY(String sVIETNAMESE_NOTIFY) {
        this.sVIETNAMESE_NOTIFY = sVIETNAMESE_NOTIFY;
    }

    public String getsENGLISH_NOTIFY() {
        return sENGLISH_NOTIFY;
    }

    public void setsENGLISH_NOTIFY(String sENGLISH_NOTIFY) {
        this.sENGLISH_NOTIFY = sENGLISH_NOTIFY;
    }

    public String getsCORE_BALANCE() {
        return sCORE_BALANCE;
    }

    public void setsCORE_BALANCE(String sCORE_BALANCE) {
        this.sCORE_BALANCE = sCORE_BALANCE;
    }

    public String getsPROMOTION_BALANCE() {
        return sPROMOTION_BALANCE;
    }

    public void setsPROMOTION_BALANCE(String sPROMOTION_BALANCE) {
        this.sPROMOTION_BALANCE = sPROMOTION_BALANCE;
    }
}

