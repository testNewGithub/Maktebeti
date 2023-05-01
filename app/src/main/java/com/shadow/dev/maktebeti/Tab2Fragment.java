package com.shadow.dev.maktebeti;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab2Fragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab2_fragment,container,false);

        Button btWithTemimApp = view.findViewById(R.id.idWithTemimApp);


          btWithTemimApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri marketUri = Uri.parse("market://details?id="+"com.shadow.dev.with_temim");
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }
           });



        return view;
    }



}
