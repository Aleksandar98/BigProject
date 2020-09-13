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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewVH> {

    Context context;
    List<Post> lista;

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
}
class RecyclerViewVH extends RecyclerView.ViewHolder{

    TextView titleTv;
    TextView descTv;
    public RecyclerViewVH(@NonNull View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.titleTv);
        descTv = itemView.findViewById(R.id.descriptionTv);
    }
}
