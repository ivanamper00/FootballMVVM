package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.models.UpcomingModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

public class UpcomingViewModel extends ViewModel {
    private MutableLiveData<List<UpcomingModel.Event>> upcoming;
    private Repositories repositories;

    public void init(){
        if(upcoming != null){
            return;
        }
        repositories = Repositories.getInstance();
        upcoming = repositories.getUpcoming(Presets.leagueId);
    }

    public LiveData<List<UpcomingModel.Event>> getUpcoming(){
        return upcoming;
    }
}
