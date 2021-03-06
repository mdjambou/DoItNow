package com.team.doitnow.common;

import android.support.v4.app.FragmentActivity;
import android.widget.Spinner;

import com.team.doitnow.adapter.Category;
import com.team.doitnow.business.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoi_000 on 28/03/2016.
 */
public class SpinnerLoader {

    public static List<String> loadCategory(FragmentActivity currentActivity) {

        CategoryRepository categoryRepository = new CategoryRepository(currentActivity);
        categoryRepository.Open();
        List<Category> listCat= categoryRepository.GetAll();
        categoryRepository.Close();

        List<String> catNames = new ArrayList<String>();
        for(int i = 0;i<listCat.size();i++){
            if(listCat.get(i)!=null){
                catNames.add(listCat.get(i).getName());
            }
        }
        return catNames;

    }
}
