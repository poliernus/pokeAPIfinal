package com.example.pokeapifinal;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapifinal.api.PokedexApi;
import com.example.pokeapifinal.models.Pokemon;
import com.example.pokeapifinal.models.PokemonRespuesta;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class PokedexActivity extends AppCompatActivity
{

        private Retrofit retrofit;
        private RecyclerView recyclerView;
        private PokedexAdapter pokedexAdapter;
        private int offset;
        private boolean cargar;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();

            setContentView(R.layout.pokedex_activity);
            recyclerView = findViewById(R.id.recyclerView);
            pokedexAdapter = new PokedexAdapter(this);
            recyclerView.setAdapter(pokedexAdapter);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
            {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
                {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy > 0)
                    {
                        int visibleCount = gridLayoutManager.getItemCount();
                        int totalCount = gridLayoutManager.getItemCount();
                        int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                        if (cargar)
                        {
                            if ((visibleCount + pastVisibleItems) >= totalCount)
                            {
                                cargar = false;
                                offset += 20;
                                getData(offset);
                            }
                        }
                    }
                }
            });

            retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
            cargar = true;
            offset = 0;
            getData(offset);
        }

        private void getData(int offset)
        {
            PokedexApi service = retrofit.create(PokedexApi.class);
            Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(offset, 20);

            pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>()
            {
                @Override
                public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response)
                {
                    cargar = true;
                    if (response.isSuccessful())
                    {
                        PokemonRespuesta pokemonRespuesta = response.body();
                        ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                        pokedexAdapter.addListPokemon(listaPokemon);
                    }
                }

                @Override
                public void onFailure(Call<PokemonRespuesta> call, Throwable t)
                {
                    cargar = true;
                }
            });
        }



}