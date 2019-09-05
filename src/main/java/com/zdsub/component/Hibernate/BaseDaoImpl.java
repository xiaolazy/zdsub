package com.zdsub.component.Hibernate;


import com.zdsub.utils.Reflection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 16:22
 **/
public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T,PK> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    protected SessionFactory sessionFactory;
    protected Class<T> entityName;

    {
        this.entityName = Reflection.getClassGenricType(getClass());
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    @Override
    public void saveOrUpdate(Object entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void save(Object entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Object entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(Object entity) {
        getSession().delete(entity);
    }

    @Override
    public Page<T> findPage(final Page<T> page) {
        return getPage(page);
    }
    @Override
    public void delete(PK id) {
        getSession().delete(find(id));
    }

    @Override
    public T find(PK id) {
        return (T)getSession().get(entityName,id);
    }

    @Override
    public List<T> findAll() {
        return getAll();
    }
    @Override
    public void flushSession() {
        getSession().flush();
    }
    /**
     * 查询所有列表参数
     * @author:lyy
     * @param:a     * @param:a
     * @Date: 2019/9/4 23:00
     **/
    public List<T> getAll(Criterion...criterions){
        return createCriteria(criterions).list();
    }

    /**
     * 分页查询所有列表参数
     * @author:lyy
     * @param:Page
     * @param:Criterion
     * @Date: 2019/9/4 23:01
     **/

    public Page<T> getPage(final Page<T> page,final Criterion...criterion){
        Criteria c = createCriteria(entityName, criterion);
        long totalCount = countCriteriaResult(c);
        c.setFirstResult(page.getPageNo());
        c.setMaxResults(page.getPageSize());
        List list = c.list();
        page.setTotalCount(totalCount);
        page.setResultList(list);
        return page;
    }

    /**
     * 创建查询条件Criteria
     * @author:lyy
     * @param:Criterion
     * @Date: 2019/9/4 23:03
     */

    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityName);
        if(criterions==null)
            return criteria;
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 创建查询条件Criteria
     * @author:lyy
     * @param:entityClass
     * @param:Criterion
     * @Date: 2019/9/4 23:04
     */

    public Criteria createCriteria(Class<?> entityname,final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityname);
        if(criterions==null)
            return criteria;
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 查询本次分页的数据的总数
     * @author:lyy
     * @param:Criteria
     * @Date: 2019/9/4 23:05
     */

    protected long countCriteriaResult(final Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection取出来,清空后再执行Count操作
        Projection projection = impl.getProjection();
        // 执行Count查询
        Long totalCountObject = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = (null == totalCountObject) ? 0 : totalCountObject;
        // 将之前的Projection条件重新设回去
        c.setProjection(projection);
        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        return totalCount;
    }
   /* protected long countCriteriaResult(final Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        try {
            orderEntries = (List<OrderEntry>) Reflection.getFieldValue(impl, "orderEntries");
            Reflection.setFieldValue(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        // 执行Count查询
        Long totalCountObject = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = (totalCountObject != null) ? totalCountObject : 0;

        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            c.setResultTransformer(transformer);
        }
        try {
            Reflection.setFieldValue(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        return totalCount;
    }*/
}
