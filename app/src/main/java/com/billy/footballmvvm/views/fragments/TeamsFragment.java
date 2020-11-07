package com.billy.footballmvvm.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.adapters.TeamsAdapter;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.viewmodels.TeamsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamsFragment newInstance(String param1, String param2) {
        TeamsFragment fragment = new TeamsFragment();
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
    public RecyclerView recyclerView;
    public LinearLayoutManager llm;
    public TeamsAdapter adapter;
    public TeamsViewModel teamsViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teams, container, false);
        declaration();
        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsViewModel.init();

        teamsViewModel.getTeams().observe(getViewLifecycleOwner(), result -> {
            displayData(result);
        });

        return view;
    }

    public void declaration(){
        recyclerView = view.findViewById(R.id.teams_recycler);
        llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
    }
    public void displayData(List<TeamsModel.Teams> teams){
        adapter = new TeamsAdapter(getContext(), teams);
        recyclerView.setAdapter(adapter);
    }
}