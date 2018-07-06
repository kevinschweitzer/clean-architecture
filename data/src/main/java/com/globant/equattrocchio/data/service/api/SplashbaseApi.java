package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.ImageByIdResponse;
import com.globant.equattrocchio.data.response.ImagesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Call<ImagesResponse> getImages();

    @GET("api/v1/images/{id}")
    Call<ImageByIdResponse> getImageById(@Path("id") long id);

}
