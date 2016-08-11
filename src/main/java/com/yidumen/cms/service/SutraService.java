package com.yidumen.cms.service;

import com.yidumen.cms.entity.Sutra;
import com.yidumen.cms.repository.SutraHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 蔡迪旻
 *         2016年01月21日
 */
@Service
public class SutraService {


    @Autowired
    private SutraHibernateRepository sutraRepository;

    public List<Sutra> findAll() {
        return sutraRepository.findAll();
    }

    public Sutra find(Long id) {
        return sutraRepository.find(id);
    }

    public void update(Sutra sutra) {
        sutraRepository.edit(sutra);
    }
}
