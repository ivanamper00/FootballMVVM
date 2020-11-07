package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.models.UpcomingModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

public class TeamsViewModel extends ViewModel {
    private MutableLiveData<List<TeamsModel.Teams>> teams;
    private Repositories repositories;

    public void init(){
        if(teams != null){
            return;
        }
        repositories = Repositories.getInstance();
        teams = repositories.getTeams(Presets.leagueId);
    }

    public LiveData<List<TeamsModel.Teams>> getTeams(){
        return teams;
    }
}
