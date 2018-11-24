package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemSetupNotify {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("URL")
    String sURL;

    String sTitle;
    String sContent;
    boolean isOnOff;

    public ItemSetupNotify(String sTitle, String sContent, boolean isOnOff) {
        this.sTitle = sTitle;
        this.sContent = sContent;
        this.isOnOff = isOnOff;
    }

    public ItemSetupNotify() {
    }

    private static ItemSetupNotify getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), ItemSetupNotify.class);
    }

    public static ArrayList<ItemSetupNotify> getList(String jsonArray) throws JSONException {
        ArrayList<ItemSetupNotify> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ItemSetupNotify>>() {
        }.getType();
        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);
        return arrayList;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public boolean isOnOff() {
        return isOnOff;
    }

    public void setOnOff(boolean onOff) {
        isOnOff = onOff;
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

    public String getsURL() {
        return sURL;
    }

    public void setsURL(String sURL) {
        this.sURL = sURL;
    }
}

