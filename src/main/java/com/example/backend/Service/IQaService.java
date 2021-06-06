package com.example.backend.Service;

public interface IQaService {

    /**
     * @param questionIndex
     * @param groupName
     * @param brandName
     * @param rankName
     * @return
     */
    String getAnswer(int questionIndex, String groupName, String brandName, String rankName, String vipName);

    /**
     * @param groupName
     * @return
     */
    String getGroupIntroAnswer(String groupName);

    /**
     * @param groupName
     * @return
     */
    String getBrandsUnderGroupAnswer(String groupName);

    /**
     * @param brandName
     * @return
     */
    String getGroupByBrandAnswer(String brandName);

    /**
     * @param brandName
     * @param groupName
     * @return
     */
    String getRelationBetweenBrandAndGroup(String brandName, String groupName);

    /**
     * @param brandName
     * @return
     */
    String getRankByBrandAnswer(String brandName);

    /**
     * @param rankName
     * @return
     */
    String getBrandsByRankAnswer(String rankName);

    /**
     * @param groupName
     * @param rankName
     * @return
     */
    String getBrandsByGroupAndRankAnswer(String groupName, String rankName);
}
