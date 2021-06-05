package com.example.backend.Service;

import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;


public interface IBrandService {

    /**
     * @param brandName
     * @return
     */
    Group getGroupByBrandName(String brandName);

    /**
     * @param brandName
     * @return
     */
    Rank getRankByBrandName(String brandName);
}
