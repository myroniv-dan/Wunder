package com.example.bogdan.wunder;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface QueryPlacemarksCompletion {
    void operation(@Nullable List<PlacemarkJSON> placemarks, @Nullable Throwable t);
}

class PlacemarksList {

    @SerializedName("placemarks")
    private List<PlacemarkJSON> placemarkList;

    public List<PlacemarkJSON> getPlacemarksList() {
        return placemarkList;
    }

    public void setPlacemarksList(List<PlacemarkJSON> placemarksArrayList) {
        this.placemarkList = placemarksArrayList;
    }

    public static void queryPlacemarks(final QueryPlacemarksCompletion completion){
        GetPlacemarkDataService service = RetrofitInstance.getRetrofitInstance().create(GetPlacemarkDataService.class);
        Call<PlacemarksList> call = service.getPlacemarkData();

        call.enqueue(new Callback<PlacemarksList>() {
            @Override
            public void onResponse(Call<PlacemarksList> call, Response<PlacemarksList> response) {
                List<PlacemarkJSON> placemarks = response.body().getPlacemarksList();
                completion.operation(placemarks, null);
            }

            @Override
            public void onFailure(Call<PlacemarksList> call, Throwable t) {
                completion.operation(null, t);
            }
        });
    }
}
