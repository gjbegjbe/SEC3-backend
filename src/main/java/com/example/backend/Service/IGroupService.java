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
     * @param name
     * @return
     */
    Group getGroupByNameContains(String name);

    /**
     * @param id
     * @return
     */
    Group getGroupById(long id);
}
