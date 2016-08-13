package com.dyj.building.service.impl;

import com.dyj.building.dao.BuildingDao;
import com.dyj.building.dao.UnitDao;
import com.dyj.building.entity.Building;
import com.dyj.building.service.BuildingPersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 建筑信息持久化操作，业务逻辑层实现类。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
@Service("buildingPersistenceService")
public class BuildingPersistenceServiceImpl implements BuildingPersistenceService {

    @Resource(name = "buildingDao")
    BuildingDao buildingDao;
    @Resource(name = "unitDao")
    UnitDao unitDao;

    /**
     * 新增保持建筑物信息。
     *
     * @param buildings 要持久化的建筑物
     */
    @Override
    public void saveBuilding(List<Building> buildings) {
        buildingDao.save(buildings);
    }
}
