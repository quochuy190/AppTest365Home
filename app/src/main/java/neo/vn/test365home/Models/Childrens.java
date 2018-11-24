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

public class Childrens implements Parcelable {
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
    @SerializedName("AVATAR")
    String sAVATAR;
    @SerializedName("USERNAME")
    String sUSERNAME;
    @SerializedName("PASS")
    String sPASS;
    @SerializedName("ID_LEVEL")
    String sID_LEVEL;
    @SerializedName("LEVEL_ID")
    String sLEVEL_ID;
    @SerializedName("LEVEL_NAME")
    String sLEVEL_NAME;
    @SerializedName("CLASS")
    String sCLASS;
    @SerializedName("ID_PROVINCE")
    String sID_PROVINCE;
    @SerializedName("PROVINCE_NAME")
    String sPROVINCE_NAME;
    @SerializedName("DISTRICT_ID")
    String sDISTRICT_ID;
    @SerializedName("DISTRICT_NAME")
    String sDISTRICT_NAME;
    @SerializedName("SCHOOL_ID")
    String sSCHOOL_ID;
    @SerializedName("SCHOOL_NAME")
    String sSCHOOL_NAME;
    @SerializedName("PARENT_ID")
    String sPARENT_ID;
    @SerializedName("YEAR_ID")
    String sYEAR_ID;
    @SerializedName("YEAR_NAME")
    String sYEAR_NAME;
    boolean isAddSub;
    String sHeaderId;
    private boolean isChecked;

    public Childrens() {
    }

    public Childrens(String sFULLNAME, String sHeaderId) {
        this.sFULLNAME = sFULLNAME;
        this.sHeaderId = sHeaderId;
    }

    public Childrens(boolean isAddSub) {
        this.isAddSub = isAddSub;
    }

    protected Childrens(Parcel in) {
        sERROR = in.readString();
        sMESSAGE = in.readString();
        sRESULT = in.readString();
        sID = in.readString();
        sFULLNAME = in.readString();
        sAVATAR = in.readString();
        sUSERNAME = in.readString();
        sPASS = in.readString();
        sID_LEVEL = in.readString();
        sLEVEL_NAME = in.readString();
        sCLASS = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<Childrens> CREATOR = new Creator<Childrens>() {
        @Override
        public Childrens createFromParcel(Parcel in) {
            return new Childrens(in);
        }

        @Override
        public Childrens[] newArray(int size) {
            return new Childrens[size];
        }
    };

    private static Childrens getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Childrens.class);
    }

    public static ArrayList<Childrens> getList(String jsonArray) throws JSONException {
        ArrayList<Childrens> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Childrens>>() {
        }.getType();
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);
        return arrayList;
    }

    public String getsHeaderId() {
        return sHeaderId;
    }

    public void setsHeaderId(String sHeaderId) {
        this.sHeaderId = sHeaderId;
    }

    public String getsLEVEL_ID() {
        return sLEVEL_ID;
    }

    public void setsLEVEL_ID(String sLEVEL_ID) {
        this.sLEVEL_ID = sLEVEL_ID;
    }

    public boolean isAddSub() {
        return isAddSub;
    }

    public void setAddSub(boolean addSub) {
        isAddSub = addSub;
    }

    public String getsID_PROVINCE() {
        return sID_PROVINCE;
    }

    public void setsID_PROVINCE(String sID_PROVINCE) {
        this.sID_PROVINCE = sID_PROVINCE;
    }

    public String getsPROVINCE_NAME() {
        return sPROVINCE_NAME;
    }

    public void setsPROVINCE_NAME(String sPROVINCE_NAME) {
        this.sPROVINCE_NAME = sPROVINCE_NAME;
    }

    public String getsDISTRICT_ID() {
        return sDISTRICT_ID;
    }

    public void setsDISTRICT_ID(String sDISTRICT_ID) {
        this.sDISTRICT_ID = sDISTRICT_ID;
    }

    public String getsDISTRICT_NAME() {
        return sDISTRICT_NAME;
    }

    public void setsDISTRICT_NAME(String sDISTRICT_NAME) {
        this.sDISTRICT_NAME = sDISTRICT_NAME;
    }

    public String getsSCHOOL_ID() {
        return sSCHOOL_ID;
    }

    public void setsSCHOOL_ID(String sSCHOOL_ID) {
        this.sSCHOOL_ID = sSCHOOL_ID;
    }

    public String getsSCHOOL_NAME() {
        return sSCHOOL_NAME;
    }

    public void setsSCHOOL_NAME(String sSCHOOL_NAME) {
        this.sSCHOOL_NAME = sSCHOOL_NAME;
    }

    public String getsPARENT_ID() {
        return sPARENT_ID;
    }

    public void setsPARENT_ID(String sPARENT_ID) {
        this.sPARENT_ID = sPARENT_ID;
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

    public String getsID_LEVEL() {
        return sID_LEVEL;
    }

    public void setsID_LEVEL(String sID_LEVEL) {
        this.sID_LEVEL = sID_LEVEL;
    }

    public String getsLEVEL_NAME() {
        return sLEVEL_NAME;
    }

    public void setsLEVEL_NAME(String sLEVEL_NAME) {
        this.sLEVEL_NAME = sLEVEL_NAME;
    }

    public String getsCLASS() {
        return sCLASS;
    }

    public void setsCLASS(String sCLASS) {
        this.sCLASS = sCLASS;
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

    public String getsAVATAR() {
        return sAVATAR;
    }

    public void setsAVATAR(String sAVATAR) {
        this.sAVATAR = sAVATAR;
    }

    public String getsUSERNAME() {
        return sUSERNAME;
    }

    public void setsUSERNAME(String sUSERNAME) {
        this.sUSERNAME = sUSERNAME;
    }

    public String getsPASS() {
        return sPASS;
    }

    public void setsPASS(String sPASS) {
        this.sPASS = sPASS;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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
        parcel.writeString(sFULLNAME);
        parcel.writeString(sAVATAR);
        parcel.writeString(sUSERNAME);
        parcel.writeString(sPASS);
        parcel.writeString(sID_LEVEL);
        parcel.writeString(sLEVEL_NAME);
        parcel.writeString(sCLASS);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
    }
}

