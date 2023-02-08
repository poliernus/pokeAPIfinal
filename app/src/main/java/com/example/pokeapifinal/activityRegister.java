package com.example.pokeapifinal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView imageView;
    int numColor = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        btnLogin = findViewById(R.id.buttonRegister);
        imageView = findViewById(R.id.imageView);
        
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorChange();
            }
        });


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
    private void colorChange(){
        if (numColor ==6){
            numColor = 1;
        }
        switch (numColor){
            case 1:
                imageView.setColorFilter(activityRegister.this.getResources().getColor(R.color.purple_200));
                break;
            case 2:
                imageView.setColorFilter(activityRegister.this.getResources().getColor(R.color.purple_500));
                break;
            case 3:
                imageView.setColorFilter(activityRegister.this.getResources().getColor(R.color.purple_700));
                break;
            case 4:
                imageView.setColorFilter(activityRegister.this.getResources().getColor(R.color.teal_200));
                break;
            case 5:
                imageView.setColorFilter(activityRegister.this.getResources().getColor(R.color.teal_700));
                break;
        }
        numColor++;
    }

    private void loginUser(String emailUser, String passUser) {

    }
}