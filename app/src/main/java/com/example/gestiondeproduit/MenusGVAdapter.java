package com.example.gestiondeproduit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.learntodroid.pfescanner.R;

import java.util.ArrayList;

public class MenusGVAdapter extends ArrayAdapter<Menus> {
    public MenusGVAdapter(@NonNull Context context, ArrayList<Menus> menuModelArrayList) {
        super(context, 0, menuModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }
        Menus menuModel = getItem(position);
        TextView menuTV = listitemView.findViewById(R.id.idTV);
        ImageView menuIV = listitemView.findViewById(R.id.idIV);
        menuTV.setText(menuModel.getMenu_name());
        menuIV.setImageResource(menuModel.getImgid());
        return listitemView;
    }
}

