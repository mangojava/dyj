package com.dyj.building.dao.impl;

import com.dyj.building.dao.StoreyDao;
import com.dyj.building.entity.Storey;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 楼层，数据访问层接口。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Repository("storeyDao")
public class StoreyDaoImpl implements StoreyDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 根据建筑ID，获取所有楼层信息集合。
     *
     * @param buildingId 所属建筑ID
     * @return 返回查询的楼层信息集合
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Storey> findByBuildingId(String buildingId) {
        Session hSession = sessionFactory.getCurrentSession();
        Query query = hSession.createQuery("from Storey s where s.building.tid = :buildingId and s.status > 0 order by s.floorNum asc ");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("buildingId", buildingId);
        return query.setProperties(paramMap).list();
    }
}
