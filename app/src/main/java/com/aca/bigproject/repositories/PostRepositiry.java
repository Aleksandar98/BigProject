package com.aca.bigproject.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aca.bigproject.model.Post;
import com.aca.bigproject.network.PostAPI;
import com.aca.bigproject.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PostRepositiry {

    private static PostRepositiry instance = null;
    private List<Post> postList = new ArrayList<>();
    MutableLiveData<List<Post>> data = new MutableLiveData<>();

    private Retrofit retrofitClient = RetrofitClient.getInstance();
    private PostAPI postAPI = retrofitClient.create(PostAPI .class);

    public static PostRepositiry getInstance(){
        if(instance == null){
            instance =  new PostRepositiry();
        }
        return instance;
    }

    public MutableLiveData<List<Post>> getPosts(){
        fetchPosts();
        data.setValue(postList);
        return data;
    }

    private void fetchPosts(){



        Observable<List<Post>> observable = postAPI.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<Post>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Post> posts) {
                data.postValue(posts);
                postList = posts;
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void addPost(Post post){

    }
}
