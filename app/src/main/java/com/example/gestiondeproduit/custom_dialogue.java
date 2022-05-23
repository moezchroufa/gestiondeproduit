package com.example.gestiondeproduit;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.learntodroid.pfescanner.R;

public class custom_dialogue extends Dialog {

    interface NumberListener {
        public void NumberEntered(String number);
    }
    public Context context;

    private EditText editTextFullName;
    private Button buttonOK;
    private Button buttonCancel;

    private custom_dialogue.NumberListener listener;

    public custom_dialogue(Context context, custom_dialogue.NumberListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_frame);

        this.editTextFullName = (EditText) findViewById(R.id.editText_fullName);
        this.buttonOK = (Button) findViewById(R.id.button_ok);
        this.buttonCancel  = (Button) findViewById(R.id.button_cancel);

        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        String number = this.editTextFullName.getText().toString();

/*
        if(number== null || number.isEmpty())  {
            Toast.makeText(this.context, "Please enter Number of products", Toast.LENGTH_LONG).show();
            return;
        }
        this.dismiss(); // Close Dialog*/

        if(this.listener!= null)  {
            this.listener.NumberEntered(number);
        }
    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }







}

