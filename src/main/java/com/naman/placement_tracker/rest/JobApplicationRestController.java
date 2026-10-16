package com.naman.placement_tracker.rest;

import com.naman.placement_tracker.entity.JobApplication;
import com.naman.placement_tracker.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobApplicationRestController {

    private final JobApplicationService service;

    public JobApplicationRestController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/applications")
    public List<JobApplication> findAll() {
        return service.findAll();
    }

    @GetMapping("/applications/{id}")
    public JobApplication getById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/applications")
    public JobApplication add(@RequestBody JobApplication application) {
        return service.create(application);
    }

    @PutMapping("/applications")
    public JobApplication update(@RequestBody JobApplication application) {
        return service.update(application);
    }

    @DeleteMapping("/applications/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "Deleted application id - " + id;
    }
}
