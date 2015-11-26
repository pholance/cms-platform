package com.yidumen.cms.repository;

import com.yidumen.cms.MessageType;
import com.yidumen.cms.RecordType;
import com.yidumen.cms.entity.Record;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 蔡迪旻
 *         2015年11月15日
 */
@Repository
public class RecordHibernateRepository extends HibernateRepository<Record> {
    public RecordHibernateRepository() {
        super(Record.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Record> findNews() {
        final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("msgType", MessageType.news));
        criteria.add(Restrictions.eq("recordType", RecordType.PLATFORM));
        return criteria.list();
    }
}
