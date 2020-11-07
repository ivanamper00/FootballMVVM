package com.billy.footballmvvm.repositories;


import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.models.LiveScoreModel;
import com.billy.footballmvvm.models.News;
import com.billy.footballmvvm.models.StandingModel;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.models.UpcomingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootballApi {
    String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";
    String LIVE_URL = "https://www.scorebat.com/video-api/";
    String RSS_URL = "https://www.allarsenal.com/";

    //RSS_URL
    @GET("feed/")
    Call<News> getNewsDetails();

    //LIVE_URL
    @GET("v1")
    Call<List<LiveScoreModel>> getLiveGames();

    // BASE_URL
    @GET("lookup_all_teams.php")
    Call<TeamsModel> getTeams(@Query("id") String league);

    @GET("eventsnextleague.php")
    Call<UpcomingModel> getUpcoming(@Query("id") String league);

    @GET("eventspastleague.php")
    Call<EventsModel> getHighlights(@Query("id") String league);

    @GET("lookupleague.php")
    Call<LeagueModel> getLeague(@Query("id") String league);

    @GET("lookuptable.php")
    Call<StandingModel> getStanding(@Query("l") String league, @Query("s") String series);

}
