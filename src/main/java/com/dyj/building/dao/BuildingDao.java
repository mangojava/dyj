package com.dyj.building.dao;

import com.dyj.building.entity.Building;

import java.util.List;

/**
 * 建筑物，数据访问层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
public interface BuildingDao {

    /**
     * 获取所有建筑物信息。
     *
     * @return 返回建筑物查询结果
     */
    List<Building> find();

    /**
     * 新增保持建筑物信息。
     *
     * @param buildings 要持久化的建筑物
     */
    void save(List<Building> buildings);
}
