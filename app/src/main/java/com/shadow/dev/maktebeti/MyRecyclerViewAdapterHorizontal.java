package com.shadow.dev.maktebeti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class MyRecyclerViewAdapterHorizontal extends RecyclerView.Adapter<MyRecyclerViewAdapterHorizontal.ViewHolder> {

    private List<ItemListBook> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;
    private Activity mActivity;
    private DialogClass dialogClass;

    // data is passed into the constructor
    // data is passed into the constructor
    MyRecyclerViewAdapterHorizontal(Activity activity, Context context, List<ItemListBook> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext=context;
        this.mActivity=activity;

        dialogClass = new DialogClass(context,activity);
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item_books, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemListBook data = mData.get(position);
        holder.myView.setImageDrawable(mContext.getResources().getDrawable(data.imageID));
        holder.mTitleView.setText(data.titleBook);
        holder.mAuthorView.setText(data.authorBook);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView mTitleView;
        TextView mAuthorView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imageView);
            mTitleView = itemView.findViewById(R.id.TitleBook);
            mAuthorView = itemView.findViewById(R.id.AuthorBook);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {mClickListener.onItemClick(view, getAdapterPosition());
                launchNextActitvity(mData.get(getAdapterPosition()).pathBook);
            }

        }
    }

    // convenience method for getting data at click position
    public ItemListBook getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    public static String Book_Path="book_path";
    public static String Is_Pdf_Or_Uri="is_pdf_or_uri";

    private void launchNextActitvity(String pdfBookPath){

        int index=pdfBookPath.indexOf(".com");

        if(!isConnected() && index!=-1){
            dialogClass.connexionDialog();
            return;
        }


        Intent intent = new Intent(mActivity, BookViewerActivity.class);
        //intent.putExtra(VIDEO_POSITION, position);
        Bundle extras=new Bundle();
        extras.putString(Book_Path,pdfBookPath);
        extras.putInt(Is_Pdf_Or_Uri,index);
        intent.putExtras(extras);
        mContext.startActivity(intent);
        //mActivity.finish();

    }



    //Get Connectivity State
    private boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




}