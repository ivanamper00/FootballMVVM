package com.billy.footballmvvm.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.TeamsModel;
import com.billy.footballmvvm.repositories.Presets;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {
    Context context;
    List<TeamsModel.Teams> teamsList;
    TeamsModel.Teams team;
    Dialog dialog;
    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamStadium;
        ImageView teamLogo;

        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamStadium = itemView.findViewById(R.id.team_stadium);
            teamLogo = itemView.findViewById(R.id.team_logo);
        }
    }


    public TeamsAdapter(Context context, List<TeamsModel.Teams> teamsList){
        this.context = context;
        this.teamsList = teamsList;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teams,parent,false);
        return new TeamsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TeamsViewHolder holder, final int position) {

        team = teamsList.get(position);
        holder.teamName.setText(team.getStrTeam());
        Picasso.get().load(team.getStrStadiumThumb()).into(holder.teamStadium);
        Picasso.get().load(team.getStrTeamLogo()).into(holder.teamLogo);

        holder.itemView.setOnClickListener( v ->{
//            Toast.makeText(context, teamsList.get(position).getIdTeam(), Toast.LENGTH_SHORT).show();
            displayDialog(teamsList.get(position));
        });
    }



    @Override
    public int getItemCount() {
        return teamsList.size();
    }


    @SuppressLint("SetTextI18n")
    public void displayDialog(TeamsModel.Teams team){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.details_team);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView teamName = dialog.findViewById(R.id.details_team_name);
        TextView teamDesc = dialog.findViewById(R.id.details_team_description);
        ImageView facebook = dialog.findViewById(R.id.details_team_fb);
        ImageView instagram = dialog.findViewById(R.id.details_team_insta);
        ImageView banner = dialog.findViewById(R.id.details_team_banner);
        ImageView close = dialog.findViewById(R.id.details_team_back);

        teamName.setText(team.getStrTeam());
        teamDesc.setText("\t\t\t "+ team.getStrDescriptionEN());
        Picasso.get().load(team.getStrTeamBanner()).into(banner);
        facebook.setOnClickListener(v->{
            Presets.toLink(context,team.getStrFacebook());
        });
        instagram.setOnClickListener(v->{
            Presets.toLink(context,team.getStrInstagram());
        });
        close.setOnClickListener(v->{
            dialog.dismiss();
        });

    }

}
