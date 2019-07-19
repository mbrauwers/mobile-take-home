package com.guestlogixtest.rickmorty.model.services;

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.BaseServiceListAsyncTask;
import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.base.BaseServiceObjectAsyncTask;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;

import java.util.ArrayList;
import java.util.List;

public class RickMortyAPI {

    //base API URL
    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    //our singleton data holder
    private static RickMortyAPI mSingleton = null;

    //API call to get the episodes
    public void getEpisodes(BaseServiceListener<List<Episode>> listener) {
        getEpisodesInternal(new ArrayList<Episode>(),BASE_URL + "/episode", listener);
    }

    //this is a recursive function that gets the episodes from all the pages
    private void getEpisodesInternal(final List<Episode> episodes, String url, final BaseServiceListener<List<Episode>> finalListener) {

        BaseServiceListener<EpisodeListResponse> internalListener = new BaseServiceListener<EpisodeListResponse>() {
            @Override
            public void onFinished(EpisodeListResponse result) {
                Log.d("msg","getEpisodes onFinished");
                episodes.addAll(result.episodes);
                if (result.info.next == null || result.info.next.length() == 0) {
                    Log.d("msg", "Finished all pages");
                    if (finalListener != null) {
                        finalListener.onFinished(episodes);
                    }
                }
                else {
                    Log.d("msg", "We've a next page");
                    getEpisodesInternal(episodes, result.info.next, finalListener);
                }
            }

            @Override
            public void onError() {
                Log.d("msg","getEpisodes onError");
            }
        };

        BaseServiceObjectAsyncTask<EpisodeListResponse> asyncTask = new BaseServiceObjectAsyncTask<>(
                EpisodeListResponse.class,
                url,
                internalListener
        );

        asyncTask.execute();
    }

    //lets gets the episodes from a certain episode
    public void getCharacters(Episode episode, BaseServiceListener<List<EpisodeCharacter>> listener) {

        StringBuilder characterIds = new StringBuilder();
        for (String charURL : episode.characters) {
            String[] parts = charURL.split("/");
            if (parts.length > 0) {
                String charID = parts[parts.length-1];
                if (characterIds.length() > 0) {
                    characterIds.append(",");
                }

                characterIds.append(charID);
            }
        }

        BaseServiceListAsyncTask<EpisodeCharacter> asyncTask = new BaseServiceListAsyncTask<>(
                null,
                EpisodeCharacter.class,
                BASE_URL + "/character/" + characterIds,
                listener
        );

        asyncTask.execute();

    }

    //returns our singleton
    public static RickMortyAPI getSingleton() {
        if (mSingleton == null) {
            mSingleton = new RickMortyAPI();
        }

        return mSingleton;
    }

}