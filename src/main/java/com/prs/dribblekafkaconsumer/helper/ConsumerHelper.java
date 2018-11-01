package com.prs.dribblekafkaconsumer.helper;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.CollectionUtils;
import com.prs.dribblekafkaconsumer.dto.Company;
import com.prs.dribblekafkaconsumer.dto.Job;
import com.prs.dribblekafkaconsumer.dto.Location;
import com.prs.dribblekafkaconsumer.vo.CompanyVO;
import com.prs.dribblekafkaconsumer.vo.JobVO;
import com.prs.dribblekafkaconsumer.vo.LocationVO;


public class ConsumerHelper {
    public static Company transformVOToEntity(final CompanyVO companyVO) {
        Company company = new Company(companyVO.getCompanyName(), companyVO.getDescription(),
                companyVO.getMainPhoneNumber());
        if (!CollectionUtils.isEmpty(companyVO.getLocations())) {
            Set<Location> locationList = new HashSet<>();
            for (LocationVO locationVO : companyVO.getLocations()) {
                Location location = new Location(locationVO.getState(), locationVO.getProvince(),
                        locationVO.getCountry(), locationVO.getDescription(), locationVO.getPhoneNumber(),
                        company);
                if (!CollectionUtils.isEmpty(locationVO.getJobs())) {
                    Set<Job> jobList = new HashSet<>();
                    for (JobVO jobVO : locationVO.getJobs()) {
                        Job job = new Job(jobVO.getJobTitle(), jobVO.getJobType(),
                                String.valueOf(jobVO.getAvailability())
                                , jobVO.getCharge(), jobVO.getCurrency(),jobVO.getDescription(),
                                String.valueOf(jobVO.getExpLevel()),new Date(),
                                jobVO.getSkills(),location);
                        jobList.add(job);
                    }
                    location.setJobs(jobList);
                }
                locationList.add(location);
            }
            company.setLocations(locationList);
        }
        return company;
    }
}
