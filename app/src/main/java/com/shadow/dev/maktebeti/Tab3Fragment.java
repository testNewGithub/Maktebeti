package com.shadow.dev.maktebeti;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab3Fragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab3_fragment,container,false);
        Button moreBooksButton = view.findViewById(R.id.idMoreBooks);


          moreBooksButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
               //Todo
                  Uri marketUri = Uri.parse("market://details?id="+"com.shadow.dev.maktebeti");
                  Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                  startActivity(marketIntent);
              }
          });


        return view;
    }

}
