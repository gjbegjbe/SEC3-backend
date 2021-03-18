package com.example.backend.Service;

import com.example.backend.Model.Link;
import com.example.backend.Model.Node;

import java.util.HashMap;

public interface IMyCoinService {
    /**
     * @param node
     * @return
     */
    boolean addNode(Node node);

    /**
     * @param uuid
     * @return
     */
    boolean deleteNodeById(long uuid);

    /**
     * @param link
     * @return
     */
    boolean addRelation(Link link);

    /**
     * @param uuid
     * @return
     */
    boolean deleteRelationById(long uuid);

    /**
     * @param nodeId
     * @return
     */
    void deleteRelationByNodeId(long nodeId);

    /**
     * @return
     */
    HashMap<String, Object> getCoin();
}
