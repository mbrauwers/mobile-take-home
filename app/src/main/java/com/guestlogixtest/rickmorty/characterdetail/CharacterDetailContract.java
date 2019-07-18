package com.guestlogixtest.rickmorty.characterdetail;

import com.guestlogixtest.rickmorty.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public interface CharacterDetailContract {

    interface View {
        void onCharacterLoaded(Map<String, String> values);
    }

    interface Presenter extends BasePresenter {
        void loadCharacter();
    }

}