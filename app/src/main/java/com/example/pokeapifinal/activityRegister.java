package com.example.pokeapifinal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activityRegister extends AppCompatActivity {

    Button btnLogin;
    EditText email, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        btnLogin = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(activityRegister.this,"Rellena este campo",Toast.LENGTH_LONG).show();
                }
                firebaseAuth.createUserWithEmailAndPassword(emailUser, passUser)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(activityRegister.this, "User Created",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                } else {
                                    Toast.makeText(activityRegister.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    private void loginUser(String emailUser, String passUser) {

    }
}