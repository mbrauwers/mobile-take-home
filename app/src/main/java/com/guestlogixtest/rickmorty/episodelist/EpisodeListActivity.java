package com.guestlogixtest.rickmorty.episodelist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.guestlogixtest.rickmorty.R;

public class EpisodeListActivity extends AppCompatActivity implements EpisodeListContract.View {

    private EpisodeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_list);
        presenter = new EpisodeListPresenter();
    }

}
