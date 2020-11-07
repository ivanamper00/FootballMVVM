package com.billy.footballmvvm.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.adapters.ViewPageAdapter;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.repositories.Presets;
import com.billy.footballmvvm.viewmodels.AboutViewModel;
import com.billy.footballmvvm.viewmodels.HomeViewModel;
import com.billy.footballmvvm.views.activities.WebContentActivity;
import com.billy.footballmvvm.views.fragments.aboutfragments.AboutLeagueFragment;
import com.billy.footballmvvm.views.fragments.aboutfragments.PrivacyFragment;
import com.billy.footballmvvm.views.fragments.matchesfragments.HighlightsFragment;
import com.billy.footballmvvm.views.fragments.matchesfragments.UpcomingFragment;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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
    public CarouselView carouselView;
    public TextView leagueName;
    private AboutViewModel aboutViewModel;
    public View view;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    public ViewPageAdapter viewPageAdapter;
    public ImageView youtube;
    public ImageView website;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        declarations();
        main();
        aboutViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        aboutViewModel.init();

        aboutViewModel.getLeague().observe(getViewLifecycleOwner(), result -> {
            displayData(result);
        });


        return view;
    }
    public void declarations(){
        carouselView = view.findViewById(R.id.carouselView);
        leagueName = view.findViewById(R.id.league_name);
        viewPager = view.findViewById(R.id.about_view_pager);
        tabLayout = view.findViewById(R.id.about_tab_layout);
        youtube = view.findViewById(R.id.youtube_link);
        website = view.findViewById(R.id.website_link);
    }

    public void displayData(LeagueModel.League league){
        String[] images = {league.getStrFanart1(),league.getStrFanart2(),league.getStrFanart3(),league.getStrFanart4()};
        carouselView.setImageListener((position,imageView)-> {
            Picasso.get().load(images[position]).into(imageView);
        });
        carouselView.setPageCount(images.length);
        leagueName.setText(league.getStrLeague());
        youtube.setOnClickListener(v ->{
            if(league.getStrYoutube() != null){
                Presets.toLink(getContext(),league.getStrYoutube());
            }else{
                Toast.makeText(getContext(), "No Youtube Account", Toast.LENGTH_SHORT).show();
            }
        });
        website.setOnClickListener(v ->{
            if(league.getStrWebsite() != null){
                Presets.toLink(getContext(),league.getStrWebsite());
            }else{
                Toast.makeText(getContext(), "No Website", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void main(){
        viewPageAdapter = new ViewPageAdapter(getActivity().getSupportFragmentManager(),0);
        viewPageAdapter.addFragment(new AboutLeagueFragment(), "About League");
        viewPageAdapter.addFragment(new PrivacyFragment(), "Securities");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}