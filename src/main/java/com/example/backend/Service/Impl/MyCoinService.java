package com.example.backend.Service.Impl;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.*;
import com.example.backend.Repository.*;
import com.example.backend.Service.IMyCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MyCoinService implements IMyCoinService {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RankRepository rankRepository;

    @Override
    public boolean addNode(Node node) {
        try {
            nodeRepository.save(node);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteNodeById(long uuid) {
        try {
            Node resNode = nodeRepository.findById(uuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Node not found for this id : " + uuid));
            nodeRepository.delete(resNode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addRelation(Link link) {
        try {
            linkRepository.save(link);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRelationById(long uuid) {
        try {
            Link resLink = linkRepository.findById(uuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Relation not found for this id : " + uuid));
            linkRepository.delete(resLink);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteRelationByNodeId(long nodeId) {
        List<Link> links1 = linkRepository.findBySourceid(nodeId);
        linkRepository.deleteAll(links1);
        List<Link> links2 = linkRepository.findByTargetid(nodeId);
        linkRepository.deleteAll(links2);
    }

    @Override
    public HashMap<String, Object> getCoin() {
        HashMap<String, Object> coin = new HashMap<>();
        List<Object> nodeList = new ArrayList<>();
        List<Object> linkList = new ArrayList<>();

        for (Group group : groupRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", group.getName());
            currNode.put("uuid", "group" + group.getId());
            currNode.put("type", "Group");
            currNode.put("color", "rgb(125,213,255)");
            currNode.put("shape", "diamond");
            nodeList.add(currNode);
        }

        for (Brand brand : brandRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", brand.getName());
            currNode.put("uuid", "brand" + brand.getId());
            currNode.put("type", "Brand");
            currNode.put("color", "rgb(112,211,189)");
            currNode.put("shape", "circle");
            nodeList.add(currNode);

            HashMap<String, Object> currLink1 = new HashMap<>();
            currLink1.put("sourceid", "group" + brand.getGid());
            currLink1.put("targetid", "brand" + brand.getId());
            currLink1.put("name", "从属于");
            currLink1.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(currLink1);

            HashMap<String, Object> currLink2 = new HashMap<>();
            currLink2.put("sourceid", "rank" + brand.getRid());
            currLink2.put("targetid", "brand" + brand.getId());
            currLink2.put("name", "等级");
            currLink2.put("uuid", "rank" + brand.getRid() + "-" + "brand" + brand.getId());
            linkList.add(currLink2);
        }

        for (Rank rank : rankRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", rank.getName());
            currNode.put("uuid", "rank" + rank.getId());
            currNode.put("type", "Rank");
            currNode.put("color", "rgb(255,131,115)");
            currNode.put("shape", "square");
            nodeList.add(currNode);
        }


        coin.put("nodes", nodeList);
        coin.put("links", linkList);
        return coin;
    }

    @Override
    public void updateCoin(KG kg) {
        nodeRepository.deleteAll();
        nodeRepository.saveAll(kg.getNode());
        linkRepository.deleteAll();
        linkRepository.saveAll(kg.getRelationship());
    }
}
