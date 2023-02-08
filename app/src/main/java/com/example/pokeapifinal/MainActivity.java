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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRegister();
            }
        });

        //Floating button y su listener
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void openActivityLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }

    public void openActivityRegister(){
        Intent intent = new Intent(this,activityRegister.class);
        startActivity(intent);
    }
}