package com.aca.bigproject.repositories;

import androidx.lifecycle.MutableLiveData;

import com.aca.bigproject.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PostRepositiry {

    private static PostRepositiry instance = null;
    private List<Post> postList = new ArrayList<>();

    public static PostRepositiry getInstance(){
        if(instance == null){
            instance =  new PostRepositiry();
        }
        return instance;
    }

    public MutableLiveData<List<Post>> getPosts(){
        setPosts();

        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        data.setValue(postList);
        return data;
    }

    private void setPosts(){
        postList.add(new Post(1,1,"Title","Desription"));
        postList.add(new Post(1,1,"Title","Desription"));
        postList.add(new Post(1,1,"Title","Desription"));
        postList.add(new Post(1,1,"Title","Desription"));
        postList.add(new Post(1,1,"Title","Desription"));
        postList.add(new Post(1,1,"Title","Desription"));

    }

    private void addPost(Post post){

    }
}
