package com.example.backend.Service.Impl;

import com.example.backend.Model.Group;
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


        return null;
    }
}
