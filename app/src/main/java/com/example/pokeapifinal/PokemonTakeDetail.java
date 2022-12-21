package com.example.pokeapifinal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class PokemonTakeDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_take_detail);

        String url = getIntent().getStringExtra("urlPokemon");
        String name = getIntent().getStringExtra("namePokemon");
        String idPoke = getIntent().getStringExtra("idPokemon");

        TextView namePoke = findViewById(R.id.namePokeDetail);
        TextView numPoke = findViewById(R.id.idPokeDetail);
        ImageView imgPoke = findViewById(R.id.imgPokeDetail);

        Glide.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + idPoke + ".png").into(imgPoke);
        namePoke.setText(name);
        numPoke.setText(idPoke);

    }
}