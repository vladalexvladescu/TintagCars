package dagger.component;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Vlad on 3/30/2017.
 */

public interface RestApi {

    @POST("/updateUser")
    Call<ResponseBody> updateUser(@Body PostS post);

    @GET("/getAllUsers")
    Call<ResponseBody> getAllPosts();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://terraconnect.northeurope.cloudapp.azure.com:8080")
            .addConverterFactory(GsonConverterFactory.create())



            .build();

}
