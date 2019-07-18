package com.guestlogixtest.rickmorty.characterdetail;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.guestlogixtest.rickmorty.R;

public class CharacterDetailActivity extends AppCompatActivity implements CharacterDetailContract.View {

    private CharacterDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        presenter = new CharacterDetailPresenter();
    }

}