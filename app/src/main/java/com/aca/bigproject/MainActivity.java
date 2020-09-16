package com.aca.bigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aca.bigproject.adapters.RecyclerViewAdapter;
import com.aca.bigproject.model.Comment;
import com.aca.bigproject.model.Post;
import com.aca.bigproject.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    RecyclerViewAdapter adapter;
    MainActivityViewModel mainActivityViewModel;
    ProgressBar progressBar;
    List<Post> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progressBar);



        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();

        mainActivityViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                lista = posts;
                adapter.setList(posts);
                adapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    showProgressBar();
                else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getPosts().getValue().size()-1);
                }
            }
        });

        initRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.addPost(new Post(99,99,"NOVI","POST"));
            }
        });
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(this,mainActivityViewModel.getPosts().getValue());
        adapter.setPostClickListener(new RecyclerViewAdapter.RecyclerViewVH.OnPostClickListener() {
            @Override
            public void onPostClick(int position, View v, TextView titleText,TextView descText) {

                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this, Pair.create( titleText,"title"),Pair.create(descText,"description"));
                intent.putExtra("commentId",lista.get(position).getId());
                intent.putExtra("title",lista.get(position).getTitle());
                intent.putExtra("description",lista.get(position).getBody());
                startActivity(intent,options.toBundle());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void showProgressBar(){ progressBar.setVisibility(View.VISIBLE);}
    private void hideProgressBar(){ progressBar.setVisibility(View.GONE);}
}