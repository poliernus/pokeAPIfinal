package com.example.pokeapifinal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeapifinal.models.Pokemon;
import com.example.pokeapifinal.api.PokemonClickListener;


import java.util.ArrayList;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {

    private ArrayList<Pokemon> data;
    private Context context;

    public PokedexAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pokemon_in_pokedex, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = data.get(position);
        holder.txtPoke.setText(pokemon.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .into(holder.imgPoke);

        holder.pokemonClickListener(new PokemonClickListener() {
            @Override
            public void onClick(View view, int id) {
                Toast.makeText(context,data.get(position).getName(),Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addListPokemon(ArrayList<Pokemon> listaPokemon) {
        data.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgPoke;
        private TextView txtPoke;

        PokemonClickListener pokemonClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoke = (ImageView) itemView.findViewById(R.id.imagenPokemon);
            txtPoke = (TextView) itemView.findViewById(R.id.nombrePokemon);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            pokemonClickListener.onClick(view,getAdapterPosition());
        }

        public void pokemonClickListener(PokemonClickListener pokemonClickListener) {
            this.pokemonClickListener = pokemonClickListener;
        }
    }
}

