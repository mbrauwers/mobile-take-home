package com.guestlogixtest.rickmorty.episodelist;

import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;
import com.guestlogixtest.rickmorty.model.repository.RickMortyRepository;
import com.guestlogixtest.rickmorty.model.services.RickMortyAPI;

public class EpisodeListPresenter implements EpisodeListContract.Presenter {

    private EpisodeListContract.View view;

    EpisodeListPresenter(EpisodeListContract.View view) {
        this.view = view;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void loadEpisodes() {
        RickMortyAPI.getSingleton().getEpisodes(new BaseServiceListener<EpisodeListResponse>() {
            @Override
            public void onFinished(EpisodeListResponse result) {

                RickMortyRepository.getSingleton().setEpisodes(result.episodes);

                if (view != null) {
                    view.onEpisodesLoaded(result.episodes);
                }
            }

            @Override
            public void onError() {
                if (view != null) {
                    view.onEpisodesLoadError("Error loading episodes");
                }
            }
        });
    }
}
