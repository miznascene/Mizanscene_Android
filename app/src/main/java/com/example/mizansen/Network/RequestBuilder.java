package com.example.mizansen.Network;


import com.example.mizansen.Network.ModelNetwork.AccountModel;
import com.example.mizansen.Network.ModelNetwork.InputLoginModel;
import com.example.mizansen.Network.ModelNetwork.IpAddressModel;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.ModelNetwork.MovieArvanCloudModel;
import com.example.mizansen.Network.ModelNetwork.MoviesModel;
import com.example.mizansen.Network.ModelNetwork.ValidationModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;



public interface RequestBuilder {


    @GET("all.json")
    Call<IpAddressModel> getIpAddress();

    @POST("jwt-auth/v1/token")
    Call<AccountModel> login(@Body InputLoginModel model);

    @POST("jwt-auth/v1/token/validate")
    Call<ValidationModel> Validation(@Header("Authorization") String token);

    @GET("mobile/v1/mainpage/{pageNumber}")
    Call<List<MainpageModel>> GetMovies(@Header("Authorization") String token, @Path("pageNumber") String PageNumber);

    @GET("mobile/v1/movies/{urlPath}")
    Call<MoviesModel> GetMoviesById(@Header("Authorization") String token, @Path("urlPath") String IdMovie);

    @GET("videos/{urlPath}")
    Call<MovieArvanCloudModel> GetMovieArvanCloudById(@Header("Authorization") String token, @Path("urlPath") String IdMovieArvanCloud);


}