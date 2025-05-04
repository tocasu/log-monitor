package com.gm.csv.monitor.logger;

import com.gm.csv.monitor.logger.model.Job;
import com.gm.csv.monitor.logger.service.MonitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Component
public class AppRunner {
    @Autowired
    private MonitorService service;

    @Autowired
    private File file;

    @PostConstruct
    public void init() {
        try {
            List<Job> jobs = service.extractOverdueJobs(file);
            List<String> messages = service.analyzeJobs(jobs);
            messages.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
