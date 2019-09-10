package com.zdsub.dao.supportTibet.Impl;

import com.zdsub.component.hibernate.BaseDaoImpl;
import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.supportTibet.SchoolDao;
import com.zdsub.entity.PageCondition;
import com.zdsub.entity.university.School;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.dao.university.impl
 * @Author: ly
 * @CreateTime: 2019-09-06 11:01
 * @Description:
 */
@Repository
public class SchoolDaoImpl extends BaseDaoImpl<School, String> implements SchoolDao {
    private void conditionCritiera(PageCondition<School> pageCondition, Criteria criteria) {
        if (pageCondition.getCondition() != null) {
            String sch_name = pageCondition.getCondition().getSch_name();
            if (StringUtils.isNotBlank(sch_name)) {
                criteria.add(Restrictions.like("sch_name", "%" + sch_name + "%", MatchMode.ANYWHERE).ignoreCase()).list();
            }
        }
    }
    @Override
    public Page<School> queryByName(PageCondition pageCondition) {
        Criteria criteria = getSession().createCriteria(School.class);
        conditionCritiera(pageCondition, criteria);
        Page<School> schoolPage = pageCondition.getPage();
        criteria.setMaxResults(schoolPage.getPageSize());
        criteria.setFirstResult((schoolPage.getPageNo() - 1) * schoolPage.getPageSize());
        List<School> schoolList = criteria.list();
        if (schoolList != null) {
            schoolPage.setResultList(schoolList);
        }
        return schoolPage;
    }
}
