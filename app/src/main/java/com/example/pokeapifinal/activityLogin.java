package com.example.pokeapifinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class activityLogin extends AppCompatActivity {

    Button btnLogin;
    EditText email, password;
    FirebaseAuth firebaseAuth;
    TextView textViewCreateAccount;
    ImageView imageView;
    int numColor = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        btnLogin = findViewById(R.id.buttonLogin);
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount);

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });

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
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorChange();
            }
        });

    }

    public void openActivityRegister(){
        Intent intent = new Intent(this, activityRegister.class);
        startActivity(intent);
    }

    private void loginUser(String emailUser, String passUser) {

    }
    private void colorChange(){
        if (numColor ==6){
            numColor = 1;
        }
        switch (numColor){
            case 1:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_200));
                break;
            case 2:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_500));
                break;
            case 3:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.purple_700));
                break;
            case 4:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.teal_200));
                break;
            case 5:
                imageView.setColorFilter(activityLogin.this.getResources().getColor(R.color.teal_700));
                break;
        }
        numColor++;
    }

}