package com.yidumen.cms.repository;

import com.yidumen.cms.entity.Sutra;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 蔡迪旻
 *         2016年01月25日
 */
@Repository
public class SutraHibernateRepository extends HibernateRepository<Sutra> {
    @Autowired
    public SutraHibernateRepository(SessionFactory sessionFactory) {
        super(Sutra.class, sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sutra> findAll() {
        return (List<Sutra>) getHibernateTemplate().find("select sutra from Sutra sutra order by sutra.leftValue");
    }
}
