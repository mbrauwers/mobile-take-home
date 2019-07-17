package com.guestlogixtest.rickmorty.model.services;

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.BaseServiceAsyncTask;
import com.guestlogixtest.rickmorty.model.base.BaseServiceListener;
import com.guestlogixtest.rickmorty.model.entities.Episode;
import com.guestlogixtest.rickmorty.model.entities.EpisodeListResponse;

import java.util.ArrayList;

public class RickMortyAPI {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    public void getEpisodes(BaseServiceListener<EpisodeListResponse> listener) {

        BaseServiceAsyncTask<EpisodeListResponse> asyncTask = new BaseServiceAsyncTask<>(
                EpisodeListResponse.class,
                BASE_URL + "/episode",
                listener
        );

        asyncTask.execute();

    }

}