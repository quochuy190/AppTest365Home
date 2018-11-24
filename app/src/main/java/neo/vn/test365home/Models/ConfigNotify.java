package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConfigNotify {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("PARENT_ID")
    String sPARENT_ID;
    @SerializedName("TAKEN_NOTIFY")
    String sTAKEN_NOTIFY;
    @SerializedName("LATE_NOTIFY")
    String sLATE_NOTIFY;
    @SerializedName("START_NOTIFY")
    String sSTART_NOTIFY;
    @SerializedName("END_NOTIFY")
    String sEND_NOTIFY;
    @SerializedName("START_GAME_NOTIFY")
    String sSTART_GAME_NOTIFY;
    @SerializedName("END_GAME_NOTIFY")
    String sEND_GAME_NOTIFY;
    @SerializedName("BUY_EXE_NOTIFY")
    String sBUY_EXE_NOTIFY;

    public ConfigNotify() {
    }

    private static ConfigNotify getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), ConfigNotify.class);
    }

    public static ArrayList<ConfigNotify> getList(String jsonArray) throws JSONException {
        ArrayList<ConfigNotify> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ConfigNotify>>() {
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

    public String getsPARENT_ID() {
        return sPARENT_ID;
    }

    public void setsPARENT_ID(String sPARENT_ID) {
        this.sPARENT_ID = sPARENT_ID;
    }

    public String getsTAKEN_NOTIFY() {
        return sTAKEN_NOTIFY;
    }

    public void setsTAKEN_NOTIFY(String sTAKEN_NOTIFY) {
        this.sTAKEN_NOTIFY = sTAKEN_NOTIFY;
    }

    public String getsLATE_NOTIFY() {
        return sLATE_NOTIFY;
    }

    public void setsLATE_NOTIFY(String sLATE_NOTIFY) {
        this.sLATE_NOTIFY = sLATE_NOTIFY;
    }

    public String getsSTART_NOTIFY() {
        return sSTART_NOTIFY;
    }

    public void setsSTART_NOTIFY(String sSTART_NOTIFY) {
        this.sSTART_NOTIFY = sSTART_NOTIFY;
    }

    public String getsEND_NOTIFY() {
        return sEND_NOTIFY;
    }

    public void setsEND_NOTIFY(String sEND_NOTIFY) {
        this.sEND_NOTIFY = sEND_NOTIFY;
    }

    public String getsSTART_GAME_NOTIFY() {
        return sSTART_GAME_NOTIFY;
    }

    public void setsSTART_GAME_NOTIFY(String sSTART_GAME_NOTIFY) {
        this.sSTART_GAME_NOTIFY = sSTART_GAME_NOTIFY;
    }

    public String getsEND_GAME_NOTIFY() {
        return sEND_GAME_NOTIFY;
    }

    public void setsEND_GAME_NOTIFY(String sEND_GAME_NOTIFY) {
        this.sEND_GAME_NOTIFY = sEND_GAME_NOTIFY;
    }

    public String getsBUY_EXE_NOTIFY() {
        return sBUY_EXE_NOTIFY;
    }

    public void setsBUY_EXE_NOTIFY(String sBUY_EXE_NOTIFY) {
        this.sBUY_EXE_NOTIFY = sBUY_EXE_NOTIFY;
    }
}

