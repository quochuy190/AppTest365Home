package neo.vn.test365home.ApiService;


import java.util.concurrent.TimeUnit;

import neo.vn.test365home.Config.Config;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by QQ on 7/4/2017.
 */

public interface ApiSeviceUploadImage {
    //Log info action user
    @Multipart
    @POST("/ImageUpload")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL_IMAGE_UPLOAD)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

}
