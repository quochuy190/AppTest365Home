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

public class Cauhoi implements Parcelable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;

    @SerializedName("INFO")
    List<CauhoiDetail> lisInfo;
    @SerializedName("ID")
    String sID;
    @SerializedName("EXCERCISE_ID")
    String sEXCERCISE_ID;
    @SerializedName("QUESTION_NUMBER")
    String sQUESTION_NUMBER;
   /* 1 là chon đáp án đúng
   *  2
   *  3
   * */
    @SerializedName("KIEU")
    String sKIEU;
    @SerializedName("HUONGDAN")
    String sHUONGDAN;
    @SerializedName("TEXT")
    String sTEXT;
    @SerializedName("IMAGE_ID")
    String sIMAGE_ID;
    @SerializedName("AUDIO_ID")
    String sAUDIO_ID;
    @SerializedName("UPDATETIME")
    String sUPDATETIME;

    String mOption;

    public Cauhoi(String sERROR, String sMESSAGE, String sRESULT, String mOption) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.mOption = mOption;
    }

    public Cauhoi() {
    }

    protected Cauhoi(Parcel in) {
        sERROR = in.readString();
        sMESSAGE = in.readString();
        sRESULT = in.readString();
    }

    public static final Creator<Cauhoi> CREATOR = new Creator<Cauhoi>() {
        @Override
        public Cauhoi createFromParcel(Parcel in) {
            return new Cauhoi(in);
        }

        @Override
        public Cauhoi[] newArray(int size) {
            return new Cauhoi[size];
        }
    };

    private static Cauhoi getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Cauhoi.class);
    }

    public static ArrayList<Cauhoi> getList(String jsonArray) throws JSONException {
        ArrayList<Cauhoi> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Cauhoi>>() {
        }.getType();
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);
        return arrayList;
    }

    public String getmOption() {
        return mOption;
    }

    public void setmOption(String mOption) {
        this.mOption = mOption;
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

    public List<CauhoiDetail> getLisInfo() {
        return lisInfo;
    }

    public void setLisInfo(List<CauhoiDetail> lisInfo) {
        this.lisInfo = lisInfo;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsEXCERCISE_ID() {
        return sEXCERCISE_ID;
    }

    public void setsEXCERCISE_ID(String sEXCERCISE_ID) {
        this.sEXCERCISE_ID = sEXCERCISE_ID;
    }

    public String getsQUESTION_NUMBER() {
        return sQUESTION_NUMBER;
    }

    public void setsQUESTION_NUMBER(String sQUESTION_NUMBER) {
        this.sQUESTION_NUMBER = sQUESTION_NUMBER;
    }

    public String getsKIEU() {
        return sKIEU;
    }

    public void setsKIEU(String sKIEU) {
        this.sKIEU = sKIEU;
    }

    public String getsHUONGDAN() {
        return sHUONGDAN;
    }

    public void setsHUONGDAN(String sHUONGDAN) {
        this.sHUONGDAN = sHUONGDAN;
    }

    public String getsTEXT() {
        return sTEXT;
    }

    public void setsTEXT(String sTEXT) {
        this.sTEXT = sTEXT;
    }

    public String getsIMAGE_ID() {
        return sIMAGE_ID;
    }

    public void setsIMAGE_ID(String sIMAGE_ID) {
        this.sIMAGE_ID = sIMAGE_ID;
    }

    public String getsAUDIO_ID() {
        return sAUDIO_ID;
    }

    public void setsAUDIO_ID(String sAUDIO_ID) {
        this.sAUDIO_ID = sAUDIO_ID;
    }

    public String getsUPDATETIME() {
        return sUPDATETIME;
    }

    public void setsUPDATETIME(String sUPDATETIME) {
        this.sUPDATETIME = sUPDATETIME;
    }

    public static Creator<Cauhoi> getCREATOR() {
        return CREATOR;
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
    }
}

