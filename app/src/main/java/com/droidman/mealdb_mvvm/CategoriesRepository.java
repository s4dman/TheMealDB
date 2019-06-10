package com.droidman.mealdb_mvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CategoriesRepository {
    private CategoriesDao categoriesDao;
    private LiveData<List<CategoriesEntity>> allCategories;


    /*Creating Constructor of the given variables*/
    public CategoriesRepository(Application application){

        CategoriesDatabase categoriesDatabase = CategoriesDatabase.getInstance(application); // Creating DB instance
        // abstract method from CategoriesDataBase class.
        // We can call this abstract method because of the CategoriesDatabase instance using builder,
        // Room will auto generate all the codes for this method.
        // Same will happen for allCategories
        categoriesDao = categoriesDatabase.categoriesDao();
        allCategories = categoriesDao.getAll();
    }

    //Creating Methods for all out DB operations
    public void insert(CategoriesEntity categoriesEntity){
        new InsertAsyncTask(categoriesDao).execute(categoriesEntity);
    }

    public void update(CategoriesEntity categoriesEntity){}

    public void delete(CategoriesEntity categoriesEntity){}

    public void deleteAll(){}

    public LiveData<List<CategoriesEntity>> getAllCategories() {
        return allCategories;
    }


    private static class InsertAsyncTask extends AsyncTask<CategoriesEntity, Void, Void> {
        private CategoriesDao categoriesDao;

        public InsertAsyncTask(CategoriesDao categoriesDao) {
            this.categoriesDao = categoriesDao;
        }

        @Override
        protected Void doInBackground(CategoriesEntity... categoriesEntities) {
            categoriesDao.insert(categoriesEntities[0]);
            return null;
        }
    }

}
