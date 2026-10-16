package com.naman.placement_tracker.dao;

import com.naman.placement_tracker.entity.JobApplication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JobApplicationDAOImpl implements JobApplicationDAO {

    private final EntityManager entityManager;

    public JobApplicationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JobApplication> findAll() {
        TypedQuery<JobApplication> query = entityManager.createQuery(
                "FROM JobApplication ORDER BY dateApplied DESC", JobApplication.class);
        return query.getResultList();
    }

    @Override
    public JobApplication findById(int id) {
        return entityManager.find(JobApplication.class, id);
    }

    @Override
    public JobApplication save(JobApplication application) {
        return entityManager.merge(application);
    }

    @Override
    public void deleteById(int id) {
        JobApplication application = entityManager.find(JobApplication.class, id);
        if (application != null) {
            entityManager.remove(application);
        }
    }
}
