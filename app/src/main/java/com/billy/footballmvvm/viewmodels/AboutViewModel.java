package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

public class AboutViewModel extends ViewModel {
    private MutableLiveData<LeagueModel.League> league;
    private Repositories repositories;

    public void init(){
        if(league != null){
            return;
        }
        repositories = Repositories.getInstance();
        league = repositories.getLeague(Presets.leagueId);
    }

    public LiveData<LeagueModel.League> getLeague(){
        return league;
    }
}
