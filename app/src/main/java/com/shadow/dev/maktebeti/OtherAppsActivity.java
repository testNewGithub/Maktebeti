package com.shadow.dev.maktebeti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OtherAppsActivity extends AppCompatActivity {

    private DialogClass dialogClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_apps_layout);

        dialogClass=new DialogClass(this,this);
    }

    // launch Play Store intent
    private void playStoreApps(String app_id) {
        Uri marketUri = Uri.parse("market://details?id=" + app_id);
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        startActivity(marketIntent);
    }

    public void backButton(View view) { onBackPressed();
    this.finish();
    }

    public void elkolfielkolButton(View view) {
        playStoreApps("com.KaaKhabia.deltatechenologie.exoplayerytvideo");
    }

    public void rechargerFacilementAppButton(View view) {
        playStoreApps("com.gtari.deltatechenologie.radio_stream_quran_app");
    }

    public void terominoGameButton(View view) {
        playStoreApps("com.gtari.deltatechenologie.tetromino");
    }


    // Action Bar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id ==R.id.rate_item ){
            dialogClass.dialogLauncher(R.string.rate_title,R.string.rate_message,2);
        }

        if(id==R.id.share_item){
            dialogClass.dialogLauncher(R.string.share_title,R.string.share_message,1);
        }

        if(id ==R.id.privacy_policy_item ){
            dialogClass.privacyPolicyDialog();
        }

        if(id ==R.id.exit_item){
            dialogClass.exitApplicationDialog();
        }

        if (id == R.id.idPlayStoreApps) {
           // moreApplication();
        }

        return super.onOptionsItemSelected(item);
    }


    public void withTemimButton(View view) {

        Uri marketUri = Uri.parse("market://details?id="+"com.shadow.dev.with_temim");
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        startActivity(marketIntent);
    }
}
