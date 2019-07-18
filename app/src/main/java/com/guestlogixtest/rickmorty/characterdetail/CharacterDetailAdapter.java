package com.guestlogixtest.rickmorty.characterdetail;

/*
 * The adapter for the character detail recycler view
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Adapter for character detail
 * We receive the fields and labels as a hashmap and show all this info through this rec view
 */

public class CharacterDetailAdapter extends RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder> {

    private List<String> fields;
    private List<String> values;

    public CharacterDetailAdapter(Map<String, String> mapValues) {
        fields = new ArrayList<String>(mapValues.keySet());
        values = new ArrayList<String>(mapValues.values());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_character_detail, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String field = fields.get(position);
        String value = values.get(position);
        holder.fieldLbl.setText(field);
        holder.valueLbl.setText(value);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fieldLbl;
        public TextView valueLbl;

        public ViewHolder(View itemView) {
            super(itemView);
            fieldLbl = itemView.findViewById(R.id.characterFieldLbl);
            valueLbl = itemView.findViewById(R.id.characterValueLbl);
        }
    }
}