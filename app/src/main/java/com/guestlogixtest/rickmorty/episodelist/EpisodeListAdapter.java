package com.guestlogixtest.rickmorty.episodelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;
import com.guestlogixtest.rickmorty.model.entities.Episode;

import java.util.List;

/*
 * The adapter for the episode list recycler view
 */

public class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.ViewHolder> {

    private List<Episode> episodes;

    public EpisodeListAdapter(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_episode_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Episode episode = episodes.get(position);
        holder.episodeLbl.setText(String.format("%s - %s", episode.episode, episode.name));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView episodeLbl;

        public ViewHolder(View itemView) {
            super(itemView);
            episodeLbl = itemView.findViewById(R.id.episodeLbl);
        }
    }
}
