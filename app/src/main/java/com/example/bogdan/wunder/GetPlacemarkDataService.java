package com.example.bogdan.wunder;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPlacemarkDataService {

    @GET("wunderbucket/locations.json")
    Call<PlacemarksList> getPlacemarkData();
}
