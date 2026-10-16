package com.naman.placement_tracker.service;

import com.naman.placement_tracker.entity.JobApplication;
import java.util.List;

public interface JobApplicationService {
    List<JobApplication> findAll();
    JobApplication findById(int id);
    JobApplication create(JobApplication application);
    JobApplication update(JobApplication application);
    void deleteById(int id);
}