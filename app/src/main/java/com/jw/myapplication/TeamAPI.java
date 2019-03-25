package com.jw.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamAPI {

        @GET("nba.Json")
        Call<TeamResponse> getTeam();

}
