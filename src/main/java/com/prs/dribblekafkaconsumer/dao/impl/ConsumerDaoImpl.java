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
        String searchCompany = "select c from Company c where c.companyName = :cName";
        Company companyDb = em.createQuery(searchCompany, Company.class).
                setParameter("cName",company.getCompanyName()).getResultList().stream().findFirst().orElse(null);
        if (companyDb==null) {
            em.persist(company);
        }
        else {
            for (Location eachLocation : company.getLocations()){
                String searchLocation = "select l from Location l where l.state = :cState and l.province = :cProvince "
                        + "and l.company.companyId = :cCompanyId";
                Location locationDb = em.createQuery(searchLocation, Location.class).setParameter("cState",eachLocation
                        .getState()).setParameter("cProvince",eachLocation.getProvince()).
                        setParameter("cCompanyId",companyDb.getCompanyId()).getResultList().stream().findFirst()
                        .orElse(null);
                if (locationDb==null){
                    eachLocation.setCompany(companyDb);
                    em.persist(eachLocation);
                }
                else {
                    for (Job eachJob : eachLocation.getJobs()){
                        String searchJob = "select j from Job j where j.jobTitle = :cJobTitle and j.jobType = :cJobType"
                                + " and j.availability = :cAvailability and j.expLevel = :cExpLevel and j.location"
                                + ".locationId = :cLocationId";
                        Job jobDb = em.createQuery(searchJob, Job.class).setParameter("cJobTitle",eachJob.getJobTitle())
                                .setParameter("cJobType",eachJob.getJobType())
                                .setParameter("cAvailability",eachJob.getAvailability())
                                .setParameter("cExpLevel",eachJob.getExpLevel()).setParameter
                                        ("cLocationId",locationDb.getLocationId()).getResultList().stream()
                                .findFirst().orElse(null);
                        if (jobDb==null){
                            eachJob.setLocation(locationDb);
                            em.persist(eachJob);
                        }
                    }
                }
            }
        }
        em.close();}
}
