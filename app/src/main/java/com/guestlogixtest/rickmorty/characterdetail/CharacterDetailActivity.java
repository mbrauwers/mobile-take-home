package com.guestlogixtest.rickmorty.characterdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogixtest.rickmorty.R;

import java.util.Map;

public class CharacterDetailActivity extends AppCompatActivity implements CharacterDetailContract.View {

    private CharacterDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        configUI();

        presenter = new CharacterDetailPresenter(this);
        presenter.loadCharacter();
        presenter.loadCharacterImage((ImageView) findViewById(R.id.characterImgView));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    void configUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.characterDetailRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button = (Button) findViewById(R.id.kill_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.killCharacter();
            }
        });
    }

    @Override
    public void onCharacterLoaded(Map<String, String> values, Boolean isAlive) {
        Log.d("msg", "CharacterDetailActivity::onCharacterLoaded " + values.size());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.characterDetailRecView);
        CharacterDetailAdapter adapter = new CharacterDetailAdapter(values);
        recyclerView.setAdapter(adapter);

        if (isAlive) {
            findViewById(R.id.kill_button).setVisibility(View.VISIBLE);
            findViewById(R.id.killed_lbl).setVisibility(View.GONE);
        }
        else {
            findViewById(R.id.kill_button).setVisibility(View.INVISIBLE);
            findViewById(R.id.killed_lbl).setVisibility(View.VISIBLE);
        }
    }

}