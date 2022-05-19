package com.example.gestiondeproduit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import com.learntodroid.pfescanner.R;

import java.util.ArrayList;
public class basic_activity extends AppCompatActivity {

    GridView menusGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_activity);

        menusGV = findViewById(R.id.idGV);

        ArrayList<Menus> MenusModelArrayList = new ArrayList<Menus>();
        MenusModelArrayList.add(new Menus("DASHBOARD"));
        MenusModelArrayList.add(new Menus("CAMERA"));
        MenusModelArrayList.add(new Menus("CONTROL SENSORS"));


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


