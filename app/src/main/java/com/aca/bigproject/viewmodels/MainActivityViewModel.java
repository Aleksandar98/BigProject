package com.aca.bigproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aca.bigproject.model.Post;
import com.aca.bigproject.repositories.PostRepositiry;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Post>> mPosts;
    private PostRepositiry postRepositiry;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init() {
        if (mPosts != null) {
            return;
        }
        postRepositiry = PostRepositiry.getInstance();

        mPosts = postRepositiry.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return mPosts;
    }

    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public void addPost(final Post post) {
        isUpdating.setValue(true);

        //sleep for 2 seconds

        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        List<Post> newList = mPosts.getValue();
                        newList.add(post);
                        mPosts.postValue(newList);
                        isUpdating.postValue(false);

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
