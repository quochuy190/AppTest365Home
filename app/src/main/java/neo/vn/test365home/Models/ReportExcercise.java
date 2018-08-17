package neo.vn.test365home.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReportExcercise {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;

    public ReportExcercise() {
    }
    private static ReportExcercise getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),ReportExcercise.class);
    }

    public  static ArrayList<ReportExcercise> getList(String jsonArray) throws JSONException {
        ArrayList<ReportExcercise> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ReportExcercise>>(){}.getType();
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
}

