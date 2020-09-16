package com.aca.bigproject.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aca.bigproject.model.Comment;
import com.aca.bigproject.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;

public class CommentActivityViewModel extends ViewModel {

    private MutableLiveData<List<Comment>> mComments;
    private CommentRepository commentRepository;

    public void init(){

        commentRepository = CommentRepository.getInstance();
        mComments =  new MutableLiveData<>();
    }

    public LiveData<List<Comment>> getComments(int id){
        LiveData<List<Comment>> lista = commentRepository.getComments(id);
//        if(lista.getValue() == null){
//           // Log.d("proveraListe", "getComments: lista JE NULL");
//            return mComments;
//        }else
        return  lista;
    }

}
