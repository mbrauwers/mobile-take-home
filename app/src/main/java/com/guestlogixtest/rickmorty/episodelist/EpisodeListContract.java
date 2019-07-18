package com.guestlogixtest.rickmorty.episodelist;

import com.guestlogixtest.rickmorty.base.BasePresenter;
import com.guestlogixtest.rickmorty.model.entities.Episode;

import java.util.List;

public interface EpisodeListContract {

    interface View {
        void onEpisodesLoaded(List<Episode> episodes);
        void onEpisodesLoadError(String error);
        void goToEpisodeDetail(Episode episode);
    }

    interface Presenter extends BasePresenter {
        void loadEpisodes();
        void onEpisodeSelected(Episode episode);
    }

}
