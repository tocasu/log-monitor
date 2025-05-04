package com.gm.csv.monitor.logger.service;

import com.gm.csv.monitor.logger.utils.JobUtils;
import com.gm.csv.monitor.logger.config.Config;
import com.gm.csv.monitor.logger.model.Job;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MonitorService {

    private final LogEntryReader reader;

    private final JobProcessor processor;

    private final Config config;

    public List<Job> extractOverdueJobs(@Qualifier("logFile") File file) throws IOException {
        return processor.convertLogEntries(reader.read(file).getEntries())
                .getJobs().stream().filter(job -> job.computeDuration() >= config.getWarningDuration())
                .collect(Collectors.toList());
    }

    public List<String> analyzeJobs(List<Job> jobs) throws IOException {
        return jobs.stream().map(job -> job.computeDuration() >= config.getErrorDuration()
                ? JobUtils.buildErrorMessage(job, config.getErrorDuration())
                : JobUtils.buildWarningMessage(job, config.getWarningDuration())).collect(Collectors.toList());
    }
}
