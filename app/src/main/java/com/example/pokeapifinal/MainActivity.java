package com.example.pokeapifinal;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;
//import com.example.pokeapifinal.FMCSend;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText title, body;
    private TextView nombrea;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user;




    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                } else {
                }
            });

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.button2);
        title = findViewById(R.id.editTitle);
        body = findViewById(R.id.editBody);
        sync();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogin();
            }
        });

        //Floating button y su listener
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FMCSend.pushNotification(
                        MainActivity.this,
                        "dP5Z7gUiRKqQz7ihYwJVY6:APA91bHgmZs43mfSZ5MLnQ_6cIhria-89R-SNHxMXO6gCIIA_mXqnelguks-nJ4CtD5BY3KB6DXZTkDWlvCx8mfBtRkmN2SvurynRTLRJQMDEHtCWk9-f8gB-MqNDUxuPoA7q9-Jc3VT",
                        "Notificacion",
                        "Se te ha enviado una notificacion"
                );
                push();
                sync();
            }

        });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.printf("Token no encontrado");
                            return;
                        }
                        String token = task.getResult();
                        System.out.println("TOKEN: " + token);
                    }
                });
    }

    public void openActivityLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }

    public void push(){
        Map<String,Object> values = new HashMap<>();
        values.put("Body",body.getText().toString());
        values.put("Title",title.getText().toString());
        db.collection("Documento").document("hZwiJlQSeRmskDnB7baC").update(values).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "Update complited", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Toast.makeText(MainActivity.this, "Error to send updates", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void sync(){
        db.collection("Documento").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()){
                    title.setText(document.getData().get("Title").toString());
                    body.setText(document.getData().get("Body").toString());
                }
            }
        });
    }
}
