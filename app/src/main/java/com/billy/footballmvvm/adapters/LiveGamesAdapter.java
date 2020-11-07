package com.billy.footballmvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.LiveScoreModel;

import java.util.List;

import static com.billy.footballmvvm.repositories.Presets.converted;

public class LiveGamesAdapter extends RecyclerView.Adapter<LiveGamesAdapter.LiveGamesViewHolder> {
    Context context;
    List<LiveScoreModel> events;
    LiveScoreModel event;
    public static class LiveGamesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        WebView vids;
        @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
        public LiveGamesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.live_games_title);
            description = itemView.findViewById(R.id.live_games_competition_name);
            date = itemView.findViewById(R.id.live_games_date);
            vids = itemView.findViewById(R.id.live_games_vids);
            vids.getSettings().setJavaScriptEnabled(true);
            vids.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
        }
    }

    public LiveGamesAdapter(Context context, List<LiveScoreModel>  events){
        this.context = context;
        this.events = events;
    }
    @NonNull
    @Override
    public LiveGamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_live_game,parent,false);
        return new LiveGamesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LiveGamesViewHolder holder, int position) {
        event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.description.setText(event.getCompetition().getName());
        holder.date.setText(event.getDate().substring(0,10) + " " + converted(event.getDate().substring(11,19)));
        String embeded = event.getEmbed();
        holder.vids.loadData(embeded, "text/html", "UTF-8");
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}