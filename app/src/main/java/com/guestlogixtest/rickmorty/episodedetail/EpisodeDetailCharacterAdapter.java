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
    private OnItemClicked listener;

    public EpisodeDetailCharacterAdapter(List<EpisodeCharacter> characters, OnItemClicked listener) {
        this.characters = characters;
        this.listener = listener;
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
        final EpisodeCharacter character = characters.get(position);
        holder.characterNameLbl.setText(character.name);

        holder.aliveLbl.setVisibility(View.INVISIBLE);
        holder.deadLbl.setVisibility(View.INVISIBLE);

        if (character.status.trim().equals("Alive")) {
            holder.aliveLbl.setVisibility(View.VISIBLE);
        }
        else {
            holder.deadLbl.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClicked(character);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView characterNameLbl;
        public TextView deadLbl;
        public TextView aliveLbl;

        public ViewHolder(View itemView) {
            super(itemView);
            characterNameLbl = itemView.findViewById(R.id.characterNameLbl);
            aliveLbl = itemView.findViewById(R.id.characterAliveLbl);
            deadLbl = itemView.findViewById(R.id.characterDeadLbl);
        }
    }
}

interface OnItemClicked {
    void onItemClicked(EpisodeCharacter character);
}

