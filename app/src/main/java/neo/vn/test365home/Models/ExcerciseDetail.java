package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExcerciseDetail {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ID")
    String sID;
    @SerializedName("WEEK_TEST_ID")
    String sWEEK_TEST_ID;
    @SerializedName("NAME")
    String sNAME;
    @SerializedName("TYPE")
    String sTYPE;
    @SerializedName("NOTICE")
    String sNOTICE;
    @SerializedName("UNDER_6_RECOMMENT")
    String sUNDER_6_RECOMMENT;
    @SerializedName("RECOMMENT_7_8")
    String sRECOMMENT_7_8;
    @SerializedName("RECOMMENT_9_10")
    String sRECOMMENT_9_10;
    @SerializedName("TAKEN_NUMBER")
    String sTAKEN_NUMBER;
    @SerializedName("STATE")
    String sSTATE;
    @SerializedName("ADMIN_COMMENT")
    String sADMIN_COMMENT;
    @SerializedName("UPDATETIME")
    String sUPDATETIME;
    @SerializedName("YEAR_ID")
    String sYEAR_ID;
    @SerializedName("YEAR_NAME")
    String sYEAR_NAME;
    @SerializedName("LEVEL_ID")
    String sLEVEL_ID;
    @SerializedName("LEVEL_NAME")
    String sLEVEL_NAME;
    @SerializedName("SUBJECT_ID")
    String sSUBJECT_ID;
    @SerializedName("SUBJECT_NAME")
    String sSUBJECT_NAME;
    @SerializedName("WEEK_ID")
    String sWEEK_ID;

    @SerializedName("WEEK_NAME")
    String sWEEK_NAME;
    @SerializedName("START_TAKE_TIME")
    String sSTART_TAKE_TIME;
    @SerializedName("END_TAKE_TIME")
    String sEND_TAKE_TIME;
    @SerializedName("DURATION")
    String sDURATION;
    @SerializedName("SUBMIT_TYPE")
    String sSUBMIT_TYPE;
    @SerializedName("POINT")
    String sPOINT;
    @SerializedName("RECOMMENT")
    String sRECOMMENT;

    @SerializedName("WORKHARD_POINT")
    String sWORKHARD_POINT;
    @SerializedName("IMPROMENT_POINT")
    String sIMPROMENT_POINT;
    @SerializedName("STICKER_ID")
    String sSTICKER_ID;

    @SerializedName("cunglam")
    String sCunglam;
    @SerializedName("cungtruong")
    String sCungtruong;
    @SerializedName("cunglop")
    String sCunglop;
    @SerializedName("caonhat")
    String sCaonhat;
    @SerializedName("trungbinh")
    String sTrungbinh;
    @SerializedName("thapnhat")
    String sThapnhat;

    public ExcerciseDetail() {
    }

    private static ExcerciseDetail getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), ExcerciseDetail.class);
    }

    public static ArrayList<ExcerciseDetail> getList(String jsonArray) throws JSONException {
        ArrayList<ExcerciseDetail> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ExcerciseDetail>>() {
        }.getType();
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);
        return arrayList;
    }

    public String getsCunglam() {
        return sCunglam;
    }

    public void setsCunglam(String sCunglam) {
        this.sCunglam = sCunglam;
    }

    public String getsCungtruong() {
        return sCungtruong;
    }

    public void setsCungtruong(String sCungtruong) {
        this.sCungtruong = sCungtruong;
    }

    public String getsCunglop() {
        return sCunglop;
    }

    public void setsCunglop(String sCunglop) {
        this.sCunglop = sCunglop;
    }

    public String getsCaonhat() {
        return sCaonhat;
    }

    public void setsCaonhat(String sCaonhat) {
        this.sCaonhat = sCaonhat;
    }

    public String getsTrungbinh() {
        return sTrungbinh;
    }

    public void setsTrungbinh(String sTrungbinh) {
        this.sTrungbinh = sTrungbinh;
    }

    public String getsThapnhat() {
        return sThapnhat;
    }

    public void setsThapnhat(String sThapnhat) {
        this.sThapnhat = sThapnhat;
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

    public String getsWEEK_TEST_ID() {
        return sWEEK_TEST_ID;
    }

    public void setsWEEK_TEST_ID(String sWEEK_TEST_ID) {
        this.sWEEK_TEST_ID = sWEEK_TEST_ID;
    }

    public String getsNAME() {
        return sNAME;
    }

    public void setsNAME(String sNAME) {
        this.sNAME = sNAME;
    }

    public String getsTYPE() {
        return sTYPE;
    }

    public void setsTYPE(String sTYPE) {
        this.sTYPE = sTYPE;
    }

    public String getsNOTICE() {
        return sNOTICE;
    }

    public void setsNOTICE(String sNOTICE) {
        this.sNOTICE = sNOTICE;
    }

    public String getsUNDER_6_RECOMMENT() {
        return sUNDER_6_RECOMMENT;
    }

    public void setsUNDER_6_RECOMMENT(String sUNDER_6_RECOMMENT) {
        this.sUNDER_6_RECOMMENT = sUNDER_6_RECOMMENT;
    }

    public String getsRECOMMENT_7_8() {
        return sRECOMMENT_7_8;
    }

    public void setsRECOMMENT_7_8(String sRECOMMENT_7_8) {
        this.sRECOMMENT_7_8 = sRECOMMENT_7_8;
    }

    public String getsRECOMMENT_9_10() {
        return sRECOMMENT_9_10;
    }

    public void setsRECOMMENT_9_10(String sRECOMMENT_9_10) {
        this.sRECOMMENT_9_10 = sRECOMMENT_9_10;
    }

    public String getsTAKEN_NUMBER() {
        return sTAKEN_NUMBER;
    }

    public void setsTAKEN_NUMBER(String sTAKEN_NUMBER) {
        this.sTAKEN_NUMBER = sTAKEN_NUMBER;
    }

    public String getsSTATE() {
        return sSTATE;
    }

    public void setsSTATE(String sSTATE) {
        this.sSTATE = sSTATE;
    }

    public String getsADMIN_COMMENT() {
        return sADMIN_COMMENT;
    }

    public void setsADMIN_COMMENT(String sADMIN_COMMENT) {
        this.sADMIN_COMMENT = sADMIN_COMMENT;
    }

    public String getsUPDATETIME() {
        return sUPDATETIME;
    }

    public void setsUPDATETIME(String sUPDATETIME) {
        this.sUPDATETIME = sUPDATETIME;
    }

    public String getsYEAR_ID() {
        return sYEAR_ID;
    }

    public void setsYEAR_ID(String sYEAR_ID) {
        this.sYEAR_ID = sYEAR_ID;
    }

    public String getsYEAR_NAME() {
        return sYEAR_NAME;
    }

    public void setsYEAR_NAME(String sYEAR_NAME) {
        this.sYEAR_NAME = sYEAR_NAME;
    }

    public String getsLEVEL_ID() {
        return sLEVEL_ID;
    }

    public void setsLEVEL_ID(String sLEVEL_ID) {
        this.sLEVEL_ID = sLEVEL_ID;
    }

    public String getsLEVEL_NAME() {
        return sLEVEL_NAME;
    }

    public void setsLEVEL_NAME(String sLEVEL_NAME) {
        this.sLEVEL_NAME = sLEVEL_NAME;
    }

    public String getsSUBJECT_ID() {
        return sSUBJECT_ID;
    }

    public void setsSUBJECT_ID(String sSUBJECT_ID) {
        this.sSUBJECT_ID = sSUBJECT_ID;
    }

    public String getsSUBJECT_NAME() {
        return sSUBJECT_NAME;
    }

    public void setsSUBJECT_NAME(String sSUBJECT_NAME) {
        this.sSUBJECT_NAME = sSUBJECT_NAME;
    }

    public String getsWEEK_ID() {
        return sWEEK_ID;
    }

    public void setsWEEK_ID(String sWEEK_ID) {
        this.sWEEK_ID = sWEEK_ID;
    }

    public String getsWEEK_NAME() {
        return sWEEK_NAME;
    }

    public void setsWEEK_NAME(String sWEEK_NAME) {
        this.sWEEK_NAME = sWEEK_NAME;
    }

    public String getsSTART_TAKE_TIME() {
        return sSTART_TAKE_TIME;
    }

    public void setsSTART_TAKE_TIME(String sSTART_TAKE_TIME) {
        this.sSTART_TAKE_TIME = sSTART_TAKE_TIME;
    }

    public String getsEND_TAKE_TIME() {
        return sEND_TAKE_TIME;
    }

    public void setsEND_TAKE_TIME(String sEND_TAKE_TIME) {
        this.sEND_TAKE_TIME = sEND_TAKE_TIME;
    }

    public String getsDURATION() {
        return sDURATION;
    }

    public void setsDURATION(String sDURATION) {
        this.sDURATION = sDURATION;
    }

    public String getsSUBMIT_TYPE() {
        return sSUBMIT_TYPE;
    }

    public void setsSUBMIT_TYPE(String sSUBMIT_TYPE) {
        this.sSUBMIT_TYPE = sSUBMIT_TYPE;
    }

    public String getsPOINT() {
        return sPOINT;
    }

    public void setsPOINT(String sPOINT) {
        this.sPOINT = sPOINT;
    }

    public String getsRECOMMENT() {
        return sRECOMMENT;
    }

    public void setsRECOMMENT(String sRECOMMENT) {
        this.sRECOMMENT = sRECOMMENT;
    }

    public String getsWORKHARD_POINT() {
        return sWORKHARD_POINT;
    }

    public void setsWORKHARD_POINT(String sWORKHARD_POINT) {
        this.sWORKHARD_POINT = sWORKHARD_POINT;
    }

    public String getsIMPROMENT_POINT() {
        return sIMPROMENT_POINT;
    }

    public void setsIMPROMENT_POINT(String sIMPROMENT_POINT) {
        this.sIMPROMENT_POINT = sIMPROMENT_POINT;
    }

    public String getsSTICKER_ID() {
        return sSTICKER_ID;
    }

    public void setsSTICKER_ID(String sSTICKER_ID) {
        this.sSTICKER_ID = sSTICKER_ID;
    }
}

