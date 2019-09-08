package com.zdsub.component.hibernate;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 19:23
 **/
@Getter
@Setter
public class Page<T extends Object> extends PageCondition {

    /*********分页参数**********/
    protected int pageNo= 1;
    protected int pageSize = -1;
    protected String orderBy;
    protected String order;
    protected long totalCount=-1;
    /**********参数列表*********/
    protected List<T> resultList = Lists.newArrayList();

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    /*public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }*/
    public Page(){ }
    public Page(int pageSize){
    this.pageSize = pageSize;
    }
    public Page(int pageNo,int pageSize,String orderBy,String order){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.order = order;
        this.orderBy = orderBy;
    }
    public Page(PageCondition condition){
        this.condition = condition;
    }

    @Override
    public Page toPage() {
        return this;
    }
}
