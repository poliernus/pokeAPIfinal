package com.example.pokeapifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.buttonSignIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityPokedex();
            }
        });
        ImageView pokeball = findViewById(R.id.imageViewPokeball);
        pokeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToRandomPokemon(idRandomPokemon());
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigatiojn_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signIn:
                openActivityLogin();
                return true;
            case R.id.register:
                Intent intent = new Intent(this, activityRegister.class);
                startActivity(intent);
                return true;
            case R.id.logOut:
                Toast.makeText(this,"You have log out!",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void openActivityPokedex(){
        Intent intent = new Intent(this, PokedexActivity.class);
        startActivity(intent);
    }

    public void openActivityLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }
    public int idRandomPokemon(){
        int min = 1;
        int max = 1154;

        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(random_int);
        return random_int;
    }
    public void changeToRandomPokemon(int num){
        ImageView pokeball = findViewById(R.id.imageViewPokeball);

        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+num+".png")
                .error(getImage("pokeball"))
                .into(pokeball);

    }

    public int getImage(String imageName) {

        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        return drawableResourceId;

    }
}