package com.guestlogixtest.rickmorty.episodelist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;
import com.guestlogixtest.rickmorty.base.Constants;
import com.guestlogixtest.rickmorty.episodedetail.EpisodeDetailActivity;
import com.guestlogixtest.rickmorty.model.entities.Episode;

import java.util.List;

public class EpisodeListActivity extends AppCompatActivity implements EpisodeListContract.View {

    private EpisodeListPresenter presenter;
    private EpisodeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_list);
        presenter = new EpisodeListPresenter(this);
        presenter.loadEpisodes();
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

    //configures adapter with the provided episodes list
    public void onEpisodesLoaded(List<Episode> episodes) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.episodeListRecView);
        adapter = new EpisodeListAdapter(episodes, new OnItemClicked() {
            @Override
            public void onItemClicked(Episode episode) {
                presenter.onEpisodeSelected(episode);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    //we had an error while loading the episodes data
    public void onEpisodesLoadError(String error) {
        showErrorMessage(error);
    }

    @Override
    public void goToEpisodeDetail(Integer episodeId) {
        Intent intent = new Intent(this, EpisodeDetailActivity.class);
        intent.putExtra(Constants.INTENT_PARAM_EPISODE_ID, episodeId);
        startActivity(intent);
    }

    void configUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.episodeListRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //shows error message
    void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}
