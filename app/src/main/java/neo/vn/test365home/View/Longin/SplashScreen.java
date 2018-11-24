package neo.vn.test365home.View.Longin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import neo.vn.test365home.ActivityHome;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;


public class SplashScreen extends BaseActivity {
    private static final String TAG = "SplashScreen";
    boolean isLogin;
    boolean isThanksWelcom;
    ImageView img_splash;
    // public static Storage storage; // this Preference comes for free from the library
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    Intent mainIntent = new Intent();
    Intent mainIntent_language = new Intent();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        img_splash = (ImageView) findViewById(R.id.img_splash);
        Glide.with(this).load(R.drawable.launch).into(img_splash);
        isLogin = SharedPrefs.getInstance().get(Constants.KEY_ISLOGIN, Boolean.class);
        isThanksWelcom = SharedPrefs.getInstance().get(Constants.KEY_THANKS_WELCOM, Boolean.class);
        if (!isThanksWelcom) {
            mainIntent.setClass(SplashScreen.this, ActivityThanksWelcome.class);
            SharedPrefs.getInstance().put(Constants.KEY_THANKS_WELCOM, true);
        } else {
            if (isLogin) {
                mainIntent.setClass(SplashScreen.this, ActivityHome.class);
            } else
                mainIntent.setClass(SplashScreen.this, ActivityRegister.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_splash_screen;
    }
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
}
