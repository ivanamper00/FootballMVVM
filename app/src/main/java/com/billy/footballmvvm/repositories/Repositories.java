package com.billy.footballmvvm.repositories;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;


import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.models.LiveScoreModel;
import com.billy.footballmvvm.models.News;
import com.billy.footballmvvm.models.StandingModel;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.models.UpcomingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories {

    private static Repositories instance;
    private FootballApi api;

    public static Repositories getInstance(){
        if(instance == null){
            instance = new Repositories();
        }
        return instance;
    }

    public Repositories() {
    }

    public MutableLiveData<List<TeamsModel.Teams>> getTeams(String League){
        MutableLiveData<List<TeamsModel.Teams>> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.BASE_URL).getTeams(League).enqueue(new Callback<TeamsModel>() {

            @Override
            public void onResponse(Call<TeamsModel> call, Response<TeamsModel> response) {
                data.setValue(response.body().getTeams());
            }

            @Override
            public void onFailure(Call<TeamsModel> call, Throwable t) {
               Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }

    public MutableLiveData<List<UpcomingModel.Event>> getUpcoming(String League){
        MutableLiveData<List<UpcomingModel.Event>> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.BASE_URL).getUpcoming(League).enqueue(new Callback<UpcomingModel>() {

            @Override
            public void onResponse(Call<UpcomingModel> call, Response<UpcomingModel> response) {
                data.setValue(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<UpcomingModel> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }

    public MutableLiveData<List<EventsModel.Event>> getHighlights(String league){
        MutableLiveData<List<EventsModel.Event>> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.BASE_URL).getHighlights(league).enqueue(new Callback<EventsModel>() {

            @Override
            public void onResponse(Call<EventsModel> call, Response<EventsModel> response) {
                data.setValue(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<EventsModel> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }

    public  MutableLiveData<List<LiveScoreModel>> getLiveGames() {
        MutableLiveData<List<LiveScoreModel>> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.LIVE_URL).getLiveGames().enqueue(new Callback<List<LiveScoreModel>>() {

            @Override
            public void onResponse(Call<List<LiveScoreModel>> call, Response<List<LiveScoreModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<LiveScoreModel>> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }

    public  MutableLiveData<LeagueModel.League> getLeague(String league) {
        MutableLiveData<LeagueModel.League> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.BASE_URL).getLeague(league).enqueue(new Callback<LeagueModel>() {

            @Override
            public void onResponse(Call<LeagueModel> call, Response<LeagueModel> response) {
                data.setValue(response.body().getLeagues().get(0));
            }

            @Override
            public void onFailure(Call<LeagueModel> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }

    public  MutableLiveData<List<StandingModel.Table>> getStanding(String league, String series) {
        MutableLiveData<List<StandingModel.Table>> data = new MutableLiveData<>();
        RetrofitService.retrofitService(FootballApi.class, FootballApi.BASE_URL).getStanding(league, series).enqueue(new Callback<StandingModel>() {

            @Override
            public void onResponse(Call<StandingModel> call, Response<StandingModel> response) {
                data.setValue(response.body().getTable());
            }

            @Override
            public void onFailure(Call<StandingModel> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }


    public  MutableLiveData<List<News.Channel.Item>> getNews() {
        MutableLiveData<List<News.Channel.Item>> data = new MutableLiveData<>();
        RetrofitService.retrofitXMLService(FootballApi.class, FootballApi.RSS_URL).getNewsDetails().enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                data.setValue(response.body().getChannel().getItem());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });
        return data;
    }
}
