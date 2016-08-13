package com.dyj.building.dao;

import com.dyj.building.entity.Unit;

/**
 * 单元，数据访问层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
public interface UnitDao {

    /**
     * 根据ID获取单元。
     *
     * @param unitId 单元ID
     * @return 返回查询的单元
     */
    Unit getById(String unitId);
}
