package com.guestlogixtest.rickmorty.model.repository;

/*
 * Repository class to get in memory data
 */

import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;

import java.util.List;

public class RickMortyRepository {

    private static RickMortyRepository singleton;
    private List<Episode> episodes;
    private List<EpisodeCharacter> currentEpisodeCharacters;

    public static RickMortyRepository getSingleton() {
        if (singleton == null) {
            singleton = new RickMortyRepository();
        }

        return singleton;
    }

    public Episode getEpisodeById(Integer id) {
        for (Episode episode: episodes) {
            if (episode.id == id) {
                return episode;
            }
        }

        return null;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }


    public List<EpisodeCharacter> getCurrentEpisodeCharacters() {
        return currentEpisodeCharacters;
    }

    public void setCurrentEpisodeCharacters(List<EpisodeCharacter> currentEpisodeCharacters) {
        this.currentEpisodeCharacters = currentEpisodeCharacters;
    }
}
