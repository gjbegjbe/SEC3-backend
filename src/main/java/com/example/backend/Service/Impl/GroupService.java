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
import java.util.HashMap;
import java.util.List;

@Service
public class GroupService implements IGroupService {
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
    public HashMap<String, Object> getGraphByGroupName(String groupName) {
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
            brandLink.put("sourceid", "brand" + brand.getId());
            brandLink.put("targetid", "group" + brand.getGid());
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

                HashMap<String, Object> BreakfastNode = new HashMap<>();
                BreakfastNode.put("name", privilege.getBreakfast() + "份");
                BreakfastNode.put("uuid", "breakfast" + brand.getId() + "-" + vip.getId());
                BreakfastNode.put("type", "Breakfast");
                nodeList.add(BreakfastNode);
                HashMap<String, Object> BreakfastLink = new HashMap<>();
                BreakfastLink.put("name", "免费早餐");
                BreakfastLink.put("sourceid", "breakfast" + brand.getId() + "-" + vip.getId());
                BreakfastLink.put("targetid", "vip" + brand.getId() + "-" + vip.getId());
                BreakfastLink.put("uuid", "breakfast" + brand.getId() + "-" + vip.getId() + "-" + "vip" + brand.getId() + "-" + vip.getId());
                linkList.add(BreakfastLink);

                HashMap<String, Object> CheakoutNode = new HashMap<>();
                CheakoutNode.put("name", privilege.getCheckout());
                CheakoutNode.put("uuid", "cheakout" + brand.getId() + "-" + vip.getId());
                CheakoutNode.put("type", "Cheakout");
                CheakoutNode.put("color", "rgb(127,127,213)");
                CheakoutNode.put("shape", "roundrectangle");
                nodeList.add(CheakoutNode);
                HashMap<String, Object> CheakoutLink = new HashMap<>();
                CheakoutLink.put("name", "最晚退房");
                CheakoutLink.put("sourceid", "cheakout" + brand.getId() + "-" + vip.getId());
                CheakoutLink.put("targetid", "vip" + brand.getId() + "-" + vip.getId());
                CheakoutLink.put("uuid", "cheakout" + brand.getId() + "-" + vip.getId() + "-" + "vip" + brand.getId() + "-" + vip.getId());
                linkList.add(CheakoutLink);
            }
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
}
