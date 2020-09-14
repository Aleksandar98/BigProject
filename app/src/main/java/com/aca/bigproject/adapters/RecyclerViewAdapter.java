package com.aca.bigproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aca.bigproject.R;
import com.aca.bigproject.model.Post;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewVH> {

    private Context context;
    private  List<Post> lista;
    private static RecyclerViewVH.OnPostClickListener postClickListener;

    public RecyclerViewAdapter(Context context, List<Post> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public RecyclerViewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_item,parent,false);
        return new RecyclerViewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewVH holder, int position) {
        holder.titleTv.setText(lista.get(position).getTitle());
        holder.descTv.setText(lista.get(position).getBody());


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setList(List<Post> posts) {
        lista = posts;
    }

    public void setPostClickListener(RecyclerViewVH.OnPostClickListener postClickListener) {
        RecyclerViewAdapter.postClickListener = postClickListener;
    }

    public static class RecyclerViewVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        MaterialCardView cardView;
        TextView titleTv;
        TextView descTv;
        public RecyclerViewVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTv = itemView.findViewById(R.id.titleTv);
            descTv = itemView.findViewById(R.id.descriptionTv);
            cardView = itemView.findViewById(R.id.cardView);
        }

        @Override
        public void onClick(View view) {
            postClickListener.onPostClick(getAdapterPosition(),view);
        }

        public interface OnPostClickListener{
            void onPostClick(int position,View v);
        }
    }
}


