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
import com.google.firebase.database.Query;
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

    String product_dateexp = str[1];

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance().getReference().getDatabase();
    DatabaseReference databaseReference = firebaseDatabase.getReference("produit").push();






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

        databaseReference.child("Nom").setValue(product_name);
        databaseReference.child("dateDexpiration").setValue(product_dateexp);

         btn_add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
            //     String result_input = numberProducts.getText().toString().trim();


                 databaseReference.child("quantite").setValue(numberProducts.getText().toString().trim());


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

         DatabaseReference newref  = firebaseDatabase.getReference().child("produit").child("quantite");
        newref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String current_val = dataSnapshot.child("quantite").getValue().toString();
                int val_int = Integer.parseInt(current_val);
                if (val_int <=10){

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(data.this,"myNotification");
                    builder.setContentTitle("attention");
                    builder.setContentText("produit est va etre indisponbile!!");
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat manager = NotificationManagerCompat.from(data.this);
                    manager.notify(1,builder.build());



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });












    }





}
