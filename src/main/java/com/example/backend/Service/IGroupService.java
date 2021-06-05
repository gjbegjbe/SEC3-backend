package com.example.backend.Service;

import com.example.backend.Model.Group;

import java.util.HashMap;
import java.util.List;

public interface IGroupService {

    /**
     * @return
     */
    List<String> getGroupNameList();

    /**
     * @param groupName
     */
    HashMap<String, Object> getGraphByGroupName(String groupName);

    /**
     * @param groupName
     * @return
     */
    Group getGroupByName(String groupName);
}
