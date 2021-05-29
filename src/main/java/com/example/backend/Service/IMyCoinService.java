package com.example.backend.Service;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;

import java.util.HashMap;

public interface IMyCoinService {
    /**
     * @param group
     * @return
     */
    long addGroup(Group group);

    /**
     * @param gruopId
     * @return
     */
    boolean deleteGroupById(long gruopId);

    /**
     * @param brand
     * @return
     */
    long addBrand(Brand brand);

    /**
     * @param brandId
     * @return
     */
    boolean deleteBrandById(long brandId);

    /**
     * @return
     */
    HashMap<String, Object> getCoin();
}
