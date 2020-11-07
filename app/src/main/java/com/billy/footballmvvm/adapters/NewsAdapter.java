package com.billy.footballmvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.models.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News.Channel.Item> itemsDetails;
    private LayoutInflater mInflater;
    public Context context;

    public NewsAdapter(Context context, List<News.Channel.Item> itemsDetails) {
        this.context = context;
        this.itemsDetails = itemsDetails;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.list_news, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        final News.Channel.Item newsDetails = itemsDetails.get(position);
        holder.txtTitle.setText(newsDetails.getTitle());
        holder.txtPubDate.setText(newsDetails.getPubDate());
        holder.txtDescription.setText(Html.fromHtml(newsDetails.getDescription()));
        holder.txtNewsLink.setText(newsDetails.getLink());
        String newStr = newsDetails.getDescription();
        try {
            if(newStr.contains(".jpg")){
                Picasso.get().load(newStr.substring(newStr.indexOf("src=")+5,newStr.indexOf(".jpg")+4)).into(holder.newsPic);
            }else if(newStr.contains(".png")){
                Picasso.get().load(newStr.substring(newStr.indexOf("src=")+5,newStr.indexOf(".png")+4)).into(holder.newsPic);
            }else{
                holder.newsPic.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Log.d("asdasd", position + newsDetails.getDescription());
        }

        if (position == itemsDetails.size() -1) {
            holder.viewSeparator.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtPubDate, txtDescription, txtNewsLink;
        ImageView newsPic;
        View viewSeparator;

        ViewHolder(View itemView) {
            super(itemView);
            newsPic =  itemView.findViewById(R.id.newsPic);
            txtNewsLink = itemView.findViewById(R.id.txtNewsLink);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPubDate = itemView.findViewById(R.id.txtPubDate);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            viewSeparator = itemView.findViewById(R.id.viewSeparator);
        }
    }
}