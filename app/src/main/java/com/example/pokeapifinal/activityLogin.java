package com.example.pokeapifinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class activityLogin extends AppCompatActivity {

    Button btnLogin;
    EditText email, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(activityLogin.this,"Rellena este campo",Toast.LENGTH_LONG).show();
                }else {
                    loginUser(emailUser,passUser);
                }
            }
        });

    }

    private void loginUser(String emailUser, String passUser) {

    }
}