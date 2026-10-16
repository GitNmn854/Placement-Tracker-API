package com.naman.placement_tracker.dao;

import com.naman.placement_tracker.entity.JobApplication;
import java.util.List;

public interface JobApplicationDAO {
    List<JobApplication> findAll();
    JobApplication findById(int id);
    JobApplication save(JobApplication application);
    void deleteById(int id);
}