package com.guestlogixtest.rickmorty.characterdetail;

import android.widget.ImageView;

import com.guestlogixtest.rickmorty.model.base.ImageDownloadAsyncTask;
import com.guestlogixtest.rickmorty.model.entities.EpisodeCharacter;
import com.guestlogixtest.rickmorty.model.repository.RickMortyRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharacterDetailPresenter implements CharacterDetailContract.Presenter {

    private CharacterDetailContract.View view;

    CharacterDetailPresenter(CharacterDetailContract.View view) {
        this.view = view;
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void loadCharacter() {
        EpisodeCharacter character = RickMortyRepository.getSingleton().getCurrentCharacter();
        if (character != null && view != null) {
            Map<String, String> values = new LinkedHashMap<>();
            values.put("Name", character.name);
            values.put("Species", character.species);
            values.put("Gender", character.gender);
            values.put("Status", character.status);
            values.put("Origin", character.origin.name);
            values.put("Current Location", character.location.name);
            view.onCharacterLoaded(values, character.status.equals("Alive"));
        }
    }

    //loads character image
    @Override
    public void loadCharacterImage(ImageView imageView) {
        EpisodeCharacter character = RickMortyRepository.getSingleton().getCurrentCharacter();
        ImageDownloadAsyncTask asyncTask = new ImageDownloadAsyncTask(character.image);
        asyncTask.execute(imageView);
    }

    @Override
    //kills the character
    public void killCharacter() {
        EpisodeCharacter character = RickMortyRepository.getSingleton().getCurrentCharacter();
        if (character != null) {
            character.status = "Dead";
        }

        //just to update the UI
        loadCharacter();
    }

}
