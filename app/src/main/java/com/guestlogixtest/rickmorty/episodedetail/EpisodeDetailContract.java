package com.guestlogixtest.rickmorty.episodedetail;

import com.guestlogixtest.rickmorty.base.BasePresenter;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;

import java.util.List;

public interface EpisodeDetailContract {

    interface View {
        void onCharactersLoaded(List<EpisodeCharacter> characterList);
        void onCharactersLoadError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void loadCharacters(Integer episodeId);
    }

}
