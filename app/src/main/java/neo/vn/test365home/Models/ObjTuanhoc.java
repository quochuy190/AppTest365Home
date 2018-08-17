package neo.vn.test365home.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObjTuanhoc implements Parcelable{
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ID")
    String sID;
    @SerializedName("YEAR_ID")
    String sYEAR_ID;
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
    @SerializedName("NAME")
    String sNAME;
    @SerializedName("REQUIREMENT")
    String sREQUIREMENT;
    @SerializedName("OBJECTIVE")
    String sOBJECTIVE;
    @SerializedName("UPDATETIME")
    String sUPDATETIME;
    @SerializedName("EDITOR")
    String sEDITOR;
    @SerializedName("PRICE")
    String sPRICE;
    @SerializedName("TOTAL_BUY")
    String sTOTAL_BUY;
    @SerializedName("STATUS")
    String sSTATUS;

    public ObjTuanhoc() {
    }

    public ObjTuanhoc(String sERROR, String sMESSAGE, String sRESULT, String sID,
                      String sYEAR_ID, String sLEVEL_ID, String sLEVEL_NAME,
                      String sSUBJECT_ID, String sSUBJECT_NAME, String sWEEK_ID,
                      String sWEEK_NAME, String sNAME, String sREQUIREMENT,
                      String sOBJECTIVE, String sUPDATETIME, String sEDITOR,
                      String sPRICE, String sTOTAL_BUY) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sID = sID;
        this.sYEAR_ID = sYEAR_ID;
        this.sLEVEL_ID = sLEVEL_ID;
        this.sLEVEL_NAME = sLEVEL_NAME;
        this.sSUBJECT_ID = sSUBJECT_ID;
        this.sSUBJECT_NAME = sSUBJECT_NAME;
        this.sWEEK_ID = sWEEK_ID;
        this.sWEEK_NAME = sWEEK_NAME;
        this.sNAME = sNAME;
        this.sREQUIREMENT = sREQUIREMENT;
        this.sOBJECTIVE = sOBJECTIVE;
        this.sUPDATETIME = sUPDATETIME;
        this.sEDITOR = sEDITOR;
        this.sPRICE = sPRICE;
        this.sTOTAL_BUY = sTOTAL_BUY;
    }

    protected ObjTuanhoc(Parcel in) {
        sERROR = in.readString();
        sMESSAGE = in.readString();
        sRESULT = in.readString();
        sID = in.readString();
        sYEAR_ID = in.readString();
        sLEVEL_ID = in.readString();
        sLEVEL_NAME = in.readString();
        sSUBJECT_ID = in.readString();
        sSUBJECT_NAME = in.readString();
        sWEEK_ID = in.readString();
        sWEEK_NAME = in.readString();
        sNAME = in.readString();
        sREQUIREMENT = in.readString();
        sOBJECTIVE = in.readString();
        sUPDATETIME = in.readString();
        sEDITOR = in.readString();
        sPRICE = in.readString();
        sTOTAL_BUY = in.readString();
    }

    public static final Creator<ObjTuanhoc> CREATOR = new Creator<ObjTuanhoc>() {
        @Override
        public ObjTuanhoc createFromParcel(Parcel in) {
            return new ObjTuanhoc(in);
        }

        @Override
        public ObjTuanhoc[] newArray(int size) {
            return new ObjTuanhoc[size];
        }
    };

    private static ObjTuanhoc getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),ObjTuanhoc.class);
    }

    public  static ArrayList<ObjTuanhoc> getList(String jsonArray) throws JSONException {
        ArrayList<ObjTuanhoc> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ObjTuanhoc>>(){}.getType();
        Gson gson= new Gson();
        arrayList = gson.fromJson(jsonArray,type);
        return arrayList;
    }


    public String getsSTATUS() {
        return sSTATUS;
    }

    public void setsSTATUS(String sSTATUS) {
        this.sSTATUS = sSTATUS;
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

    public String getsYEAR_ID() {
        return sYEAR_ID;
    }

    public void setsYEAR_ID(String sYEAR_ID) {
        this.sYEAR_ID = sYEAR_ID;
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

    public String getsNAME() {
        return sNAME;
    }

    public void setsNAME(String sNAME) {
        this.sNAME = sNAME;
    }

    public String getsREQUIREMENT() {
        return sREQUIREMENT;
    }

    public void setsREQUIREMENT(String sREQUIREMENT) {
        this.sREQUIREMENT = sREQUIREMENT;
    }

    public String getsOBJECTIVE() {
        return sOBJECTIVE;
    }

    public void setsOBJECTIVE(String sOBJECTIVE) {
        this.sOBJECTIVE = sOBJECTIVE;
    }

    public String getsUPDATETIME() {
        return sUPDATETIME;
    }

    public void setsUPDATETIME(String sUPDATETIME) {
        this.sUPDATETIME = sUPDATETIME;
    }

    public String getsEDITOR() {
        return sEDITOR;
    }

    public void setsEDITOR(String sEDITOR) {
        this.sEDITOR = sEDITOR;
    }

    public String getsPRICE() {
        return sPRICE;
    }

    public void setsPRICE(String sPRICE) {
        this.sPRICE = sPRICE;
    }

    public String getsTOTAL_BUY() {
        return sTOTAL_BUY;
    }

    public void setsTOTAL_BUY(String sTOTAL_BUY) {
        this.sTOTAL_BUY = sTOTAL_BUY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sERROR);
        parcel.writeString(sMESSAGE);
        parcel.writeString(sRESULT);
        parcel.writeString(sID);
        parcel.writeString(sYEAR_ID);
        parcel.writeString(sLEVEL_ID);
        parcel.writeString(sLEVEL_NAME);
        parcel.writeString(sSUBJECT_ID);
        parcel.writeString(sSUBJECT_NAME);
        parcel.writeString(sWEEK_ID);
        parcel.writeString(sWEEK_NAME);
        parcel.writeString(sNAME);
        parcel.writeString(sREQUIREMENT);
        parcel.writeString(sOBJECTIVE);
        parcel.writeString(sUPDATETIME);
        parcel.writeString(sEDITOR);
        parcel.writeString(sPRICE);
        parcel.writeString(sTOTAL_BUY);
    }
}

