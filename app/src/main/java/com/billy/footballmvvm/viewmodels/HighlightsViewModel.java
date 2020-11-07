package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

public class HighlightsViewModel extends ViewModel {
    private MutableLiveData<List<EventsModel.Event>> highlights;
    private Repositories repositories;

    public void init(){
        if(highlights != null){
            return;
        }
        repositories = Repositories.getInstance();
        highlights = repositories.getHighlights(Presets.leagueId);
    }

    public LiveData<List<EventsModel.Event>> getHighlights(){
        return highlights;
    }
}
