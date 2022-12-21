package com.example.pokeapifinal.api;

import com.example.pokeapifinal.models.PokemonRespuesta;
import com.example.pokeapifinal.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokedexApi
{
     @GET("pokemon")
     Call<PokemonRespuesta> obtenerListaPokemon(@Query("offset") int offset, @Query("limit") int limit);
}
