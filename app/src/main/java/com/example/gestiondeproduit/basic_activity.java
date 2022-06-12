package com.example.gestiondeproduit;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.learntodroid.pfescanner.R;

import java.util.ArrayList;
public class basic_activity extends AppCompatActivity {

    GridView menusGV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case   R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                Intent leave = new Intent(basic_activity.this, activity_login.class);
                leave.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(leave);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_activity);

        menusGV = findViewById(R.id.idGV);

        ArrayList<Menus> MenusModelArrayList = new ArrayList<Menus>();
        MenusModelArrayList.add(new Menus("tableau de bord",R.drawable.first_image));
        MenusModelArrayList.add(new Menus("camera",R.drawable.first_camera));
        MenusModelArrayList.add(new Menus("configuration capteur",R.drawable.first_setting));


        MenusGVAdapter adapter = new MenusGVAdapter(this, MenusModelArrayList);
        menusGV.setAdapter(adapter);
        menusGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                if (position == 0) {
                    Intent def = new Intent(basic_activity.this, dashboard.class);
                    startActivity(def);
                } else if (position == 1) {
                    Intent abc = new Intent(basic_activity.this, camera.class);
                    startActivity(abc);
                }else if (position == 2){
                    Intent sens = new Intent(basic_activity.this, sensors.class);
                    startActivity(sens);
                }
            }
        });







    }
}


