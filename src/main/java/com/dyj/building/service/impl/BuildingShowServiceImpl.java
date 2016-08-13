package com.dyj.building.service.impl;

import com.dyj.building.dao.BuildingDao;
import com.dyj.building.dao.StoreyDao;
import com.dyj.building.dao.UnitDao;
import com.dyj.building.entity.Building;
import com.dyj.building.entity.Storey;
import com.dyj.building.entity.Unit;
import com.dyj.building.service.BuildingShowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 建筑详细信息多级展示，业务逻辑层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
@Service("buildingShowService")
public class BuildingShowServiceImpl implements BuildingShowService {

    @Resource(name = "buildingDao")
    private BuildingDao buildingDao;
    @Resource(name = "storeyDao")
    private StoreyDao storeyDao;
    @Resource(name = "unitDao")
    private UnitDao unitDao;

    /**
     * 获取所有建筑物的数据。
     *
     * @return 返回建筑物集合
     */
    @Override
    public List<Building> findBuildings() {
        return buildingDao.find();
    }

    /**
     * 根据建筑物ID获取其下所有的楼层数据。
     *
     * @param buildingId 建筑物ID
     * @return 返回拥有的所有楼层数据
     */
    @Override
    public List<Storey> findStoreysByBuildingId(String buildingId) {
        return storeyDao.findByBuildingId(buildingId);
    }

    /**
     * 根据单元ID获取其单元数据。
     *
     * @param unitId 单元ID
     * @return 返回其单元数据
     */
    @Override
    public Unit getUnitByUnitId(String unitId) {
        return unitDao.getById(unitId);
    }
}
