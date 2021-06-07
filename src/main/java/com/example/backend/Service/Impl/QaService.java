package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.RankRepository;
import com.example.backend.Service.IQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QaService implements IQaService {
    @Autowired
    GroupService groupService;

    @Autowired
    BrandService brandService;

    @Autowired
    RankService rankService;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    RankRepository rankRepository;

    @Override
    public String getAnswer(int questionIndex, String groupName, String brandName, String rankName, String vipName) {
        Group group = groupService.getGroupByNameContains(groupName);
        Brand brand = brandService.getBrandByNameContains(brandName);
        Rank rank = rankService.getRankByNameContains(rankName);

        System.out.println(group);
        System.out.println(brand);
        System.out.println(rank);

        if (questionIndex == 1)
            return getGroupIntroAnswer(group);
        if (questionIndex == 2)
            return getBrandsUnderGroupAnswer(group);
        if (questionIndex == 3)
            return getGroupByBrandAnswer(brand);
        if (questionIndex == 4)
            return getRelationBetweenBrandAndGroup(brand, group);
        if (questionIndex == 5)
            return getRankByBrandAnswer(brand);
        if (questionIndex == 6)
            return getBrandsByRankAnswer(rank);
        if (questionIndex == 7)
            return getBrandsByGroupAndRankAnswer(group, rank);


        return "...";
    }

    @Override
    public String getGroupIntroAnswer(Group group) {
        if (group == null)
            return "没有找到这个集团的简介。";
        return group.getIntroduction();
    }

    @Override
    public String getBrandsUnderGroupAnswer(Group group) {
        // TODO
        return null;
    }

    @Override
    public String getGroupByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店所属的集团。";
        Group group = groupService.getGroupById(brand.getGid());
        if (group == null)
            return "没有找到这个酒店所属的集团。";
        return brand.getName() + "隶属于" + group.getName() + "。";
    }

    @Override
    public String getRelationBetweenBrandAndGroup(Brand brand, Group group) {
        // TODO
        return null;
    }

    @Override
    public String getRankByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的档次信息。";
        Rank rank = rankService.getRankById(brand.getRid());
        if (rank == null)
            return "没有找到这个酒店的档次信息。";
        return brand.getName() + "的定位是" + rank.getName() + "。";
    }

    @Override
    public String getBrandsByRankAnswer(Rank rank) {
        // TODO
        return null;
    }

    @Override
    public String getBrandsByGroupAndRankAnswer(Group group, Rank rank) {
        // TODO
        return null;
    }
}
