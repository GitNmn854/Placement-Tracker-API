package com.naman.placement_tracker.service;

import com.naman.placement_tracker.dao.JobApplicationDAO;
import com.naman.placement_tracker.entity.JobApplication;
import com.naman.placement_tracker.entity.JobStatus;
import com.naman.placement_tracker.exception.JobApplicationNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationDAO dao;

    public JobApplicationServiceImpl(JobApplicationDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<JobApplication> findAll() {
        return dao.findAll();
    }

    @Override
    public JobApplication findById(int id) {
        JobApplication application = dao.findById(id);
        if (application == null) {
            throw new JobApplicationNotFoundException("Application id not found - " + id);
        }
        return application;
    }

    @Override
    @Transactional
    public JobApplication create(JobApplication application) {
        application.setId(0); // id 0 forces an INSERT instead of an update
        if (application.getStatus() == null) {
            application.setStatus(JobStatus.APPLIED);
        }
        if (application.getDateApplied() == null) {
            application.setDateApplied(LocalDate.now());
        }
        return dao.save(application);
    }

    @Override
    @Transactional
    public JobApplication update(JobApplication application) {
        findById(application.getId()); // throws 404 if the id doesn't exist
        return dao.save(application);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        findById(id); // throws 404 if the id doesn't exist
        dao.deleteById(id);
    }
}