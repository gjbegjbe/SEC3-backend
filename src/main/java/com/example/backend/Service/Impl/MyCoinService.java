package com.example.backend.Service.Impl;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Link;
import com.example.backend.Model.Node;
import com.example.backend.Repository.LinkRepository;
import com.example.backend.Repository.NodeRepository;
import com.example.backend.Service.IMyCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MyCoinService implements IMyCoinService {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private LinkRepository linkRepository;

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
    public void deleteRelationByNodeId(int nodeId) {
        List<Link> links1 = linkRepository.findBySourceid(nodeId);
        linkRepository.deleteAll(links1);
        List<Link> links2 = linkRepository.findByTargetid(nodeId);
        linkRepository.deleteAll(links2);
    }

    @Override
    public HashMap<String, Object> getCoin() {
        HashMap<String, Object> coin = new HashMap<>();
        coin.put("node", nodeRepository.findAll());
        coin.put("relationship", linkRepository.findAll());
        return coin;
    }
}
