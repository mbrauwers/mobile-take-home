package com.guestlogixtest.rickmorty.characterdetail;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;

import java.util.Map;

public class CharacterDetailActivity extends AppCompatActivity implements CharacterDetailContract.View {

    private CharacterDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        presenter = new CharacterDetailPresenter(this);
        configUI();
        presenter.loadCharacter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    void configUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.characterDetailRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onCharacterLoaded(Map<String, String> values) {
        Log.d("msg", "CharacterDetailActivity::onCharacterLoaded " + values.size());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.characterDetailRecView);
        CharacterDetailAdapter adapter = new CharacterDetailAdapter(values);
        recyclerView.setAdapter(adapter);
    }

}