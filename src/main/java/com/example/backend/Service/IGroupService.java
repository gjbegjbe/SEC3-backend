package com.example.backend.Service;

import java.util.HashMap;
import java.util.List;

public interface IGroupService {

    /**
     *
     * @return
     */
    List<String> getGroupNameList();

    /**
     * @param groupName
     */
    HashMap<String, Object> getGraphByGroupName(String groupName);
}
