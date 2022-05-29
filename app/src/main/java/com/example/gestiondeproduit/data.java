package com.example.gestiondeproduit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learntodroid.pfescanner.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class data extends AppCompatActivity {
    private static final String TAG ="" ;
    EditText numberProducts;
    Button btn_add;


   // camera init_camera = new camera();
    public String text_from_qrcode = camera.getContent_result();

    String[] str = text_from_qrcode.split(",");
    String product_name = str[0];
    String product_ref = str[1];
    String product_dateexp = str[2];

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance().getReference().getDatabase();
    DatabaseReference databaseReference = firebaseDatabase.getReference("produit");






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_frame);
        // pour les notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel ch = new NotificationChannel("myNotification","myNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(ch);
        }
        numberProducts = (EditText) findViewById(R.id.btnNumber);
        btn_add =(Button) findViewById(R.id.button_ok);
        // check product storage
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("quantité").exists()){
                    if (snapshot.child("quantié").getValue().toString() == "1"){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(data.this,"myNotification");
                        builder.setContentTitle("Alert");
                        builder.setContentText("produit"+snapshot.getKey()+" low storage!");
                        builder.setSmallIcon(R.drawable.ic_launcher_background);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat manager = NotificationManagerCompat.from(data.this);
                        manager.notify(1,builder.build());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         btn_add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String result_input = numberProducts.getText().toString();
                 if (result_input.equalsIgnoreCase("")){
                     result_input = "1";
                 }
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
                 String currentDateandTime = sdf.format(new Date());

                 databaseReference.child("Nom produit:").setValue(product_name);
                 databaseReference.child("Date d'expiration").setValue(product_dateexp);
                 databaseReference.child("Reference").setValue(product_ref);
                 databaseReference.child("quantité").setValue(result_input);
                 databaseReference.child("Date d'ajout").setValue(currentDateandTime);
                 Log.w(TAG, "product added :", null);

                 NotificationCompat.Builder builder = new NotificationCompat.Builder(data.this,"myNotification");
                 builder.setContentTitle("new");
                 builder.setContentText("produit est ajouter!!");
                 builder.setSmallIcon(R.drawable.ic_launcher_background);
                 builder.setAutoCancel(true);

                 NotificationManagerCompat manager = NotificationManagerCompat.from(data.this);
                 manager.notify(1,builder.build());
                 Intent sens = new Intent(data.this, camera.class);
                 startActivity(sens);
             }
         });



    }





}
