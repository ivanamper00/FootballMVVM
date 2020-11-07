package com.billy.footballmvvm.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.StandingModel;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.repositories.Presets;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeTeamsAdapter extends RecyclerView.Adapter<HomeTeamsAdapter.HomeTeamViewHolder> {
    Context context;
    List<TeamsModel.Teams> teamsList;
    List<StandingModel.Table> standings;
    TeamsModel.Teams team;
    Dialog dialog;
    public class HomeTeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamLogo;

        public HomeTeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.home_team_name);
            teamLogo = itemView.findViewById(R.id.home_team_logo);
        }
    }


    public HomeTeamsAdapter(Context context, List<TeamsModel.Teams> teamsList, List<StandingModel.Table> standings){
        this.context = context;
        this.teamsList = teamsList;
        this.standings = standings;
    }

    @NonNull
    @Override
    public HomeTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_teams,parent,false);
        return new HomeTeamViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final HomeTeamViewHolder holder, int position) {

        team = teamsList.get(position);
        holder.teamName.setText(team.getStrTeam());
        Picasso.get().load(team.getStrTeamBadge()).into(holder.teamLogo);

        holder.itemView.setOnClickListener( v ->{
            displayDialog(teamsList.get(position),standings);
        });
    }



    @Override
    public int getItemCount() {
        return teamsList.size();
    }


    @SuppressLint("SetTextI18n")
    public void displayDialog(TeamsModel.Teams team, List<StandingModel.Table> standings){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.details_standings);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        RelativeLayout loading = dialog.findViewById(R.id.relative_loading);
        ImageView close = dialog.findViewById(R.id.details_standings_close);
        TextView teamName = dialog.findViewById(R.id.details_standings_team_name);
        TextView played = dialog.findViewById(R.id.details_standings_team_played);
        TextView win = dialog.findViewById(R.id.details_standings_team_win);
        TextView draw = dialog.findViewById(R.id.details_standings_team_draw);
        TextView loss = dialog.findViewById(R.id.details_standings_team_loss);
        ImageView banner = dialog.findViewById(R.id.details_standings_team_banner);

        Picasso.get().load(team.getStrTeamBanner()).into(banner, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loading.setVisibility(View.GONE);
            }
        });
        for(int i=0 ; i < standings.size(); i++){
            if(standings.get(i).getTeamid().equalsIgnoreCase(team.getIdTeam())){
                played.setText( "Played: " + String.valueOf(standings.get(i).getPlayed()));
                win.setText("Wins: " +String.valueOf(standings.get(i).getWin()));
                draw.setText("Draws: " +String.valueOf(standings.get(i).getDraw()));
                loss.setText("Losses: " +String.valueOf(standings.get(i).getLoss()));
                break;
            }
            if(i == standings.size()-1 && !standings.get(i).getTeamid().equalsIgnoreCase(team.getIdTeam())){
                played.setText("No Data Available.");
                win.setText("");
                draw.setText("");
                loss.setText("");
            }
        }
        teamName.setText(team.getStrTeam());
        close.setOnClickListener(v -> {
            dialog.dismiss();
        });



    }

}
