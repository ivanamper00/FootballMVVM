package com.billy.footballmvvm.views.fragments.aboutfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.LeagueModel;
import com.billy.footballmvvm.viewmodels.AboutViewModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutLeagueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutLeagueFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutLeagueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutLeagueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutLeagueFragment newInstance(String param1, String param2) {
        AboutLeagueFragment fragment = new AboutLeagueFragment();
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
    public AboutViewModel aboutViewModel;
    public ImageView leagueLogo;
    public ImageView leagueTrophy;
    public TextView leagueDescription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_league, container, false);
        declaration();
        aboutViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        aboutViewModel.init();

        aboutViewModel.getLeague().observe(getViewLifecycleOwner(), result ->{
            displayData(result);
        });

        return view;
    }
    public void declaration(){
        leagueDescription = view.findViewById(R.id.league_description);
        leagueLogo = view.findViewById(R.id.league_logo);
        leagueTrophy = view.findViewById(R.id.league_trophy);
    }
    @SuppressLint("SetTextI18n")
    public void displayData(LeagueModel.League league){
        leagueDescription.setText("\t\t\t "+league.getStrDescriptionEN());
        Picasso.get().load(league.getStrBadge()).into(leagueLogo);
        Picasso.get().load(league.getStrTrophy()).into(leagueTrophy);
    }
}