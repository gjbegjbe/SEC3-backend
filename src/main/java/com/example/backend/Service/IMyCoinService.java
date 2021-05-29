package com.example.backend.Service;

import com.example.backend.Model.*;

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
     * @param group
     * @return
     */
    long addGroup(Group group);

    /**
     * @param gruopId
     * @return
     */
    boolean deleteGroupById(long gruopId);

    /**
     * @param brand
     * @return
     */
    long addBrand(Brand brand);

    /**
     * @param brandId
     * @return
     */
    boolean deleteBrandById(long brandId);

    /**
     * @return
     */
    HashMap<String, Object> getCoin();


    /**
     * @param kg
     */
    void updateCoin(KG kg);
}
