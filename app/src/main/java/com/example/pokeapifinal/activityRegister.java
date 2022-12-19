package com.example.pokeapifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activityRegister extends AppCompatActivity {

    private static final String FILE_NAME = "users.txt";

    private String name;
    private String password;
    private String passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activityRegister.this);


        ImageView arrowBack = findViewById(R.id.imageBackArrowRegister);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openActivityLogin();}
        });

        Button buttonCreate = findViewById(R.id.buttonSignIn);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText textName = findViewById(R.id.editTextRegisterName);
                name = String.valueOf(textName.getText());
                EditText textViewPassword = findViewById(R.id.editTextRegisterPassword);
                password = String.valueOf(textViewPassword.getText());
                EditText textPasswordConfirm = findViewById(R.id.editTextRegisterPasswordConfirm);
                passwordConfirm = String.valueOf(textPasswordConfirm.getText());

                if(!checkConfirmPassword(password,passwordConfirm)){
                    Toast.makeText(activityRegister.this,"The two passwords do not match!!", Toast.LENGTH_LONG).show();
                }else{
                    createAccount(name, password,preferences);
                }
            }
        });

        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openActivityLogin();}
        });


        ImageView menu = findViewById(R.id.imageMenuRegister);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    public void createAccount(String name, String password, SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("my_name", name);
        editor.apply();
        editor.putString("my_password", password);
        editor.apply();
        Toast.makeText(activityRegister.this,"Account created!", Toast.LENGTH_LONG).show();


    }


    public boolean checkConfirmPassword(String password, String passwordConfirm){
        if (password.equals(passwordConfirm)){
            return true;
        }else{
            return false;
        }
    }

    public void openActivityLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }
}