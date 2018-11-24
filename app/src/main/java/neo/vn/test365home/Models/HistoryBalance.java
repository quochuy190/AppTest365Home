package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryBalance {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ID")
    String sID;
    @SerializedName("PARENT_ID")
    String sPARENT_ID;
    @SerializedName("TYPE")
    String sTYPE;
    @SerializedName("CHANNEL")
    String sCHANNEL;
    @SerializedName("AMOUNT")
    String sAMOUNT;
    @SerializedName("BALANCE_TYPE")
    String sBALANCE_TYPE;
    @SerializedName("DESCRIPTION")
    String sDESCRIPTION;
    @SerializedName("TRANSACTION_TIME")
    String sTRANSACTION_TIME;


    private static HistoryBalance getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),HistoryBalance.class);
    }

    public  static ArrayList<HistoryBalance> getList(String jsonArray) throws JSONException {
        ArrayList<HistoryBalance> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<HistoryBalance>>(){}.getType();
        Gson gson= new Gson();
        arrayList = gson.fromJson(jsonArray,type);
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

    public String getsPARENT_ID() {
        return sPARENT_ID;
    }

    public void setsPARENT_ID(String sPARENT_ID) {
        this.sPARENT_ID = sPARENT_ID;
    }

    public String getsTYPE() {
        return sTYPE;
    }

    public void setsTYPE(String sTYPE) {
        this.sTYPE = sTYPE;
    }

    public String getsCHANNEL() {
        return sCHANNEL;
    }

    public void setsCHANNEL(String sCHANNEL) {
        this.sCHANNEL = sCHANNEL;
    }

    public String getsAMOUNT() {
        return sAMOUNT;
    }

    public void setsAMOUNT(String sAMOUNT) {
        this.sAMOUNT = sAMOUNT;
    }

    public String getsBALANCE_TYPE() {
        return sBALANCE_TYPE;
    }

    public void setsBALANCE_TYPE(String sBALANCE_TYPE) {
        this.sBALANCE_TYPE = sBALANCE_TYPE;
    }

    public String getsDESCRIPTION() {
        return sDESCRIPTION;
    }

    public void setsDESCRIPTION(String sDESCRIPTION) {
        this.sDESCRIPTION = sDESCRIPTION;
    }

    public String getsTRANSACTION_TIME() {
        return sTRANSACTION_TIME;
    }

    public void setsTRANSACTION_TIME(String sTRANSACTION_TIME) {
        this.sTRANSACTION_TIME = sTRANSACTION_TIME;
    }
}
