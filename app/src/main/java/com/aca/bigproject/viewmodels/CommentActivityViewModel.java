package com.aca.bigproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aca.bigproject.model.Comment;
import com.aca.bigproject.repositories.CommentRepository;

import java.util.List;

public class CommentActivityViewModel extends ViewModel {

    private MutableLiveData<Comment> mComments;
    private CommentRepository commentRepository;

    public void init(){

        commentRepository = CommentRepository.getInstance();

    }

    public LiveData<List<Comment>> getComments(int id){
        return  commentRepository.getComments(id);
    }

}
