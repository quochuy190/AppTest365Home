package neo.vn.test365home;

import android.app.Application;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.Models.City;
import neo.vn.test365home.Models.District;
import neo.vn.test365home.Models.Schools;

public class App extends Application {
    private static App sInstance;
    private Gson mGSon;
    public static App self() {
        return sInstance;
    }
    public static District mDistrict;
    public static City mCity;
    public static Schools mSchools;
    public static String mKhoihoc;
    public static List<District> mLisDistrict;
    public static List<City> mLisCity;
    public static List<Schools> mLisSchools;
    public static List<Childrens> mLisChildren;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGSon = new Gson();
        mLisDistrict = new ArrayList<>();
        mLisCity = new ArrayList<>();
        mLisSchools = new ArrayList<>();
        mLisChildren = new ArrayList<>();
    }

    public Gson getGSon() {
        return mGSon;
    }
}
