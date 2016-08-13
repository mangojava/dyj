package com.dyj.building.service;

import com.dyj.building.entity.Building;

import java.util.List;

/**
 * 建筑信息持久化操作，业务逻辑层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
public interface BuildingPersistenceService {

    /**
     * 新增保存建筑物信息。
     *
     * @param buildings 要持久化的建筑物
     */
    void saveBuilding(List<Building> buildings);
}
