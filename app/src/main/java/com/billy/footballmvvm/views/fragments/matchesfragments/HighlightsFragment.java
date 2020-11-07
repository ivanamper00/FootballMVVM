package com.billy.footballmvvm.views.fragments.matchesfragments;

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
import com.billy.footballmvvm.adapters.HighlightsAdapter;
import com.billy.footballmvvm.models.EventsModel;
import com.billy.footballmvvm.viewmodels.HighlightsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighlightsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HighlightsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighlightsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HighlightsFragment newInstance(String param1, String param2) {
        HighlightsFragment fragment = new HighlightsFragment();
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
    public HighlightsViewModel highlightsViewModel;
    public RecyclerView recyclerView;
    public HighlightsAdapter adapter;
    public LinearLayoutManager llm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_highlights, container, false);
        highlightsViewModel = new ViewModelProvider(this).get(HighlightsViewModel.class);
        highlightsViewModel.init();

        highlightsViewModel.getHighlights().observe(getViewLifecycleOwner(), result -> {
            dataDisplay(result);
        });
        declaration();
        return view;
    }
    public void declaration(){
        recyclerView = view.findViewById(R.id.highlights_recycler);
        llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
    }
    public void dataDisplay(List<EventsModel.Event> highlights){
        adapter = new HighlightsAdapter(getContext(),highlights);
        recyclerView.setAdapter(adapter);
    }
}