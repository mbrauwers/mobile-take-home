package com.guestlogixtest.rickmorty.episodedetail;

import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;
import com.guestlogixtest.rickmorty.model.repository.RickMortyRepository;
import com.guestlogixtest.rickmorty.model.services.RickMortyAPI;

import java.util.List;

public class EpisodeDetailPresenter implements EpisodeDetailContract.Presenter {

    private EpisodeDetailContract.View view;

    EpisodeDetailPresenter(EpisodeDetailContract.View view) {
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
    public void loadCharacters(Integer episodeId) {
        Episode episode = RickMortyRepository.getSingleton().getEpisodeById(episodeId);
        RickMortyAPI.getSingleton().getCharacters(episode, new BaseServiceListener<List<EpisodeCharacter>>() {
            @Override
            public void onFinished(List<EpisodeCharacter> result) {

                RickMortyRepository.getSingleton().setCurrentEpisodeCharacters(result);

                if (view != null) {
                    view.onCharactersLoaded(result);
                }
            }

            @Override
            public void onError() {
                if (view != null) {
                    view.onCharactersLoadError("Error loading characters");
                }
            }
        });
    }

    @Override
    public void characterSelected(EpisodeCharacter character) {
        if (view != null) {
            RickMortyRepository.getSingleton().setCurrentCharacter(character);
            view.goToCharacterDetail();
        }
    }
}
