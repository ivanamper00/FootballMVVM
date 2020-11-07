package com.billy.footballmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.models.News;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.repositories.Repositories;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<News.Channel.Item>> news;
    private Repositories repositories;

    public void init(){
        if(news != null){
            return;
        }
        repositories = Repositories.getInstance();
        news = repositories.getNews();
    }

    public LiveData<List<News.Channel.Item>> getNews(){
        return news;
    }
}
