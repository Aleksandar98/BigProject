package com.aca.bigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.aca.bigproject.model.Comment;
import com.aca.bigproject.viewmodels.CommentActivityViewModel;

import java.util.List;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        int postId = getIntent().getIntExtra("commentId",0);

        CommentActivityViewModel commentActivityViewModel = ViewModelProviders.of(this).get(CommentActivityViewModel.class);

        TextView commentsTv = findViewById(R.id.commentsTv);

        commentActivityViewModel.init();

        commentActivityViewModel.getComments(postId).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                if(comments.size()>0)
                commentsTv.setText(comments.get(0).getBody());
            }
        });
    }
}