package com.aca.bigproject.network;

import com.aca.bigproject.model.Comment;
import com.aca.bigproject.model.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostAPI {

    @GET("posts")
    public Observable<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    public Observable<List<Comment>> getCommentsForPost(@Path("id") int id);
}
