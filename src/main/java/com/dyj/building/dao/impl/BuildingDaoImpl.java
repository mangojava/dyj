package com.dyj.building.dao.impl;

import com.dyj.building.dao.BuildingDao;
import com.dyj.building.entity.Building;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 建筑物，数据访问层实现类。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Repository("buildingDao")
public class BuildingDaoImpl implements BuildingDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 获取所有建筑物信息。
     *
     * @return 返回建筑物查询结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Building> find() {
        Session hSession = sessionFactory.getCurrentSession();
        Query query = hSession.createQuery("from Building b where b.status > 0 order by b.createTime desc ");
        return query.list();
    }

    /**
     * 新增保持建筑物信息。
     *
     * @param buildings 要持久化的建筑物
     */
    @Override
    public void save(List<Building> buildings) {
        Session hSession = sessionFactory.getCurrentSession();
        for (Building b : buildings) {
            hSession.save(b);
        }
    }
}
