package com.dyj.building.dao;

import com.dyj.building.entity.Storey;

import java.util.List;

/**
 * 楼层，数据访问层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
public interface StoreyDao {

    /**
     * 根据建筑ID，获取所有楼层信息集合。
     *
     * @param buildingId 所属建筑ID
     * @return 返回查询的楼层信息集合
     */
    List<Storey> findByBuildingId(String buildingId);
}
