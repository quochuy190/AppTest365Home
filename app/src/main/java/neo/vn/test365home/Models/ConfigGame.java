package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConfigGame {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("CHILD_ID")
    String sCHILD_ID;
    @SerializedName("CHILD_NAME")
    String sCHILD_NAME;
    @SerializedName("GAME_TPTT")
    String sGAME_TPTT;
    @SerializedName("GAME_SUDOKU")
    String sGAME_SUDOKU;
    @SerializedName("GAME_KOW")
    String sGAME_KOW;
    @SerializedName("GAME_TNNL")
    String sGAME_TNNL;

    public ConfigGame() {
    }

    private static ConfigGame getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), ConfigGame.class);
    }

    public static ArrayList<ConfigGame> getList(String jsonArray) throws JSONException {
        ArrayList<ConfigGame> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ConfigGame>>() {
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

    public String getsCHILD_ID() {
        return sCHILD_ID;
    }

    public void setsCHILD_ID(String sCHILD_ID) {
        this.sCHILD_ID = sCHILD_ID;
    }

    public String getsCHILD_NAME() {
        return sCHILD_NAME;
    }

    public void setsCHILD_NAME(String sCHILD_NAME) {
        this.sCHILD_NAME = sCHILD_NAME;
    }

    public String getsGAME_TPTT() {
        return sGAME_TPTT;
    }

    public void setsGAME_TPTT(String sGAME_TPTT) {
        this.sGAME_TPTT = sGAME_TPTT;
    }

    public String getsGAME_SUDOKU() {
        return sGAME_SUDOKU;
    }

    public void setsGAME_SUDOKU(String sGAME_SUDOKU) {
        this.sGAME_SUDOKU = sGAME_SUDOKU;
    }

    public String getsGAME_KOW() {
        return sGAME_KOW;
    }

    public void setsGAME_KOW(String sGAME_KOW) {
        this.sGAME_KOW = sGAME_KOW;
    }

    public String getsGAME_TNNL() {
        return sGAME_TNNL;
    }

    public void setsGAME_TNNL(String sGAME_TNNL) {
        this.sGAME_TNNL = sGAME_TNNL;
    }

}

