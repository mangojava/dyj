package com.dyj.building.service;

import com.dyj.building.entity.Building;
import com.dyj.building.entity.Storey;
import com.dyj.building.entity.Unit;

import java.util.List;

/**
 * 建筑详细信息多级展示，业务逻辑层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
public interface BuildingShowService {

    /**
     * 获取所有建筑物的数据。
     *
     * @return 返回建筑物集合
     */
    List<Building> findBuildings();

    /**
     * 根据建筑物ID获取其下所有的楼层数据。
     *
     * @param buildingId 建筑物ID
     * @return 返回拥有的所有楼层数据
     */
    List<Storey> findStoreysByBuildingId(String buildingId);

    /**
     * 根据单元ID获取其单元数据。
     *
     * @param unitId 单元ID
     * @return 返回其单元数据
     */
    Unit getUnitByUnitId(String unitId);
}
