package com.example.backend.Service.Impl;

import com.example.backend.Model.*;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Service.IQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QaService implements IQaService {
    @Autowired
    private GroupService groupService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private RankService rankService;

    @Autowired
    private VipService vipService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public String getAnswer(int questionIndex, String groupName, String brandName, String rankName, String vipName) {
        Group group = groupService.getGroupByNameContains(groupName);
        Brand brand = brandService.getBrandByNameContains(brandName);
        Rank rank = rankService.getRankByNameContains(rankName);
        Vip vip = vipService.getVipByNameContains(vipName);

        System.out.println(group);
        System.out.println(brand);
        System.out.println(rank);

        if (questionIndex == 1)
            return getGroupIntroAnswer(group);
        if (questionIndex == 2)
            return getBrandsUnderGroupAnswer(group);
        if (questionIndex == 3)
            return getRelationBetweenBrandAndGroup(brand, group);
        if (questionIndex == 4)
            return getGroupByBrandAnswer(brand);
        if (questionIndex == 5)
            return getRankByBrandAnswer(brand);
        if (questionIndex == 6)
            return getBrandsByRankAnswer(rank);
        if (questionIndex == 7)
            return getBrandsByGroupAndRankAnswer(group, rank, 8);
        if (questionIndex == 8)
            return getAppAndPlatByBrandAnswer(brand);
        if (questionIndex == 9)
            return getDiscountByBrandAndVipAnswer(brand, vip);


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
        if (group == null)
            return "没有找到这个集团的信息。";
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            String subRes = getBrandsByGroupAndRankAnswer(group, rankService.getRankById(i), 3);
            if (subRes.contains("没有"))
                continue;
            if (i != 1)
                subRes = subRes.substring(group.getName().length() + 5);
            res.append(subRes);
        }
        res = new StringBuilder(res.toString().replaceAll("。", ","));
        res = new StringBuilder(res.substring(0, res.length() - 1) + "。");
        return res.toString();
    }

    @Override
    public String getRelationBetweenBrandAndGroup(Brand brand, Group group) {
        if (brand == null)
            return "没有关于这个酒店的信息。";
        if (group == null)
            return "没有关于这个集团的信息。";
        if (brand.getGid() == group.getId()) {
            return "是的。";
        } else {
            return "不是。";
        }
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
        if (rank == null)
            return "没有这个档次信息。";
        List<Brand> brandList = new ArrayList<>();
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "高")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "中")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "低")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }

        StringBuilder res = new StringBuilder("推荐的" + rank.getName() + "酒店有");
        for (Brand brand : brandList) {
            res.append(brand.getName()).append("、");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("等。");
        return res.toString();
    }

    @Override
    public String getBrandsByGroupAndRankAnswer(Group group, Rank rank, int maxNum) {
        if (group == null)
            return "没有这个集团信息。";
        if (rank == null)
            return "没有这个档次信息。";
        List<Brand> brandList = new ArrayList<>();
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "高")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "中")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "低")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        if (brandList.isEmpty())
            return group.getName() + "旗下没有该档次的酒店。";

        StringBuilder res = new StringBuilder(group.getName() + "旗下推荐的" + rank.getName() + "酒店有");
        for (Brand brand : brandList) {
            res.append(brand.getName()).append("、");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("等。");
        return res.toString();
    }

    @Override
    public String getAppAndPlatByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        Group group = groupService.getGroupById(brand.getGid());
        String res = "";
        res += brand.getName();
        res += "可以在";
        res += group.getPlatform() + "和" + group.getHomepage() + "官网进行预订。";
        return res;
    }

    @Override
    public String getDiscountByBrandAndVipAnswer(Brand brand, Vip vip) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        if (vip == null)
            return "没有找到这个会员卡的信息。";

        Privilege privilege = privilegeService.getPrivilegeByVidAndBid(vip.getId(), brand.getId());
        if (privilege == null)
            return vip.getName() + "在预定" + brand.getName() + "时不享受权益。";

        String res = "";
        res += vip.getName();
        res += "在预定";
        res += brand.getName();
        res += "时享受";
        res += privilege.getDiscount();
        res += "优惠。";
        return res;
    }
}
