package com.billy.footballmvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.UpcomingModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.billy.footballmvvm.repositories.Presets.converted;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {
    Context context;
    List<UpcomingModel.Event> events;
    UpcomingModel.Event event;
    public static class UpcomingViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        ImageView leagueThumb;
        @SuppressLint("SetJavaScriptEnabled")
        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.upcoming_title);
            description = itemView.findViewById(R.id.upcoming_description);
            date = itemView.findViewById(R.id.upcoming_date);
            leagueThumb = itemView.findViewById(R.id.upcoming_league_thumb);
        }
    }

    public UpcomingAdapter(Context context, List<UpcomingModel.Event> events){
        this.context = context;
        this.events = events;
    }
    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_upcoming,parent,false);
        return new UpcomingViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        event = events.get(position);
        holder.title.setText(event.getStrEvent());
        holder.description.setText("League:\t" + event.getStrLeague() + "\nVenue:\t" + event.getStrVenue());
        holder.date.setText(event.getDateEvent() + " " + converted(event.getStrTime()));
        Picasso.get().load(event.getStrThumb()).into(holder.leagueThumb);
//        String embeded = event.getStrVideo().replace("watch?v=", "embed/");
//        holder.vids.loadUrl(embeded);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
