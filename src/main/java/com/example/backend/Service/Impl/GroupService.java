package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Privilege;
import com.example.backend.Model.Vip;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupService implements IGroupService {
    private final List<String> brandColorList = Arrays.asList(
            "rgb(230,241,216)",
            "rgb(175,215,136)",
            "rgb(91,189,43)",
            "rgb(72,150,32)",
            "rgb(54,117,23)"
    );

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private VipService vipService;

    @Override
    public List<String> getGroupNameList() {
        List<String> res = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            res.add(group.getName());
        }
        return res;
    }


    @Override
    public HashMap<String, Object> getGraphByGroupName4Level(String groupName) {
        HashMap<String, Object> graph = new HashMap<>();
        List<Object> nodeList = new ArrayList<>();
        List<Object> linkList = new ArrayList<>();

        Group group = groupRepository.findByName(groupName);
        HashMap<String, Object> groupNode = new HashMap<>();
        System.out.println(group.getName());
        groupNode.put("name", group.getName());
        groupNode.put("uuid", "group" + group.getId());
        groupNode.put("type", "group");
        groupNode.put("color", "rgb(125,213,255)");
        groupNode.put("shape", "diamond");
        nodeList.add(groupNode);

        for (Brand brand : brandRepository.findAllByGid(group.getId())) {
            HashMap<String, Object> brandNode = new HashMap<>();
            brandNode.put("name", brand.getName());
            brandNode.put("uuid", "brand" + brand.getId());
            brandNode.put("type", "Brand");
            brandNode.put("color", "rgb(80," + brand.getRid() * 50 + ",80)");
            brandNode.put("shape", "downtriangle");
            nodeList.add(brandNode);
            HashMap<String, Object> brandLink = new HashMap<>();
            brandLink.put("sourceid", "group" + brand.getGid());
            brandLink.put("targetid", "brand" + brand.getId());
            brandLink.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(brandLink);

            for (Privilege privilege : privilegeRepository.findAllByBid(brand.getId())) {
                Vip vip = vipService.getVipById(privilege.getVid());
                String vipName = vip.getName();

                HashMap<String, Object> vipNode = new HashMap<>();
                vipNode.put("name", vipName);
                vipNode.put("uuid", "vip" + brand.getId() + "-" + vip.getId());
                vipNode.put("type", "Vip");
                vipNode.put("color", "rgb(127,127,213)");
                vipNode.put("shape", "star");
                nodeList.add(vipNode);
                HashMap<String, Object> vipLink = new HashMap<>();
                vipLink.put("sourceid", "vip" + brand.getId() + "-" + vip.getId());
                vipLink.put("targetid", "brand" + brand.getId());
                vipLink.put("uuid", "brand" + brand.getId() + "-" + "vip" + brand.getId() + "-" + vip.getId());
                linkList.add(vipLink);

                HashMap<String, Object> breakfastNode = new HashMap<>();
                breakfastNode.put("name", privilege.getBreakfast() + "份");
                breakfastNode.put("uuid", "breakfast" + brand.getId() + "-" + vip.getId());
                breakfastNode.put("type", "Breakfast");
                nodeList.add(breakfastNode);
                HashMap<String, Object> breakfastLink = new HashMap<>();
                breakfastLink.put("name", "免费早餐");
                breakfastLink.put("sourceid", "breakfast" + brand.getId() + "-" + vip.getId());
                breakfastLink.put("targetid", "vip" + brand.getId() + "-" + vip.getId());
                breakfastLink.put("uuid", "breakfast" + brand.getId() + "-" + vip.getId() + "-" + "vip" + brand.getId() + "-" + vip.getId());
                linkList.add(breakfastLink);

                HashMap<String, Object> cheakoutNode = new HashMap<>();
                cheakoutNode.put("name", privilege.getCheckout());
                cheakoutNode.put("uuid", "cheakout" + brand.getId() + "-" + vip.getId());
                cheakoutNode.put("type", "Cheakout");
                cheakoutNode.put("color", "rgb(127,127,213)");
                cheakoutNode.put("shape", "roundrectangle");
                nodeList.add(cheakoutNode);
                HashMap<String, Object> cheakoutLink = new HashMap<>();
                cheakoutLink.put("name", "最晚退房");
                cheakoutLink.put("sourceid", "cheakout" + brand.getId() + "-" + vip.getId());
                cheakoutLink.put("targetid", "vip" + brand.getId() + "-" + vip.getId());
                cheakoutLink.put("uuid", "cheakout" + brand.getId() + "-" + vip.getId() + "-" + "vip" + brand.getId() + "-" + vip.getId());
                linkList.add(cheakoutLink);
            }
        }

        graph.put("nodes", nodeList);
        graph.put("links", linkList);
        return graph;
    }

    @Override
    public HashMap<String, Object> getGraphByGroupName3Level(String groupName) {
        HashMap<String, Object> graph = new HashMap<>();
        List<Object> nodeList = new ArrayList<>();
        List<Object> linkList = new ArrayList<>();

        Group group = groupRepository.findByName(groupName);
        HashMap<String, Object> groupNode = new HashMap<>();
        System.out.println(group.getName());
        groupNode.put("name", group.getName());
        groupNode.put("uuid", "group" + group.getId());
        groupNode.put("type", "group");
        groupNode.put("color", "rgb(125,213,255)");
        groupNode.put("shape", "piccircle");
        groupNode.put("imgsrc", group.getImgsrc());
        nodeList.add(groupNode);

        for (Brand brand : brandRepository.findAllByGid(group.getId())) {
            HashMap<String, Object> brandNode = new HashMap<>();
            brandNode.put("name", brand.getName());
            brandNode.put("uuid", "brand" + brand.getId());
            brandNode.put("type", "Brand");
            brandNode.put("color", brandColorList.get((int) (brand.getRid() - 1)));
            brandNode.put("shape", "downtriangle");
            nodeList.add(brandNode);
            HashMap<String, Object> brandLink = new HashMap<>();
            brandLink.put("sourceid", "group" + brand.getGid());
            brandLink.put("targetid", "brand" + brand.getId());
            brandLink.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(brandLink);

            Privilege privilege;
            try {
                privilege = privilegeRepository.findFirstByBidOrderByBreakfastDescCheckoutDesc(brand.getId());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            HashMap<String, Object> breakfastNode = new HashMap<>();
            breakfastNode.put("name", privilege.getBreakfast() + "份");
            breakfastNode.put("uuid", "breakfast" + brand.getId());
            breakfastNode.put("type", "Breakfast");
            breakfastNode.put("shape", "star");
            breakfastNode.put("color", "rgb(551,114,514)");
            nodeList.add(breakfastNode);
            HashMap<String, Object> breakfastLink = new HashMap<>();
            breakfastLink.put("name", "最多免费早餐");
            breakfastLink.put("sourceid", "breakfast" + brand.getId());
            breakfastLink.put("targetid", "brand" + brand.getId());
            breakfastLink.put("uuid", "breakfast" + brand.getId() + "-" + "brand" + brand.getId());
            linkList.add(breakfastLink);

            HashMap<String, Object> cheakoutNode = new HashMap<>();
            cheakoutNode.put("name", privilege.getCheckout());
            cheakoutNode.put("uuid", "cheakout" + brand.getId());
            cheakoutNode.put("type", "Cheakout");
            cheakoutNode.put("color", "rgb(127,127,213)");
            cheakoutNode.put("shape", "roundrectangle");
            nodeList.add(cheakoutNode);
            HashMap<String, Object> cheakoutLink = new HashMap<>();
            cheakoutLink.put("name", "最晚退房");
            cheakoutLink.put("sourceid", "cheakout" + brand.getId());
            cheakoutLink.put("targetid", "brand" + brand.getId());
            cheakoutLink.put("uuid", "cheakout" + brand.getId() + "-" + "brand" + brand.getId());
            linkList.add(cheakoutLink);
        }

        graph.put("nodes", nodeList);
        graph.put("links", linkList);
        return graph;
    }

    @Override
    public Group getGroupByNameContains(String name) {
        try {
            return groupRepository.findByNameContains(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Group getGroupById(long id) {
        try {
            return groupRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getDetailByGroupName(String groupName) {
        Group group = getGroupByNameContains(groupName);
        if (group == null)
            return "暂无相关信息。";
        return group.getIntroduction() + groupName + "的APP是" + group.getPlatform() + "，官方站点是" + group.getHomepage() + "。";
    }
}
