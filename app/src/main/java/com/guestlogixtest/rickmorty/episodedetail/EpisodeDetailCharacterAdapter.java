package com.guestlogixtest.rickmorty.episodedetail;

/*
 * The adapter for the episode list recycler view
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;

import java.util.List;

/*
 * The adapter for the character list recycler view
 */

public class EpisodeDetailCharacterAdapter extends RecyclerView.Adapter<EpisodeDetailCharacterAdapter.ViewHolder> {

    private List<EpisodeCharacter> characters;

    public EpisodeDetailCharacterAdapter(List<EpisodeCharacter> characters) {
        this.characters = characters;
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public EpisodeDetailCharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_characters_list, parent, false);
        return new EpisodeDetailCharacterAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EpisodeDetailCharacterAdapter.ViewHolder holder, int position) {
        EpisodeCharacter character = characters.get(position);
        holder.characterNameLbl.setText(character.name);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView characterNameLbl;

        public ViewHolder(View itemView) {
            super(itemView);
            characterNameLbl = itemView.findViewById(R.id.characterNameLbl);
        }
    }
}
