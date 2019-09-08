package com.zdsub.component.hibernate;

import java.io.Serializable;
import java.util.List;

public interface BaseDao <T,PK extends Serializable> {
    /**
     * 保存或更新
     * @param entity
     * */
    void saveOrUpdate(final T entity);
    /**
     * 保存
     * @param entity
     * */
    void save(final T entity);
    /**
     * 更新
     * @param entity
     * */
    void update(final T entity);
    /**
     * 删除
     * @param entity
     * */
    void delete(final T entity);
    /**
     * 根据ID删除
     * @param id
     * */
    void delete(final PK id);
    /**
     * 根据id查询
     * @param id
     * */
    T find(final PK id);
    /**
     * 查询所有
     * */
    List<T> findAll();
    /**
     * 分页查询
     * */
    Page<T> findPage(Page<T> page);
    /**
     * 刷新session
     * */
    void flushSession();
}
