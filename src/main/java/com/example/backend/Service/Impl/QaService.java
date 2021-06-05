package com.example.backend.Service.Impl;

import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;
import com.example.backend.Service.IQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QaService implements IQaService {
    @Autowired
    GroupService groupService;

    @Autowired
    BrandService brandService;

    @Override
    public String getAnswer(int questionIndex, String groupName, String brandName, String rankName) {
        if (questionIndex == 1)
            return getGroupIntroAnswer(groupName);
        if (questionIndex == 2)
            return getBrandsUnderGroupAnswer(groupName);
        if (questionIndex == 3)
            return getGroupByBrandAnswer(brandName);
        if (questionIndex == 4)
            return getRelationBetweenBrandAndGroup(brandName, groupName);
        if (questionIndex == 5)
            return getRankByBrandAnswer(brandName);
        if (questionIndex == 6)
            return getBrandsByRankAnswer(rankName);
        if (questionIndex == 7)
            return getBrandsByGroupAndRankAnswer(groupName, rankName);


        return "...";
    }

    @Override
    public String getGroupIntroAnswer(String groupName) {
        Group group = groupService.getGroupByName(groupName);
        if (group == null)
            return "没有找到这个集团的简介。";
        return group.getIntroduction();
    }

    @Override
    public String getBrandsUnderGroupAnswer(String groupName) {
        // TODO
        return null;
    }

    @Override
    public String getGroupByBrandAnswer(String brandName) {
        Group group = brandService.getGroupByBrandName(brandName);
        if (group == null)
            return "没有找到这个酒店所属的集团。";
        return brandName + "隶属于" + group.getName() + "。";
    }

    @Override
    public String getRelationBetweenBrandAndGroup(String brandName, String groupName) {
        // TODO
        return null;
    }

    @Override
    public String getRankByBrandAnswer(String brandName) {
        Rank rank = brandService.getRankByBrandName(brandName);
        if (rank == null)
            return "没有找到这个酒店的档次信息。";
        return brandName + "的定位是" + rank.getName() + "。";
    }

    @Override
    public String getBrandsByRankAnswer(String rankName) {
        // TODO
        return null;
    }

    @Override
    public String getBrandsByGroupAndRankAnswer(String groupName, String rankName) {
        // TODO
        return null;
    }
}
