package com.example.gestiondeproduit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.learntodroid.pfescanner.R;

public class data extends AppCompatActivity {
    private static final String TAG ="" ;
    EditText numberProducts;
    Button btn_add;

    camera init_camera;
    public String text_from_qrcode = init_camera.getContent_result();

    String[] str = text_from_qrcode.split(",");
    String product_name = str[0];
    String product_ref = str[1];
    String product_dateexp = str[2];






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_frame);
        numberProducts = (EditText) findViewById(R.id.btnNumber);
        btn_add =(Button) findViewById(R.id.button_ok);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance().getReference().getDatabase();
        DatabaseReference databaseReference = firebaseDatabase.getReference(product_name);
        databaseReference.child("Date exp").setValue(product_dateexp);
         databaseReference.child("Ref").setValue(product_ref);

         btn_add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 databaseReference.child("quantité").setValue(numberProducts.getText().toString());
                 Log.w(TAG, "product added :", null);
                 Toast.makeText(data.this, "product added",
                         Toast.LENGTH_SHORT).show();
                 Intent sens = new Intent(data.this, camera.class);
                 startActivity(sens);
             }
         });

    }
}
