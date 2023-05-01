package com.shadow.dev.maktebeti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;


public class TabMainActivity extends AppCompatActivity  {

    private ViewPager mViewPager;
    private SectionPageAdapter sectionPageAdapter;
    private DialogClass dialogClass;
    private AdView mAdView1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_main_layout);


        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager= findViewById(R.id.pager);
        setupViewPage(mViewPager);

        TabLayout tabLayout=findViewById(R.id.tablyt);
        tabLayout.setupWithViewPager(mViewPager);
        dialogClass=new DialogClass(this,this);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1 = findViewById(R.id.adViewItem1);
        mAdView1.loadAd(adRequest);

    }

    private void setupViewPage(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"المكتبة");
        adapter.addFragment(new Tab3Fragment(),"طلب كتاب");
        adapter.addFragment(new Tab2Fragment(),"المتفرقات");

        viewPager.setAdapter(adapter);
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

            //throw new RuntimeException("Test Crash"); // Force a crash

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
            moreApplication();
        }


        return super.onOptionsItemSelected(item);
    }

    private void moreApplication(){
        Intent intent = new Intent(TabMainActivity.this, OtherAppsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        dialogClass.exitApplicationDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
