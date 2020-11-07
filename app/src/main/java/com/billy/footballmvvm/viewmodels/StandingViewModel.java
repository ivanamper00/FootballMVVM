package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.models.StandingModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

import static com.billy.footballmvvm.repositories.Presets.getYear;

public class StandingViewModel extends ViewModel {
    private MutableLiveData<List<StandingModel.Table>> standings;
    private Repositories repositories;

    public void init(){
        if(standings != null){
            return;
        }
        repositories = Repositories.getInstance();
        standings = repositories.getStanding(Presets.leagueId, ((getYear()-1) + "-" + getYear()));
    }

    public LiveData<List<StandingModel.Table>> getStanding(){
        return standings;
    }
}
