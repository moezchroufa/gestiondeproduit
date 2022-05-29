package com.example.gestiondeproduit;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.learntodroid.pfescanner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;

public class camera extends AppCompatActivity {

    public static String content_result;
    Button btn_scan;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now;
    LocalDate exp_date,conversion_date;
    String message_box;


    public FirebaseDatabase firebaseDatabase; // entry point
    public DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameraactivityx);
        btn_scan =findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v->
        {
            now = LocalDateTime.now();
            scanCode();
        });
    }
    public static String getContent_result(){
        return content_result;
    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(captureActivity.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            content_result = result.getContents();
            String[] str = content_result.split(",");
          //  String product_name = str[0];
          //  String product_ref = str[1];
            String product_dateexp = str[1];
           // firebaseDatabase= FirebaseDatabase.getInstance().getReference().getDatabase();
          //  databaseReference = firebaseDatabase.getReference(product_name);
          //  databaseReference.child("Date exp").setValue(product_dateexp);
           // databaseReference.child("quantité").setValue(0);
           // databaseReference.child("Ref").setValue(product_ref);
          //  exp_date = LocalDate.parse(product_dateexp);


            AlertDialog.Builder builder = new AlertDialog.Builder(camera.this);
            builder.setTitle("un produit est detecter!");

            builder.setPositiveButton("ajouter", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Intent sens = new Intent(camera.this, data.class);
                    startActivity(sens);


                } // end of the onclick function
            }).setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();

                }
            }).show(); // end of add button







        } // end of if statement
    }); // end of the scan option



 } // end of the class
