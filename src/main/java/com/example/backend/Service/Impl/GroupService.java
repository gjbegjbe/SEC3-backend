package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupService implements IGroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    BrandRepository brandRepository;

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
        HashMap<String, Object> rootNode = new HashMap<>();
        System.out.println(group.getName());
        rootNode.put("name", group.getName());
        rootNode.put("uuid", "group" + group.getId());
        rootNode.put("type", "group");
        nodeList.add(rootNode);

        for (Brand brand : brandRepository.findAllByGid(group.getId())) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", brand.getName());
            currNode.put("uuid", "brand" + brand.getId());
            currNode.put("type", "Brand");
//            currNode.put("color", "rgb(112,211,189)");
//            currNode.put("shape", "circle");
            nodeList.add(currNode);

            HashMap<String, Object> currLink1 = new HashMap<>();
            currLink1.put("sourceid", "group" + brand.getGid());
            currLink1.put("targetid", "brand" + brand.getId());
            currLink1.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(currLink1);
        }

        graph.put("nodes", nodeList);
        graph.put("links", linkList);
        return graph;
    }
}
