package com.guestlogixtest.rickmorty.episodedetail;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.guestlogixtest.rickmorty.R;

public class EpisodeDetailActivity extends AppCompatActivity implements EpisodeDetailContract.View {

    private EpisodeDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);
        presenter = new EpisodeDetailPresenter();
    }

}