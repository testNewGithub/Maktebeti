package com.shadow.dev.maktebeti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class Tab1Fragment extends Fragment implements MyRecyclerViewAdapterHorizontal.ItemClickListener{



    private LinearLayoutManager horizontalLayoutManager0;
    private LinearLayoutManager horizontalLayoutManager1;
    private LinearLayoutManager horizontalLayoutManager2;
    private LinearLayoutManager horizontalLayoutManager3;
    private LinearLayoutManager horizontalLayoutManager4;
    private LinearLayoutManager horizontalLayoutManager5;

    private MyRecyclerViewAdapterHorizontal adapter0;
    private RecyclerView recyclerView0;
    private MyRecyclerViewAdapterHorizontal adapter1;
    private RecyclerView recyclerView1;
    private MyRecyclerViewAdapterHorizontal adapter2;
    private RecyclerView recyclerView2;
    private MyRecyclerViewAdapterHorizontal adapter3;
    private RecyclerView recyclerView3;
    private MyRecyclerViewAdapterHorizontal adapter4;
    private RecyclerView recyclerView4;
    private MyRecyclerViewAdapterHorizontal adapter5;
    private RecyclerView recyclerView5;

    private static final String Tag="Tab1Fragment";
    private com.google.android.material.floatingactionbutton.FloatingActionButton fabShare;
    private DialogClass dialogClass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MobileAds.initialize(getContext(), getResources().getString(R.string.ad_app_id));

        View view= inflater.inflate(R.layout.tab1_fragment,container,false);
        //textView = view.findViewById(R.id.idConnexiontAlert);


        // data to populate the RecyclerView with
        ArrayList<ItemListBook> booksList = new ArrayList<>();
        booksList.add(new ItemListBook(R.drawable.fikalbi_ontha_ibriya ,"في قلبي أنثى عبريّة","خولة حمدي", "fi_kalbi_ontha_ibriya_compressed.pdf"));
        booksList.add(new ItemListBook(R.drawable.interkherisiyouss ,"أنتيخريستوس","أحمد خالد مصطفى", "interkhrisyouss_compressed.pdf"));
        booksList.add(new ItemListBook(R.drawable.ardhe_zikoula_a,"أرض زيكولا - ج1","عمرو عبد الحميد", "ardh_zikola_a_compressed.pdf"));
        booksList.add(new ItemListBook(R.drawable.ardh_zikola_b , "أرض زيكولا - ج2","عمرو عبد الحميد", "ardh_zikola_b_compressed.pdf"));
        // Todo start
        //booksList.add(new ItemListBook(R.drawable.kawaaed_jaratayan , "قواعد جارتين-ج1","عمرو عبد الحميد", "ardh_zikola_b_compressed.com"));
        //booksList.add(new ItemListBook(R.drawable.kawaaed_jaratayan , "قواعد جارتين-ج2","عمرو عبد الحميد", "ardh_zikola_b_compressed.com"));
        //booksList.add(new ItemListBook(R.drawable.kawaaed_jaratayan , "قواعد جارتين-ج3","عمرو عبد الحميد", "ardh_zikola_b_compressed.com"));
        // Todo end


        ArrayList<ItemListBook> booksList1 = new ArrayList<>();
        booksList1.add(new ItemListBook(R.drawable.elquran_elkarim ,"القرآن الكريم","", "elquaran_elkarim_compressed.pdf"));
        booksList1.add(new ItemListBook(R.drawable.lianaka_allah ,"لانك الله","علي بن جابر الفيفي", "lianaka_allah_compressed.pdf"));
        booksList1.add(new ItemListBook(R.drawable.indama_eltakayna_omar ,"عندما التقيت عمر","أدهم شرقاوي", "omar_ibn_elkhattab_compressed.pdf"));
        booksList1.add(new ItemListBook(R.drawable.fetetni_elsalah ,"فاتتني صلاة","اسلام جمال", "fatetni_elsalah_compressed.pdf"));
        booksList1.add(new ItemListBook(R.drawable.kouni_sahabiya ,"كوني صحابيّة","حنان لاشين", "kouni_sahabiya.pdf"));


        ArrayList<ItemListBook> booksList2 = new ArrayList<>();
        booksList2.add(new ItemListBook(R.drawable.aaser_elaalem ,"عصر العلم","أحمد زويل", "aaser_elaalem_compressed.pdf"));
        booksList2.add(new ItemListBook(R.drawable.mouhandes_ala_tarik_a ,"مهندس على الطريق1","عبد اللّه البرغوثي", "mohandes_alaa_tarik_a_compressed.pdf"));
        booksList2.add(new ItemListBook(R.drawable.mouhandes_ala_tarik_b ,"مهندس على الطريق2","عبد اللّه البرغوثي", "mouhandes_ala_eltarik_b_compressed.pdf"));
        booksList2.add(new ItemListBook(R.drawable.steeve_jobs ,"ستيف جوبز","والتر ايزاكسون", "steve_jobs_compressed.pdf"));

        ArrayList<ItemListBook> booksList3 = new ArrayList<>();
        booksList3.add(new ItemListBook(R.drawable.fan_ella_moubalet ,"فن اللامبالاة","مارك مانسون", "fan_ella_moubalat_compressed.pdf"));
        booksList3.add(new ItemListBook(R.drawable.hayet_bela_tawater ,"حياة بلا توتر","ابراهيم الفقي", "hayat_bela_tawater_compressed.pdf"));
        booksList3.add(new ItemListBook(R.drawable.idaret_elwaket ,"ادارة الوقت","ابراهيم الفقي", "idaret_elwaket_compressed.pdf"));
        booksList3.add(new ItemListBook(R.drawable.seher_elaakel ,"سحر العقل","مارتا هيات", "seher_eltefkir_compressed.pdf"));


        ArrayList<ItemListBook> booksList4 = new ArrayList<>();
        //booksList4.add(new ItemListBook(R.drawable.alinsen_wa_kouaho_elkhafiya ,"الانسان وقواه الخفيّة","كولن ولسن", "alinsen_wo_kiwaho_elkhafiya_compressed.pdf"));
        booksList4.add(new ItemListBook(R.drawable.ayna_elenseen ,"أين الا نسان","طنطاوي جوهري", "ayna_eleensen.pdf"));
        booksList4.add(new ItemListBook(R.drawable.athar_eleleem_fi_elmojtemaa ,"أثر العلم في المجتمع","برتراند راسل", "athar_elelem.pdf"));
       // booksList4.add(new ItemListBook(R.drawable.hakadhe_tekela_zaradachete ,"فلسفة العلم","أليكس روزنبرج", "falsefet_elaalem.pdf"));
        booksList4.add(new ItemListBook(R.drawable.ma_waraa_eltabiaa ,"ماوراء الأوهام","اريش فروم", "ma_waraaa_elwahem.pdf"));
        booksList4.add(new ItemListBook(R.drawable.hakadhe_tekela_zaradachete ,"هكذا تكلم زرادشت","فريدريك نيتشه", "hakadha_takalama_zaradachet_compressed.pdf"));

        ArrayList<ItemListBook> booksList5 = new ArrayList<>();
        booksList5.add(new ItemListBook(R.drawable.nabdhe,"نبض","أدهم شرقاوي", "nabdhe_compressed.pdf"));
        booksList5.add(new ItemListBook(R.drawable.wahey_elkalam ,"وحي القلم","مصطفى صادق الرافعي", "woahye_elkalam_compressed.pdf"));
        booksList5.add(new ItemListBook(R.drawable.eykadoly ,"ايكادولي","حنان لاشين", "ikadoli.pdf"));
        booksList5.add(new ItemListBook(R.drawable.houlme_rajoul_moudhehek ,"حلم رجل مضحك","دوستويفسكي", "holmou_rajoulen_modhehek.pdf"));


        // set up the RecyclerView
        horizontalLayoutManager0
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView0 = view.findViewById(R.id.rvBooksList0);
        recyclerView0.setLayoutManager(horizontalLayoutManager0);
        adapter0 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList);
        adapter0.setClickListener(this);
        recyclerView0.setAdapter(adapter0);

        recyclerView1 = view.findViewById(R.id.rvBooksList1);
        horizontalLayoutManager1
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(horizontalLayoutManager1);
        adapter1 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList1);
        adapter1.setClickListener(this);
        recyclerView1.setAdapter(adapter1);


        recyclerView2 = view.findViewById(R.id.rvBooksList2);
        horizontalLayoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
        adapter2 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList2);
        adapter2.setClickListener(this);
        recyclerView2.setAdapter(adapter2);


        recyclerView3 = view.findViewById(R.id.rvBooksList3);
        horizontalLayoutManager3
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);
        adapter3 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList3);
        adapter3.setClickListener(this);
        recyclerView3.setAdapter(adapter3);


        recyclerView4 = view.findViewById(R.id.rvBooksList4);
        horizontalLayoutManager4
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(horizontalLayoutManager4);
        adapter4 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList4);
        adapter4.setClickListener(this);
        recyclerView4.setAdapter(adapter4);


        recyclerView5 = view.findViewById(R.id.rvBooksList5);
        horizontalLayoutManager5
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView5.setLayoutManager(horizontalLayoutManager5);
        adapter5 = new MyRecyclerViewAdapterHorizontal(getActivity(),getContext(), booksList5);
        adapter5.setClickListener(this);
        recyclerView5.setAdapter(adapter5);

        dialogClass=new DialogClass(getContext(),getActivity());

        fabShare = view.findViewById(R.id.fab_share1);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
                dialogClass.dialogLauncher(R.string.share_title,R.string.share_message,1);
            }
        });



        //Floating Action menu
        FloatingActionButton floatingActionButton1 = view.findViewById(R.id.material_design_floating_action_menu_item1_main1);
        FloatingActionButton floatingActionButton2 = view.findViewById(R.id.material_design_floating_action_menu_item2_main1);


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



        return view;
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}
