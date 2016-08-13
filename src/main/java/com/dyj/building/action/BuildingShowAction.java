package com.dyj.building.action;

import com.dyj.building.entity.Building;
import com.dyj.building.entity.Storey;
import com.dyj.building.entity.Unit;
import com.dyj.building.service.BuildingShowService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 建筑信息多级展示，控制层。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Controller("buildingShowAction")
@Scope("prototype")
@RequestMapping(value = "/bs")
public class BuildingShowAction {

    @Resource(name = "buildingShowService")
    private BuildingShowService buildingShowService;

    /**
     * 获取所有建筑物的数据。
     *
     * @return 返回建筑物集合
     */
    @RequestMapping(value = "/building", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Building> findBuildings() {
        return buildingShowService.findBuildings();
    }

    /**
     * 根据建筑物ID获取其下所有的楼层数据。
     *
     * @param buildingId 建筑物ID
     * @return 返回拥有的所有楼层数据
     */
    @RequestMapping(value = "/storey/{buildingId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Storey> findStoreysByBuildingId(@PathVariable String buildingId) {
        return buildingShowService.findStoreysByBuildingId(buildingId);
    }

    /**
     * 根据单元ID获取其单元数据。
     *
     * @param unitId 单元ID
     * @return 返回其单元数据
     */
    @RequestMapping(value = "/unit/{unitId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Unit getUnitByUnitId(@PathVariable String unitId) {
        return buildingShowService.getUnitByUnitId(unitId);
    }
}
