package com.aca.bigproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aca.bigproject.R;
import com.aca.bigproject.model.Comment;

import java.util.List;

public class CommentRecyAdapter extends RecyclerView.Adapter<CommentRecyAdapter.ViewHolder> {

    private List<Comment> commentList;
    private Context context;

    public CommentRecyAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_recy_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.body.setText(commentList.get(position).getBody());
        holder.name.setText(commentList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void setList(List<Comment> comments) {
        commentList = comments;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.commentName);
            body = itemView.findViewById(R.id.commentBody);
        }
    }
}
