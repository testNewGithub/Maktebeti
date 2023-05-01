package com.shadow.dev.maktebeti;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.shockwave.pdfium.PdfDocument;
import java.util.List;



public class BookViewerActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnTapListener, OnRenderListener {


    private Context mContext=this;
    public static String SAMPLE_FILE ;
    PDFView pdfView;
    Integer pageNumber;
    String pdfFileName;
    private Button markerButton;
    private Button fullScreeenBottn;
    private Integer index;

    private boolean isShowWidgets=true;
    private FloatingActionMenu floatingActionMenu;
    private com.google.android.material.floatingactionbutton.FloatingActionButton fabShare;
    private DialogClass dialogClass;

    private InterstitialAd mInterstitialAd;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bookviewer);
        addFloatingActionMenu();


        MobileAds.initialize(this,
                getResources().getString(R.string.ad_app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.ad_interstitial_item));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });


        floatingActionMenu=findViewById(R.id.material_design_android_floating_action_menu_main);

        //Intent intent=getIntent();
        //actuelPosition=intent.getIntExtra(Tab1Fragment.VIDEO_POSITION,0);
        Bundle extras =getIntent().getExtras();
        assert extras != null;
        SAMPLE_FILE = extras.getString(MyRecyclerViewAdapterHorizontal.Book_Path);
        index = extras.getInt(MyRecyclerViewAdapterHorizontal.Is_Pdf_Or_Uri);

        pageNumber=loadSavedPage(SAMPLE_FILE);
        pdfView= (PDFView)findViewById(R.id.pdfView);
        markerButton=(Button)findViewById(R.id.idMarkerButton);
        fullScreeenBottn=(Button)findViewById(R.id.idFullScreenButton);

        dialogClass=new DialogClass(this,this);

        displayFromAsset(SAMPLE_FILE,pageNumber);

        fabShare = findViewById(R.id.fab_share);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //Todo
                dialogClass.dialogLauncher(R.string.share_book_title,R.string.share_book_message,1);
            }
        });

    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Toast.makeText(this,"الصفحة الحالية: "+ (pageNumber+1), Toast.LENGTH_LONG).show();
    }



    private void showWidgets(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        markerButton.setVisibility(View.VISIBLE);
        fullScreeenBottn.setVisibility(View.VISIBLE);
        floatingActionMenu.showMenu(true);
        fabShare.show();
    }


    private void hideWidgets(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        markerButton.setVisibility(View.INVISIBLE);
        fullScreeenBottn.setVisibility(View.INVISIBLE);
        floatingActionMenu.hideMenu(true);
        fabShare.hide();
    }

    private void displayFromAsset(String assetFileName,int pageNumber) {
        pdfFileName = assetFileName;
        pdfView.useBestQuality(true);

        if(index!=-1){
            //Todo
            pdfView.fromUri(Uri.parse(SAMPLE_FILE))
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onTap(this)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new SecondScrolHandle(this))
                    .fitEachPage(true)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .spacing(10)
                    .load();

        }else {
            //TOdo
            pdfView.fromAsset(SAMPLE_FILE)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onTap(this)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new SecondScrolHandle(this))
                    .fitEachPage(true)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .spacing(10)
                    .load();
        }



    }



    private boolean isDialogLaunched=false;
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        float percent=  (float)(page+1)/(float)pageCount *100;

        //Toast.makeText(this,String.valueOf((float)page/(float)pageCount),Toast.LENGTH_LONG).show();

        if(percent>=95.0f && !isDialogLaunched){
            dialogClass.dialogLauncher(R.string._share_book_title,R.string._share_book_msg,1);
            isDialogLaunched=true;
        }

        //setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }




    //Save Favorite video in database
    private void saveActualPage(String bookName, int actualPage) {
        SharedPreferences.Editor editor = getSharedPreferences("Books", MODE_PRIVATE).edit();
        editor.putInt(bookName, actualPage);
        editor.apply();
    }

    private int loadSavedPage(String bookName){

        SharedPreferences perfers=getSharedPreferences("Books", Activity.MODE_PRIVATE);
        if(!perfers.contains(bookName)) {
            return 0;
        }

        int state=perfers.getInt(bookName,0);
        return state;
    }



    @Override
    public boolean onTap(MotionEvent e) {

        if(isShowWidgets){
            hideWidgets();
            isShowWidgets=false;
        }else{
            showWidgets();
            isShowWidgets=true;
        }

        return false;
    }

    public void buSavePage(View view) {
        saveActualPage(SAMPLE_FILE,pageNumber);
        //Toast.makeText(this,"تمّ حفظ الصفحة",Toast.LENGTH_LONG).show();

        RelativeLayout relativeLayout=findViewById(R.id.LayoutID);
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "تمّ حفظ الصفحة", Snackbar.LENGTH_LONG);
        View view1=snackbar.getView();
        view1.setBackgroundColor(Color.parseColor("#BB673AB7"));
        TextView tv=(TextView) view1.findViewById(com.google.android.material.R.id.snackbar_text);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));//46E74D"));
        snackbar.show();

    }

    public void buFullScreenView(View view) {
        if(isShowWidgets){
            hideWidgets();
            isShowWidgets=false;
        }
    }



    @Override
    public void onInitiallyRendered(int nbPages) {
        pdfView.fitToWidth(nbPages);
    }



    private void addFloatingActionMenu(){
        //Floating Action menu
        FloatingActionButton floatingActionButton1 = findViewById(R.id.material_design_floating_action_menu_item1_main);
        FloatingActionButton floatingActionButton2 = findViewById(R.id.material_design_floating_action_menu_item2_main);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogClass.dialogLauncher(R.string.rate_title,R.string.rate_message,2);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogClass.exitApplicationDialog();
            }
        });

    }


    @Override
    public void finish() {
        super.finish();
    }


    @Override
    protected void onResume() {

        if(!mInterstitialAd.isLoaded())
           mInterstitialAd.loadAd(new AdRequest.Builder().build());

        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onBackPressed() {
    // ToDO launch Interstitial AD

        if(!mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        },300); // Millisecond 1000 = 1 sec

        super.onBackPressed();
    }


}
