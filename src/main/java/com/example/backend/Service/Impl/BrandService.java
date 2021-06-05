package com.example.backend.Service.Impl;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.RankRepository;
import com.example.backend.Service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    RankRepository rankRepository;


    @Override
    public Group getGroupByBrandName(String brandName) {
        try {
            Brand brand = brandRepository.findByName(brandName);
            Group group = groupRepository.findById(brand.getGid())
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found for this brandName"));
            return group;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Rank getRankByBrandName(String brandName) {
        try {
            Brand brand = brandRepository.findByName(brandName);
            Rank rank = rankRepository.findById(brand.getRid())
                    .orElseThrow(() -> new ResourceNotFoundException("Rank not found for this brandName"));
            return rank;
        } catch (Exception e) {
            return null;
        }
    }
}
