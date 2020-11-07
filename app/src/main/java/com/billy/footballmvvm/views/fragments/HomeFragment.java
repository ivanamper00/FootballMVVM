package com.billy.footballmvvm.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.adapters.HomeTeamsAdapter;
import com.billy.footballmvvm.adapters.LiveGamesAdapter;
import com.billy.footballmvvm.adapters.NewsAdapter;
import com.billy.footballmvvm.adapters.TeamsAdapter;
import com.billy.footballmvvm.adapters.UpcomingAdapter;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.models.LiveScoreModel;
import com.billy.footballmvvm.models.News;
import com.billy.footballmvvm.models.StandingModel;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.models.UpcomingModel;
import com.billy.footballmvvm.viewmodels.HomeViewModel;
import com.billy.footballmvvm.viewmodels.LiveViewModel;
import com.billy.footballmvvm.viewmodels.NewsViewModel;
import com.billy.footballmvvm.viewmodels.StandingViewModel;
import com.billy.footballmvvm.viewmodels.TeamsViewModel;
import com.billy.footballmvvm.viewmodels.UpcomingViewModel;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public View view;
    public UpcomingViewModel upcomingViewModel;
    public TeamsViewModel teamsViewModel;
    public StandingViewModel standingViewModel;
    public NewsViewModel newsViewModel;
    public RecyclerView recyclerView;
    public RecyclerView teamsRecyclerView;
    public RecyclerView newsRecyclerView;
    public LinearLayoutManager llm;
    public LinearLayoutManager llm1;
    public UpcomingAdapter upcomingAdapter;
    public NewsAdapter newsAdapter;
    public HomeTeamsAdapter homeTeamsAdapter;
    public GridLayoutManager grid;
    public LinearLayout homeLinear;
    public List<StandingModel.Table> standings = new ArrayList<>();
    public List<UpcomingModel.Event> upcoming  = new ArrayList<>();
    public List<TeamsModel.Teams> teams  = new ArrayList<>();
    public List<News.Channel.Item> news  = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        declarations();

        standingViewModel = new ViewModelProvider(this).get(StandingViewModel.class);
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.init();
        upcomingViewModel.init();
        teamsViewModel.init();
        standingViewModel.init();


        newsViewModel.getNews().observe(getViewLifecycleOwner(), result ->{
            news.addAll(result);
            String newStr = result.get(0).getDescription();
            Log.d("asdasd", newStr.substring(newStr.indexOf("src")+5,newStr.indexOf(".jpg")+4));
            newsAdapter.notifyDataSetChanged();
        });
        upcomingViewModel.getUpcoming().observe(getViewLifecycleOwner(), result->{
            upcoming.addAll(result);
            if(upcomingAdapter != null){
                upcomingAdapter.notifyDataSetChanged();
                homeLinear.setVisibility(View.VISIBLE);
            }
        });
        teamsViewModel.getTeams().observe(getViewLifecycleOwner(), result->{
            teams.addAll(result);
            homeTeamsAdapter.notifyDataSetChanged();
        });
        standingViewModel.getStanding().observe(getViewLifecycleOwner(), result->{
            standings.addAll(result);
            homeTeamsAdapter.notifyDataSetChanged();
        });

        displayUpcoming();
        displayTeams();
        displayNews();
        return view;
    }
    public void declarations(){
        homeLinear = view.findViewById(R.id.home_layout);
        homeLinear.setVisibility(View.GONE);

        //News
        newsRecyclerView = view.findViewById(R.id.recyclerViewNews);
        llm1 = new LinearLayoutManager(getContext());
        newsRecyclerView.setLayoutManager(llm1);
       //Upcoming
        recyclerView = view.findViewById(R.id.home_upcoming_recycler);
        llm = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(llm);
        //Teams
        teamsRecyclerView = view.findViewById(R.id.home_teams_recycler);
        grid = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL,false);
        teamsRecyclerView.setLayoutManager(grid);
    }

    public void displayUpcoming(){
        upcomingAdapter = new UpcomingAdapter(getContext(), upcoming);
        recyclerView.setAdapter(upcomingAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
    public void displayTeams(){
        homeTeamsAdapter = new HomeTeamsAdapter(getContext(), teams, standings);
        teamsRecyclerView.setAdapter(homeTeamsAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(teamsRecyclerView);
    }
    public void displayNews(){
        newsAdapter = new NewsAdapter(getContext(), news);
        newsRecyclerView.setAdapter(newsAdapter);
    }
}