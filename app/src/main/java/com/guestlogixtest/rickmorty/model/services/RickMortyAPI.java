package com.guestlogixtest.rickmorty.model.services;

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.BaseServiceAsyncTask;
import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;

import java.util.ArrayList;

public class RickMortyAPI {

    //base API URL
    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    //our singleton data holder
    private static RickMortyAPI mSingleton = null;

    //API call to get the episodes
    public void getEpisodes(BaseServiceListener<EpisodeListResponse> listener) {

        BaseServiceAsyncTask<EpisodeListResponse> asyncTask = new BaseServiceAsyncTask<>(
                EpisodeListResponse.class,
                BASE_URL + "/episode",
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