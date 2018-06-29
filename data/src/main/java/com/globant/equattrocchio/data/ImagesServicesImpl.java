package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.mapper.ImageByIdMapper;
import com.globant.equattrocchio.data.mapper.ImagesResponseMapper;
import com.globant.equattrocchio.data.response.ImageById;
import com.globant.equattrocchio.data.response.ImagesResponse;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.model.CompleteImage;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.List;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<List<Image>> observer) {
        Retrofit retrofit = buildRetrofit();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<ImagesResponse> call = api.getImages();

        call.enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                //todo: show the response.body() on the ui
                ImagesResponseMapper mapper = new ImagesResponseMapper();
                observer.onNext(mapper.map(response.body()));
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                //todo: update the UI with a connection error message
                observer.onError(t);
            }
        });

    }

    @Override
    public void getImageById(final Observer<CompleteImage> observer, int id) {
        Retrofit retrofit = buildRetrofit();
        SplashbaseApi api = retrofit.create(SplashbaseApi.class);
        Call<ImageById> call = api.getImageById(id);

        call.enqueue(new Callback<ImageById>(){
            @Override
            public void onResponse(Call<ImageById> call, Response<ImageById> response) {
                CompleteImage image = (new ImageByIdMapper()).map(response.body());
                observer.onNext(image);
            }

            @Override
            public void onFailure(Call<ImageById> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    private Retrofit buildRetrofit(){
        return new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
