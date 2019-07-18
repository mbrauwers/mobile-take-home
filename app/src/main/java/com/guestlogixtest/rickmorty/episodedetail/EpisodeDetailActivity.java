package com.guestlogixtest.rickmorty.episodedetail;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;
import com.guestlogixtest.rickmorty.base.Constants;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;

import java.util.List;

public class EpisodeDetailActivity extends AppCompatActivity implements EpisodeDetailContract.View {

    private EpisodeDetailPresenter presenter;
    private EpisodeDetailCharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);
        presenter = new EpisodeDetailPresenter(this);

        if (getIntent().getIntExtra(Constants.INTENT_PARAM_EPISODE_ID, -1) >= 0) {
            presenter.loadCharacters(getIntent().getIntExtra(Constants.INTENT_PARAM_EPISODE_ID, -1));
        }

        configUI();
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

    @Override
    public void onCharactersLoaded(List<EpisodeCharacter> characterList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.episodeListRecView);
        adapter = new EpisodeDetailCharacterAdapter(characterList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCharactersLoadError(String errorMessage) {
        showErrorMessage(errorMessage);
    }

    void configUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.charactersRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //shows error message
    void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}