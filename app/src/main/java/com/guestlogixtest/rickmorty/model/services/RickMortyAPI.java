package com.guestlogixtest.rickmorty.model.services;

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.BaseServiceAsyncTask;
import com.guestlogixtest.rickmorty.model.base.BaseServiceListAsyncTask;
import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.base.BaseServiceObjectAsyncTask;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RickMortyAPI {

    //base API URL
    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    //our singleton data holder
    private static RickMortyAPI mSingleton = null;

    //API call to get the episodes
    public void getEpisodes(BaseServiceListener<EpisodeListResponse> listener) {

        BaseServiceObjectAsyncTask<EpisodeListResponse> asyncTask = new BaseServiceObjectAsyncTask<>(
                EpisodeListResponse.class,
                BASE_URL + "/episode",
                listener
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

        Log.d("msg", "All character ids are "+ characterIds);

        Log.d("msg", "url is " + BASE_URL + "/character/" + characterIds);

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