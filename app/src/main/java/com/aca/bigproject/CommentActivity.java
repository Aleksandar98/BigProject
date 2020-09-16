package com.aca.bigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aca.bigproject.adapters.CommentRecyAdapter;
import com.aca.bigproject.model.Comment;
import com.aca.bigproject.viewmodels.CommentActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    RecyclerView commentRecy;
    CommentRecyAdapter adapter;
    CommentActivityViewModel commentActivityViewModel;
    int postId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);



        postId = getIntent().getIntExtra("commentId",0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        Toolbar toolbar = findViewById(R.id.toolbarComment);

        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_baseline_arrow_back_24));
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_baseline_share_24));


         commentActivityViewModel = ViewModelProviders.of(this).get(CommentActivityViewModel.class);

        TextView descInComment = findViewById(R.id.descInComment);
        TextView titleInCommetn = findViewById(R.id.titleInComment);
        ImageButton zoom_button = findViewById(R.id.zoom_button);
        LinearLayout titleHolder = findViewById(R.id.titleHolder);

        zoom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleHolder.getVisibility() == View.VISIBLE) {
                    titleHolder.setVisibility(View.GONE);
                    zoom_button.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_drop));
                }else{
                    titleHolder.setVisibility(View.VISIBLE);
                    zoom_button.setImageDrawable(getResources().getDrawable(R.drawable.ic_zoom));
                }
            }
        });
        commentRecy = findViewById(R.id.commentRecy);


        titleInCommetn.setText(title);
        descInComment.setText(description);

        commentActivityViewModel.init();

        initRecyclerView();


        commentActivityViewModel.getComments(postId).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                if(comments.size()>0){
                    adapter.setList(comments);
                    adapter.notifyDataSetChanged();
                    Log.d("proveraListe", "pozvan " + comments.size());
                }
            }
        });
    }

    private void initRecyclerView() {
        adapter = new CommentRecyAdapter(new ArrayList<Comment>(),getApplicationContext());
        commentRecy.setAdapter(adapter);
        commentRecy.setLayoutManager(new LinearLayoutManager(this));
    }
}