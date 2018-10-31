package com.prs.dribblekafkaconsumer.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.prs.dribblekafkaconsumer.dao.ConsumerDao;
import com.prs.dribblekafkaconsumer.dto.Company;

@Repository
@Transactional
public class ConsumerDaoImpl implements ConsumerDao {

    @PersistenceContext
    private EntityManager em;

    @Override public void save(final Company company){
        em.merge(company);
    }
}
