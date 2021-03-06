package com.yidumen.cms.repository;

import com.yidumen.cms.entity.Account;
import com.yidumen.cms.entity.Page;
import com.yidumen.cms.entity.Sutra;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蔡迪旻
 *         2015年12月18日
 */
@Configuration
public class RepositoryConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public HibernateRepository<Account> accountRepository() {
        return new HibernateRepository<>(Account.class, sessionFactory);
    }

    @Bean
    public HibernateRepository<Page> pageRepository() {
        return new HibernateRepository<>(Page.class, sessionFactory);
    }

    @Bean
    public HibernateRepository<Sutra> sutraRepository() {
        return new HibernateRepository<>(Sutra.class, sessionFactory);
    }
}
