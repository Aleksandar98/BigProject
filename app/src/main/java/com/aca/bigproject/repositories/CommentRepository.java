package com.aca.bigproject.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aca.bigproject.model.Comment;
import com.aca.bigproject.model.Post;
import com.aca.bigproject.network.PostAPI;
import com.aca.bigproject.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CommentRepository {

    private static CommentRepository instance;
    private MutableLiveData<List<Comment>> data = new MutableLiveData<>();
    private List<Comment> commentList = new ArrayList<>();


    private Retrofit retrofitClient = RetrofitClient.getInstance();
    private PostAPI postAPI = retrofitClient.create(PostAPI .class);

    public static CommentRepository getInstance() {
        if (instance == null) {
            instance = new CommentRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Comment>> getComments(int id){
        fetchComments(id);
        data.postValue(commentList);
        return data;

    }

    private void fetchComments(int id) {
        postAPI.getCommentsForPost(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Comment> comments) {
                        data.postValue(comments);
                        commentList = comments;
                        Log.d("commentList", "onNext: "+comments.size());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
