package com.example.gestiondeproduit;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.learntodroid.pfescanner.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class sensors extends AppCompatActivity {
    TextView vdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_activity);
         vdate = (TextView) findViewById(R.id.current_date);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());


        // Now we display formattedDate value in TextView

        vdate.setText("TIME NOW:"+formattedDate);
        vdate.setGravity(Gravity.CENTER);
        vdate.setTextSize(20);

      //  setContentView(vdate);

    }
}
