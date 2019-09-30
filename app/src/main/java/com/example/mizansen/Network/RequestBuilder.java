package com.example.mizansen.Network;


import com.example.mizansen.Network.ModelNetwork.IpAddressModel;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.ModelNetwork.MovieArvanCloudModel;
import com.example.mizansen.Network.ModelNetwork.MoviesModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;



public interface RequestBuilder {


    @GET("all.json")
    Call<IpAddressModel> getIpAddress();

    @GET("mobile/v1/movies/{urlPath}")
    Call<MoviesModel> GetMoviesById(@Header("Authorization") String token, @Path("urlPath") String IdMovie);

    @GET("videos/{urlPath}")
    Call<MovieArvanCloudModel> GetMovieArvanCloudById(@Header("Authorization") String token, @Path("urlPath") String IdMovieArvanCloud);


}
