package com.shadow.dev.maktebeti;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class DialogClass implements RewardedVideoAdListener {
    private Context mContext;
    private Activity mActivity;
    private RewardedVideoAd mRewardedVideoAd;



    DialogClass(final Context context, Activity activity){
        this.mContext=context;
        this.mActivity=activity;


        MobileAds.initialize(context, context.getResources().getString(R.string.ad_app_id));

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


    }


    // Load video
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(mContext.getResources().getString(R.string.ad_video_item),
                new AdRequest.Builder().build());
    }



    // Launch Privacy Policy
    public void privacyPolicyDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle(mContext.getResources().getString(R.string.privacy_policy));

        WebView wv = new WebView(mContext);
        wv.loadUrl("file:///android_asset/privacy_policy.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton(mActivity.getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    // Connexion State Alert
    public void connexionDialog() {

        final Dialog dialog1 = new Dialog(mContext);
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog1.setContentView(R.layout.internet_state_dialog);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.setCancelable(false);
        dialog1.show();

        dialog1.findViewById(R.id.idConnectionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
            }

        });
    }



    // exit applaiction
    public void exitApplicationDialog(){
        final Dialog dialog = new Dialog(mContext);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.cancel_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

        if (!mRewardedVideoAd.isLoaded()) {
            loadRewardedVideoAd();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        },300); // Millisecond 1000 = 1 sec


        //Dialog Alert
        dialog.findViewById(R.id.positive_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

        dialog.findViewById(R.id.cancel_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                mActivity.finish();
                //System.exit(0);
                mActivity.moveTaskToBack(true);
            }

        });

        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

    }


    // Share and Rate Application Dialog
    public void dialogLauncher(int idTitle, final int idMessage, final int idTaskToDo ){

        final Dialog dialog = new Dialog(mContext);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        final TextView messageDialog=(TextView)dialog.findViewById(R.id.message);
        final TextView titleDialog=(TextView)dialog.findViewById(R.id.title);
        titleDialog.setText(idTitle);
        messageDialog.setText(idMessage);
        dialog.show();
        //RunAnimation(R.id.idShinePermission,R.anim.internet_button_anim);

        Animation anim = AnimationUtils.loadAnimation(mContext,R.anim.dialog_anim);
        final ImageView shine =(ImageView)dialog.findViewById(R.id.idShinePermission);
        shine.startAnimation(anim);

        // Allow
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // select tache
                switch (idTaskToDo){
                    case 1 : shareApplicaion();
                        break;
                    case 2 : rateApplication();
                        break;
                    default: shareApplicaion();
                }
                dialog.cancel();
                shine.clearAnimation();
            }
        });

        // Cancel
        dialog.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                shine.clearAnimation();
            }

        });

    }

    // Share Application method id = 1
    private void shareApplicaion(){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, "مكتبتي - مجموعة العقل زينة, مجموعة من الكتب القيّمة و المفيدة. طاقة للعقل و الفكر, لا تتردد في نجربة التطبيق مجاننا على بلاي ستور. خدماتنا مجانيّة لا تتردد في اقتناص الفرصة."+"https://play.google.com/store/apps/details?id=com.shadow.dev.maktebeti");
        mActivity.startActivity(Intent.createChooser(shareIntent, mContext.getString((R.string.share_with))));
    }

    // Rate Application method id = 2
    private void rateApplication(){
        Uri marketUri = Uri.parse("market://details?id="+"com.shadow.dev.maktebeti");
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        mActivity.startActivity(marketIntent);
    }


    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {
        loadRewardedVideoAd();
    }
}
