package com.guestlogixtest.rickmorty.characterdetail;

import android.widget.ImageView;

import com.guestlogixtest.rickmorty.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public interface CharacterDetailContract {

    interface View {
        void onCharacterLoaded(Map<String, String> values, Boolean isAlive);
    }

    interface Presenter extends BasePresenter {
        void loadCharacter();
        void loadCharacterImage(ImageView imageView);
        void killCharacter();
    }

}