package com.jw.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private MainActivity view;
    private TeamAPI teamAPI;

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {

        //Pour ceux qui veulent aller plus loin
        //Il faut créer ces objets avec des singletons.
        // Voir le cours de Génie Logiciel -> Singleton
        //Pour ceux qui veulent encore aller plus loin
        //Voir Injection de dépendances
        //On crée un objet gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://augustinmorieux.github.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre instance de notre RestAPI Pokemon.
        TeamAPI restApi = retrofit.create(TeamAPI.class);


        Call<TeamResponse> call = restApi.getTeam();
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                TeamResponse teamResponse = response.body();
                List<Team> listTeam = teamResponse.getResults();
                view.showList(listTeam);
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });

    }
}
