package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.LiveScoreModel;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

public class LiveViewModel extends ViewModel {
    private MutableLiveData<List<LiveScoreModel>> live;
    private Repositories repositories;

    public void init(){
        if(live != null){
            return;
        }
        repositories = Repositories.getInstance();
        live = repositories.getLiveGames();
    }

    public LiveData<List<LiveScoreModel>> getLive(){
        return live;
    }
}
