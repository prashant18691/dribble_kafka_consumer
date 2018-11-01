package com.prs.dribblekafkaconsumer.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.prs.dribblekafkaconsumer.dao.ConsumerDao;
import com.prs.dribblekafkaconsumer.dto.Company;
import com.prs.dribblekafkaconsumer.dto.Job;
import com.prs.dribblekafkaconsumer.dto.Location;


@Repository
@Transactional
public class ConsumerDaoImpl implements ConsumerDao {

    @PersistenceContext
    private EntityManager em;

    @Override public void save(final Company company){
        String searchCompany = "select count(c) from Company c where c.companyId = :cCompanyId";
        Long companyCount = em.createQuery(searchCompany, Long.class).
                setParameter("cCompanyId",company.getCompanyId()).getSingleResult();
        if (companyCount==0) {
            em.persist(company);
        }
        else {
            for (Location eachLocation : company.getLocations()){
                String searchLocation = "select count(l) from Location l where l.locationId = :cLocationId";
                Long locationCount = em.createQuery(searchLocation, Long.class).setParameter("cLocationId",
                        eachLocation.getLocationId()).getSingleResult();
                if (locationCount==0){
                    em.persist(eachLocation);
                }
                else {
                    for (Job eachJob : eachLocation.getJobs()){
                        String searchJob = "select count(j) from Job j where j.jobId = :cJobId";
                        Long jobCount = em.createQuery(searchJob, Long.class).setParameter("cJobId",eachJob.getJobId()).getSingleResult();
                        if (jobCount==0){
                            em.persist(eachJob);
                        }
                    }
                }
            }
        }
        em.close();}
}
