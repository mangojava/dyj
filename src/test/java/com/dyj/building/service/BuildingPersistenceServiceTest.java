package com.dyj.building.service;

import com.dyj.building.entity.Building;
import com.dyj.building.entity.Storey;
import com.dyj.building.entity.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 建筑物持久化操作业务逻辑，单元测试。
 *
 * @author 林久久, created at 2016/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring-*.xml"})
@Transactional
public class BuildingPersistenceServiceTest {

    @Resource(name = "buildingPersistenceService")
    BuildingPersistenceService buildingPersistenceService;

    @Rollback
    @Test
    public void save() throws Exception {

        List<Building> buildings = new ArrayList<>();

        // 创建建筑大楼
        Building b = new Building();
        b.setBuildingName("模拟大厦");
        b.setLon(119.35D);
        b.setLat(26.025D);
        b.setStatus(1);
        b.setStructChartUrl("/upload/image/building/building_001.png");

        int storeyMax = 26;
        int unitMax = 12;
        // 创建层和单元
        // 遍历楼层
        for (int i = 0; i < storeyMax; i++) {

            Storey s = new Storey();
            s.setFloorNum(i + 1);
            s.setStructChartUrl("/upload/image/storey/storey_001.png");
            s.setStatus(1);
            s.setBuilding(b);

            // 遍历单元
            for (int j = 0; j < unitMax; j++) {
                Unit u = new Unit();
                u.setUnitName(MessageFormat.format("{0}0{1}", String.format("%02d", i + 1), String.format("%02d", j + 1)));
                u.setStructChartUrl("/upload/image/unit/unit_001.png");
                u.setStatus(1);
                u.setStorey(s);
                s.getUnits().add(u);
            }

            b.getStoreys().add(s);
        }

        buildings.add(b);
        buildingPersistenceService.saveBuilding(buildings);
    }

}