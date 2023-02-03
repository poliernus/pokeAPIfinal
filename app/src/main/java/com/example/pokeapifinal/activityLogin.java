package com.example.pokeapifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activityLogin extends AppCompatActivity {

    String name;
    String pasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        TextView createAccount = findViewById(R.id.textViewCreateAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openActivityRegister();}
        });

        Button buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextName = findViewById(R.id.editTextRegisterName);
                name =  String.valueOf(editTextName.getText());
                EditText editTextPassword = findViewById(R.id.editTextRegisterPassword);
                pasword =  String.valueOf(editTextPassword.getText());

                if (confirmLogin(name, pasword)){
                    Toast.makeText(activityLogin.this, "Welcome "+name, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(activityLogin.this, "The name or password are wrong!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageView imageViewArrow = findViewById(R.id.imageViewArrow2);
        imageViewArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityMain();
            }
        });
    }

    public void openActivityMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void openActivityRegister(){
        Intent intent = new Intent(this, activityRegister.class);
        startActivity(intent);
    }


    public Boolean confirmLogin(String name, String password){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activityLogin.this);

        String storedName = preferences.getString("my_name",null);
        String storedPasword = preferences.getString("my_password",null);

        if(name.equals(storedName)&& password.equals(storedPasword)){
            return true;
        }else{
            return false;
        }
    }
}