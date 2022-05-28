package com.example.gestiondeproduit;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondeproduit.camera;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.learntodroid.pfescanner.R;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {
    FirebaseDatabase fbd;
    DatabaseReference dbref;
    ListView list1;
    String name,value;
    ArrayList<String> arraylist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        list1 = (ListView) findViewById(R.id.list1);
        dbref = FirebaseDatabase.getInstance().getReference();
        arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arraylist);
        list1.setAdapter(arrayAdapter);
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 name = snapshot.getKey().toString();
                // value = snapshot.getValue().toString();
                 String ref = snapshot.child("Reference").getValue().toString();
                String da = snapshot.child("Date d'ajout").getValue().toString();
                String dp = snapshot.child("Date d'expiration").getValue().toString();
                String q = snapshot.child("quantité").getValue().toString();
                String data_to_list = "produit:"+name+"\n"+"Date Ajout :"+da+"\n"+"Date perime:"+dp+"\n"+"quantité:"+q+"\n"+"Reference:"+ref;
                arraylist.add(data_to_list);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder build = new AlertDialog.Builder(dashboard.this);

                build.setTitle("les information sur le produit!").setMessage((String)adapterView.getItemAtPosition(i)).setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // we will work here


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();

                    }
                }).show();























            }
        });


    }
}

