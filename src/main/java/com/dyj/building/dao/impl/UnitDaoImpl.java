package com.dyj.building.dao.impl;

import com.dyj.building.dao.UnitDao;
import com.dyj.building.entity.Unit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元，数据访问层实现类。
 *
 * @author 林久久, created at 2016/8/10.
 */
@Repository("unitDao")
public class UnitDaoImpl implements UnitDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 根据ID获取单元。
     *
     * @param unitId 单元ID
     * @return 返回查询的单元
     */
    @SuppressWarnings("unchecked")
    @Override
    public Unit getById(String unitId) {
        Session hSession = sessionFactory.getCurrentSession();
        Query query = hSession.createQuery("from Unit u where u.tid = :unitId and u.status > 0 ");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("unitId", unitId);
        List<Unit> units = query.setProperties(paramMap).list();
        return (units != null && units.size() > 0) ? units.get(0) : null;
    }
}
