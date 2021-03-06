package com.droidman.mvvm_demo1.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.droidman.mvvm_demo1.Entity.CategoriesEntity;
import com.droidman.mvvm_demo1.Repository.CategoriesRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {

    private CategoriesRepository categoriesRepository;
    private LiveData<List<CategoriesEntity>> allCategories;

    /* Never store Context of an activity in the ViewModel */

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        this.categoriesRepository = new CategoriesRepository(application);
        this.allCategories = categoriesRepository.getAllCategories();
    }

    public void insert(CategoriesEntity categoriesEntity) {
        categoriesRepository.insert(categoriesEntity);
    }

    public LiveData<List<CategoriesEntity>> getAllCategories() {
        return allCategories;
    }
}
